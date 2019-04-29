package ru.bell.practice.springboot.view.officeView;

import javax.validation.constraints.NotNull;

public class OfficeSaveView {

    @NotNull(message = "orgId cannot be null.")
    private Long orgId;

    private String name;

    private String address;

    private String phone;

    private Boolean isActive = true;

    public OfficeSaveView() {
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
}