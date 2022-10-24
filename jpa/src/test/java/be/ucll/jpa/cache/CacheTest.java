package be.ucll.jpa.cache;

import be.ucll.jpa.entities.caching.Description;
import be.ucll.jpa.entities.caching.DescriptionTranslation;
import be.ucll.jpa.entities.caching.Item;
import be.ucll.spring.JpaConfig;
import be.ucll.spring.MyAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;


@Test
@ContextConfiguration(classes = {JpaConfig.class, MyAppConfig.class})
@ActiveProfiles("jpa")
public class CacheTest extends AbstractTransactionalTestNGSpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @SuppressWarnings("all")
    public void testCaching() {

        // -BEGIN DATA SETUP -- must run in a separate transaction to demonstrate the caching in a single test
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setPropagationBehavior(TransactionTemplate.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                Item item = new Item();
                Description description = new Description();
                DescriptionTranslation descriptionTranslation = new DescriptionTranslation();
                descriptionTranslation.setLanguage("NL");
                descriptionTranslation.setTranslation("testing");
                description.getTranslations().add(descriptionTranslation);
                item.setItemDescription(description);
                entityManager.persist(item);
                return null;
            }
        });
        // -END DATA SETUP
        entityManager.clear();
        entityManagerFactory.getCache().evictAll();

        // ----------2ND LEVEL CACHE EXAMPLE---------

        //1) First time an Item is loaded, it will trigger two queries:
        // - one for the item
        // - another for the relations
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                Item item = (Item) entityManager.find(Item.class, new Long(1));
                // Triggers two queries, one for the description, another for the translation
                item.getItemDescription().getTranslations().iterator().next()
                        .getTranslation();
                return null;
            }
        });

        //2) Redo the same thing, but now no more queries as all data was cached !
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                Item item = (Item) entityManager.find(Item.class, new Long(1));
                item.getItemDescription().getTranslations().iterator().next()
                        .getTranslation();
                return null;
            }
        });

        // Clear second level cache so we can prove that the caching really worked
        entityManager.clear();
        entityManagerFactory.getCache().evictAll(); // <--- clear the second level cache, you normally never do this

        //3) We now see queries again, as the cache was cleared
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                Item item = (Item) entityManager.find(Item.class, new Long(1));
                item.getItemDescription().getTranslations().iterator().next()
                        .getTranslation();
                return null;
            }
        });

        // Clear everything up again so we can start cleanly for the next test
        entityManager.clear();
        entityManagerFactory.getCache().evictAll();

        // ----------QUERY CACHE EXAMPLE---------

        //1) First time query is executed, query is also not cachable so we will see queries
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                List<Item> items = (List<Item>) entityManager.createQuery("from CacheItem").getResultList();
                // Triggers another two queries to load the description and the translations
                items.iterator().next().getItemDescription().getTranslations()
                        .iterator().next();
                return null;
            }
        });

        //2) Doing the same query again, but now cache the query, this will again execute our query, however, it will
        // NOT trigger a query for the relations as they where cached (2nd level cache) by the previous query
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                List<Item> items = (List<Item>) entityManager.createQuery("from CacheItem").setHint("org.hibernate.cacheable", true).getResultList();
                // No more queries here ! getting the values from second level cache
                items.iterator().next().getItemDescription().getTranslations()
                        .iterator().next();
                return null;
            }
        });


        //3) Do the same query again, but now we will no longer see any query at all
        //- our query itself was cached previously
        //- resulting data was already cached
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                List<Item> items = (List<Item>) entityManager.createQuery("from CacheItem").setHint("org.hibernate.cacheable", true).getResultList();
                // No more queries here ! getting the values from second level cache
                items.iterator().next().getItemDescription().getTranslations()
                        .iterator().next();
                return null;
            }
        });

    }
}
