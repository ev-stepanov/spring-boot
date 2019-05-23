package ru.bell.practice.springboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "Doc_user")
public class DocUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Дата регистрации
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "doc_date")
    private Date docDate;

    /**
     *Номер документа
     */
    @Column(name = "doc_number", length = 20)
    private String docNumber;

    /**
     * Тип документа
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "doc_id")
    private DocType docType;

    public DocUser() {
    }

    public DocUser(Date docDate, String docNumber, DocType docType) {
        this.docDate = docDate;
        this.docNumber = docNumber;
        this.docType = docType;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }

    @Override
    public String toString() {
        return "DocUser{" +
                "id=" + id +
                ", version=" + version +
                ", docDate=" + docDate +
                ", docNumber='" + docNumber + '\'' +
                ", docType=" + docType +
                '}';
    }
}
