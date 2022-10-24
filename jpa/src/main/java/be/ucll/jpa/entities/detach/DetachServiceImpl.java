package be.ucll.jpa.entities.detach;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class DetachServiceImpl implements DetachService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DetachParent getDetach() {
    return null;
    }

    @Override
    public void saveDetach(DetachParent detachParent) {

    }
}
