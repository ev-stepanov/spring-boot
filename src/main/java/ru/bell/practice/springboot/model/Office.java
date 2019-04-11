package ru.bell.practice.springboot.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Office")
public class Office {

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
     * Ид организации
     */
    @Column(name = "org_id", nullable = false)
    private Long orgId;

    /**
     * Название офиса
     */
    @Column(length = 50)
    private String name;

    /**
     * Адрес офиса
     */
    @Column
    private String address;

    /**
     * Телефон
     */
    @Column(length = 12)
    private String phone;

    /**
     * Работает офис?
     */
    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", insertable=false, updatable=false)
    private Organization organization;

    public Office() {

    }

    public Office(Long orgId) {
        this.orgId = orgId;
        this.isActive = true;
        this.version = 0;
    }

    public Long getId() {
        return id;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Office office = (Office) o;
        return id.equals(office.id) &&
                version.equals(office.version) &&
                orgId.equals(office.orgId) &&
                Objects.equals(name, office.name) &&
                Objects.equals(address, office.address) &&
                Objects.equals(phone, office.phone) &&
                Objects.equals(isActive, office.isActive) &&
                Objects.equals(organization, office.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, orgId, name, address, phone, isActive, organization);
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", version=" + version +
                ", orgId=" + orgId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                ", organization=" + organization +
                '}';
    }
}