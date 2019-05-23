package ru.bell.practice.springboot.dao.docDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.DocType;

import javax.persistence.criteria.*;
import javax.persistence.*;
import java.util.List;

/**
 * DAO слой удостверений
 */
@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager em;

    /**
     * Конструктор
     * @param em контекст
     */
    @Autowired
    public DocDaoImpl(EntityManager em) {
        this.em = em;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> list() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docType = criteriaQuery.from(DocType.class);

        criteriaQuery.select(docType);
        TypedQuery<DocType> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public DocType getByName(String docName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docTypeRoot = criteriaQuery.from(DocType.class);

        Predicate predicate = criteriaBuilder.equal(docTypeRoot.get("name"), docName);

        criteriaQuery.select(docTypeRoot).where(predicate);
        TypedQuery<DocType> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public DocType getByCode(Long docCode) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocType> criteriaQuery = criteriaBuilder.createQuery(DocType.class);
        Root<DocType> docTypeRoot = criteriaQuery.from(DocType.class);

        Predicate predicate = criteriaBuilder.equal(docTypeRoot.get("code"), docCode);

        criteriaQuery.select(docTypeRoot).where(predicate);
        TypedQuery<DocType> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }
}
