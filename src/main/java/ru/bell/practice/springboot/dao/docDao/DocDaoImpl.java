package ru.bell.practice.springboot.dao.docDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
}
