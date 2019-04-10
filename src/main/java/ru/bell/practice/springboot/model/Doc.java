package ru.bell.practice.springboot.model;

import javax.persistence.*;

@Entity(name = "Doc")
public class Doc {

    @Id
    @Column(name = "doc_code")
    private Long docCode;

    /**
     * Специальное поле Hibernate
     */
    @Version
    private Integer version;


    /**
     * Наименование документа
     */
    @Column(name = "doc_name")
    private String docName;

    public Doc() {

    }

    public Doc(Long docCode, String docName) {
        this.docCode = docCode;
        this.docName = docName;
        this.version = 0;
    }

    public Long getDocCode() {
        return docCode;
    }

    public String getDocName() {
        return docName;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "docCode=" + docCode +
                ", docName='" + docName + '\'' +
                '}';
    }
}