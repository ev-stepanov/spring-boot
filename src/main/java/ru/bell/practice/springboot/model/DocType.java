package ru.bell.practice.springboot.model;

import javax.persistence.*;

@Entity(name = "Doc")
public class DocType {

    @Id
    @Column
    private Long id;

    /**
     * Специальное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Код документа
     */
    @Column(name = "doc_code")
    private String docCode;

    /**
     * Наименование документа
     */
    @Column(name = "doc_name")
    private String docName;

    public DocType() {

    }

    public DocType(String docCode, String docName) {
        this.docCode = docCode;
        this.docName = docName;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public String getDocCode() {
        return docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    @Override
    public String toString() {
        return "DocType{" +
                "id=" + id +
                ", docCode=" + docCode +
                ", docName='" + docName + '\'' +
                '}';
    }
}