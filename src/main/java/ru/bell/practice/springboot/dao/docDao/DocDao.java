package ru.bell.practice.springboot.dao.docDao;

import ru.bell.practice.springboot.model.DocType;

import java.util.List;

public interface DocDao {
    List<DocType> list();
}
