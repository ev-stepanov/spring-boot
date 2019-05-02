package ru.bell.practice.springboot.model;

import javax.persistence.*;

@Entity(name = "Doc_type")
public class DocType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Long code;

    /**
     * Наименование документа
     */
    @Column(name = "doc_name")
    private String name;

    public DocType() {

    }

    public DocType(Long code, String name) {
        this.code = code;
        this.name = name;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public Long getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}