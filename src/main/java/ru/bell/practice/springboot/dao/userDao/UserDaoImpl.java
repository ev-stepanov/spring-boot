package ru.bell.practice.springboot.dao.userDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.User;

import javax.persistence.criteria.*;
import javax.persistence.*;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    /**
     * Конструктор
     * @param em контекст
     */
    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> filter(Long officeId, String firstName, String secondName, String middleName,
                             String position, Long docCode, Long citizenshipCode) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate predicate = criteriaBuilder.equal(userRoot.get("office").get("id"), officeId);

        if (firstName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("firstName"), firstName));
        }

        if (secondName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("secondName"), secondName));
        }

        if (middleName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("middleName"), middleName));
        }

        if (position != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("position"), position));
        }

        if (docCode != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("docUser").get("docType").get("code"), docCode));
        }

        if (citizenshipCode != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("citizenship").get("code"), citizenshipCode));
        }

        criteriaQuery.select(userRoot).where(predicate);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long id) {
        return em.find(User.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User user) {
        em.merge(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }
}
