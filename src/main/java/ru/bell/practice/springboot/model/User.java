package ru.bell.practice.springboot.model;

import javax.persistence.*;

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

    @Column(name = "citizenship_id")
    private Long citizenshipId;


    /**
     * Номер телефона
     */
    @Column(length = 12)
    private String phone;

    /**
     * Действителен?
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "office_id", insertable=false, updatable=false)
    private Office office;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citizenship_id", insertable=false, updatable=false)
    private Country citizenship;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id")
    @MapsId
    private DocUser docUser;

    public User() {

    }

    public User(Long officeId, String firstName, String position) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.position = position;
        this.isIdentified = true;
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

    public Country getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Country citizenship) {
        this.citizenship = citizenship;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Long getCitizenshipId() {
        return citizenshipId;
    }

    public void setCitizenshipId(Long citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    public DocUser getDocUser() {
        return docUser;
    }

    public void setDocUser(DocUser docUser) {
        this.docUser = docUser;
    }
}