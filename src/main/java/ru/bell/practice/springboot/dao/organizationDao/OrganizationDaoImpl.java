package ru.bell.practice.springboot.dao.organizationDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements  OrganizationDao {

    private final EntityManager em;

    /**
     *
     * @param em контекст
     */
    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> filter(String name, String inn, Boolean isActive) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        Predicate predicate = criteriaBuilder.equal(organizationRoot.get("name"), name);
        if (inn != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("inn"), inn));
        }
        if (isActive != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("isActive"), isActive));
        }
        criteriaQuery.select(organizationRoot).where(predicate);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getById(Long id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }
}
