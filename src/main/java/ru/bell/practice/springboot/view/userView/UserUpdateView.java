package ru.bell.practice.springboot.view.userView;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.util.Date;

public class UserUpdateView {
    @NotNull
    private Long id;

    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{1,50}")
    private String firstName;

    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{1,100}")
    private String position;

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{1,50}")
    private String secondName;

    @Size(max = 50)
    @Pattern(regexp = "[a-zA-Zа-яА-Я \\d*]{1,50}")
    private String middleName;

    @Size(min = 7, max = 12)
    @Pattern(regexp = "(\\+?)\\d{7,12}")
    private String phone;

    @Size(max = 50)
    @Pattern(regexp = "[A-Za-zА-Яа-я0-9 ,-]{1,50}")
    private String docName;

    @Size(max = 20)
    @Pattern(regexp = "\\d{1,20}")
    private String docNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date docDate;
    private Boolean isIdentified;
    private Long officeId;
    private Long citizenshipCode;

    public UserUpdateView() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }
}
