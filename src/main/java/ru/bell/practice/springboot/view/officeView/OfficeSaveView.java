package ru.bell.practice.springboot.view.officeView;

import javax.validation.constraints.*;

public class OfficeSaveView {

    @NotNull
    private Long orgId;

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{1,50}")
    private String name;

    @Size(max = 255)
    @Pattern(regexp = "[a-zA-Zа-яА-Я0-9 ,./-]{1,255}")
    private String address;

    @Size(min = 7, max = 12)
    @Pattern(regexp = "(\\+?)\\d{7,12}")
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