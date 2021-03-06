package ru.bell.practice.springboot.model;

import javax.persistence.*;
import java.util.Objects;

/**
 *Пользователь
 */
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    /**
     * Служебное поле Hibernate
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
     * Номер телефона
     */
    @Column(length = 12)
    private String phone;

    /**
     * Действителен?
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Ид офиса
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     *Гражданство
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citizenship_id")
    private Country citizenship;

    /**
     * Документ пользователя
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "doc_user_id")
    private DocUser docUser;

    public User() {

    }

    public User(String firstName, String position) {
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

    public DocUser getDocUser() {
        return docUser;
    }

    public void setDocUser(DocUser docUser) {
        this.docUser = docUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                Objects.equals(version, user.version) &&
                firstName.equals(user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(middleName, user.middleName) &&
                Objects.equals(position, user.position) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(isIdentified, user.isIdentified) &&
                Objects.equals(office, user.office) &&
                Objects.equals(citizenship, user.citizenship) &&
                Objects.equals(docUser, user.docUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, firstName, secondName, middleName, position, phone, isIdentified, office, citizenship, docUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", isIdentified=" + isIdentified +
                ", office=" + office +
                ", citizenship=" + citizenship +
                ", docUser=" + docUser +
                '}';
    }
}