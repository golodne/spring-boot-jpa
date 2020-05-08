package ru.db.springbootjpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.db.springbootjpa.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class PersonRepositoryJpa implements PersonRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Person p) {
        em.persist(p);
    }

    @Override
    public Person getById(int id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person getFirst() {
        TypedQuery<Person> query = em.createQuery(
                "select p from Person p where p.id = 1", Person.class);
        return query.getSingleResult();
    }

    @Override
    public List<Person> getAll() {
        TypedQuery<Person> query = em.createQuery(
                "select p from Person p", Person.class);
        return query.getResultList();
    }

    @Override
    public Person getByName(String name) {
        TypedQuery<Person> query = em.createQuery(
                "select p from Person p where p.name = :name", Person.class);
        query.setParameter("name",name);

        return query.getSingleResult();
    }

    @Override
    public List<String> getAllName() {
        Query query = em.createQuery("select p.name from Person p");
        return query.getResultList();
    }

    @Override
    public BigInteger getMaxID() {
        Query query = em.createQuery("select MAX(p.id) from Person p");
        return BigInteger.valueOf((int) query.getSingleResult());
    }

    @Override
    public void deletePerson(int id) {
        Query query = em.createQuery("delete from Person p where p.id = :id");
        query.setParameter("id",id);
        int rowCount = query.executeUpdate();
    }
}
