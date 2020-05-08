package ru.db.springbootjpa.repository;

import ru.db.springbootjpa.model.Person;
import java.math.BigInteger;
import java.util.List;

public interface PersonRepository {
    void insert(Person p);
    Person getById(int id);
    Person getFirst();
    List<Person> getAll();
    Person getByName(String name);
    List<String> getAllName();
    BigInteger getMaxID();
    void deletePerson(int id);
}
