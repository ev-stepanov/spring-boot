package ru.bell.practice.springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager entityManager;

    @Autowired
    public DocDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<DocType> list() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docType = criteriaQuery.from(DocType.class);
        criteriaQuery.select(docType);
        TypedQuery<DocType> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
