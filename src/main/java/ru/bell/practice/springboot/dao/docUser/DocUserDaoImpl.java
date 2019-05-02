package ru.bell.practice.springboot.dao.docUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bell.practice.springboot.model.DocUser;

import javax.persistence.EntityManager;

@Repository
public class DocUserDaoImpl implements DocUserDao {

    private final EntityManager em;

    /**
     * Конструктор
     * @param em контекст
     */
    @Autowired
    public DocUserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(DocUser docUser) {
        em.persist(docUser);
    }
}
