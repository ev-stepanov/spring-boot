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

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<Organization> listByName(Organization organization) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        Predicate predicate = criteriaBuilder.like(organizationRoot.get("name"), "%" + organization.getName() + "%");
        if (organization.getInn() != null && !organization.getInn().isEmpty()) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("inn"), organization.getInn()));
        }
        if (organization.getActive() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(organizationRoot.get("isActive"), organization.getActive()));
        }
        criteriaQuery.select(organizationRoot).where(predicate);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public Organization organizationByID(Long id) {
        return em.find(Organization.class, id);
    }

    @Override
    public void update(Organization organization) {
        em.merge(organization);
    }

    @Override
    public void save(Organization organization) {
        em.persist(organization);
    }
}
