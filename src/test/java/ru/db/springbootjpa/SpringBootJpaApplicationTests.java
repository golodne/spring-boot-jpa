package ru.db.springbootjpa;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.db.springbootjpa.model.Person;
import ru.db.springbootjpa.repository.PersonRepository;
import ru.db.springbootjpa.repository.PersonRepositoryJpa;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpringBootJpaApplicationTests {

	@TestConfiguration
	static class TestDataBaseConfiguration {
		@Bean
		public PersonRepository personRepository() {
			return new PersonRepositoryJpa();
		}
	}

	@Autowired
	private TestEntityManager em;

	@Autowired
	PersonRepository personRepository;

	@Test
	public void whenFindByName_thenReturnPerson() {
		Person p = new Person("Vladimir");
		em.persist(p);
		em.flush();
		Person found = personRepository.getByName("Vladimir");
		Assert.assertEquals(found.getName(),p.getName());
	}

}
