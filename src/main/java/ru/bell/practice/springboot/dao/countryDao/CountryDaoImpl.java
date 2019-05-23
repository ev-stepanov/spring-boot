package ru.bell.practice.springboot.dao.countryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.Country;

import javax.persistence.criteria.*;
import javax.persistence.*;
import java.util.List;

/**
 * DAO слой гражданств
 */
@Repository
public class CountryDaoImpl implements CountryDao {

    private final EntityManager em;

    /**
     * Конструктор
     * @param em контекст
     */
    @Autowired
    public CountryDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Country> list() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> country = criteriaQuery.from(Country.class);
        criteriaQuery.select(country);
        TypedQuery<Country> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Country getByCode(Long code) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);

        Predicate predicate = criteriaBuilder.equal(countryRoot.get("code"), code);

        criteriaQuery.select(countryRoot).where(predicate);
        TypedQuery<Country> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
