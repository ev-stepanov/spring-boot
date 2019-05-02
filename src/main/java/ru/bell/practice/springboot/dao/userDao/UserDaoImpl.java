package ru.bell.practice.springboot.dao.userDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public List<User> filter(User user) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate predicate = criteriaBuilder.equal(userRoot.get("office").get("id"), user.getOffice().getId());

        if (user.getFirstName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                    userRoot.get("firstName"), "%" + user.getFirstName() + "%"));
        }

        if (user.getSecondName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                    userRoot.get("secondName"), "%" + user.getSecondName() + "%"));
        }

        if (user.getMiddleName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                    userRoot.get("middleName"), "%" + user.getMiddleName() + "%"));
        }

        if (user.getPosition() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(
                    userRoot.get("position"), "%" + user.getPosition() + "%"));
        }

        if (user.getDocUser() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("docUser").get("docType").get("code"), user.getDocUser().getDocType().getCode()));
        }

        if (user.getCitizenship() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(
                    userRoot.get("citizenship").get("code"), user.getCitizenship().getCode()));
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
