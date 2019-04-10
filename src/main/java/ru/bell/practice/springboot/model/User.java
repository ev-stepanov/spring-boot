package ru.bell.practice.springboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * Специальное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Имя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * Должность
     */
    @Column(length = 100, nullable = false)
    private String position;

    /**
     * Ид офиса
     */

    @Column(name = "office_id")
    private Long officeId;


    /**
     *Гражданство
     */

    @Column(name = "citizenship_code")
    private Long citizenshipCode;

    /**
     * Ид документа
     */

    @Column(name = "doc_code")
    private Long docCode;

    /**
     * Дата регистрации
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "doc_date")
    private Date docDate;

    /**
     *Номер документа
     */
    @Column(name = "doc_number")
    private Long docNumber;

    /**
     * Номер телефона
     */
    @Column(length = 12)
    private String phone;

    /**
     * Идантифицирован?
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "office_id", insertable=false, updatable=false)
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenship_code", insertable=false, updatable=false)
    private Country citizenshipId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code", insertable=false, updatable=false)
    private Doc docId;


    public User() {

    }

    public User(Long officeId, String firstName, String position) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.position = position;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Long getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Long docNumber) {
        this.docNumber = docNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Country getCitizenshipId() {
        return citizenshipId;
    }

    public void setCitizenshipId(Country citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    public Doc getDocId() {
        return docId;
    }

    public void setDocId(Doc docId) {
        this.docId = docId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }
}