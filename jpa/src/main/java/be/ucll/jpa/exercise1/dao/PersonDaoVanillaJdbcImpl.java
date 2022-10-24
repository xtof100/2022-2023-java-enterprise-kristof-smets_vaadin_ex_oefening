package be.ucll.jpa.exercise1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import be.ucll.jpa.exercise1.AddressSearchCriteria;
import be.ucll.jpa.exercise1.entities.Person;

@Repository
@Transactional
@Profile("vanilla-jdbc")
public class PersonDaoVanillaJdbcImpl implements PersonDao {

	private DataSource dataSource;

	@Autowired
	public PersonDaoVanillaJdbcImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Person> findAll() {
		try (Connection connection = dataSource.getConnection()) {
			List<Person> result = new ArrayList<>();

			Statement s = connection.createStatement();
			ResultSet resultSet = s.executeQuery("select p.id, p.name, p.firstname, a.person_id, a.box, a.country, a.housenumber, "
					+ "a.municipality, a.postalcode, a.street, a.id from person2 p left join person_address a on p.id = a.person_id");

			while (resultSet.next()) {
				return null;
			}
			return result;
		} catch (SQLException s) {
			throw new RuntimeException(s);
		}
	}

	@Override
	public List<Person> findByName(final String name) {
		return null;
	}

	@Override
	public void savePerson(final Person person) {

		try (Connection connection = dataSource.getConnection()) {

			PreparedStatement psPerson = connection.prepareStatement("insert into person2 (name, firstname) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException s) {
			throw new RuntimeException(s);
		}
	}

	@Override
	public void removePerson(final Person person) {

	}

	@Override
	public List<Person> findByAddress(final AddressSearchCriteria addressSearchCriteria) {
		return null;
	}
}
