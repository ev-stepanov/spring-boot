package ru.bell.practice.springboot.dao.officeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * DAO для работы с офисами
 */
@Repository
public class OfficeDaoImpl implements OfficeDao {
    private final EntityManager em;

    /**
     * Конструктор.
     *
     * @param em контекст
     */
    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> filter(Long orgId, String name, String phone, Boolean isActive) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);

        Predicate predicate = criteriaBuilder.equal(officeRoot.get("organization").get("id"), orgId);
        if (name != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("name"), name));
        }
        if (phone != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("phone"), phone));
        }
        if (isActive != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(officeRoot.get("isActive"), isActive));
        }

        criteriaQuery.select(officeRoot).where(predicate);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getById(Long id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Office office) {
        em.merge(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Office office) {
        em.persist(office);
    }
}
