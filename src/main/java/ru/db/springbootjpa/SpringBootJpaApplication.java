package ru.db.springbootjpa;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.db.springbootjpa.model.Person;
import ru.db.springbootjpa.repository.PersonRepository;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootJpaApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(SpringBootJpaApplication.class);
		PersonRepository repository = context.getBean(PersonRepository.class);

		Person p1 = new Person("Vladimir");
		repository.insert(p1);

		int personId = p1.getId();

		Person nullPerson = repository.getById(personId);
		System.out.println("person is " + nullPerson);

		System.out.println("first is " + repository.getFirst());

		repository.insert(new Person("Oleg"));
		repository.insert(new Person("John"));

		List<Person> personList = repository.getAll();
		personList.stream().forEach(System.out::println);

		System.out.println("find Oleg " + repository.getByName("Oleg"));

		System.out.println("all names:");
		List<String> listNames = repository.getAllName();
		listNames.stream().forEach(System.out::println);

		System.out.println("maxID: " + repository.getMaxID());

		System.out.println("delete Oleg id=2");
		repository.deletePerson(2);
		List<Person> listNew = repository.getAll();
		listNew.stream().forEach(System.out::println);

		Console.main(args);
	}
}
