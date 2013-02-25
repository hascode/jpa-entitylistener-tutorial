package it;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.sql.SQLException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hascode.tutorial.entity.Book;

public class BookIT {
	static EntityManagerFactory emf;
	static EntityManager em;
	static EntityTransaction tx;

	@BeforeClass
	public static void setupClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("hascode-tutorial");
		em = emf.createEntityManager();
		tx = em.getTransaction();
	}

	@Before
	public void setUp() {
		tx.begin();
	}

	@AfterClass
	public static void teardownClass() throws SQLException {
		em.close();
		emf.close();
	}

	@After
	public void tearDown() {
		tx.rollback();
	}

	@Test(timeout = 10000)
	public void shouldTriggerBookLifecycles() throws Exception {
		Book book = new Book();
		book.setPublished(new Date());
		book.setTitle("Some book");
		em.persist(book);
		assertThat(book.getId(), is(notNullValue()));
		assertThat(book.getId(), is(1L));

		book.setPublished(new Date());
		em.persist(book);
		em.flush();
		em.refresh(book); // we just want to trigger postLoad

		Book book2 = em.find(Book.class, 1L);
		assertThat(book2, is(notNullValue()));
		assertThat(book2.getId(), is(book.getId()));
		assertThat(book2.getTitle(), is(book.getTitle()));

		em.remove(book2);
		em.flush();
	}

	@Test(timeout = 10000, expected = PersistenceException.class)
	public void testShouldValidateTitle() throws Exception {
		Book book = new Book();
		book.setPublished(new Date());
		book.setTitle("The shitty boook");
		em.persist(book);
	}
}
