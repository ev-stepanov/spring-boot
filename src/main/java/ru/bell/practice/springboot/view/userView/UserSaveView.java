package ru.bell.practice.springboot.view.userView;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class UserSaveView {

    @NotNull(message = "officeId cannot be null")
    private Long officeId;

    @NotEmpty(message = "first name cannot be empty")
    private String firstName;

    @NotEmpty(message = "position cannot be empty")
    private String position;

    private String secondName;
    private String middleName;
    private String phone;
    private Boolean isIdentified;
    private Long citizenshipCode;
    private Long docName;
    private Long docNumber;
    private Date docDate;
    private Long docCode;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Long getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Long citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Long getDocName() {
        return docName;
    }

    public void setDocName(Long docName) {
        this.docName = docName;
    }

    public Long getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(Long docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public Long getDocCode() {
        return docCode;
    }

    public void setDocCode(Long docCode) {
        this.docCode = docCode;
    }

    @Override
    public String toString() {
        return "UserSaveView{" +
                "officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", position='" + position + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phone='" + phone + '\'' +
                ", isIdentified=" + isIdentified +
                ", citizenshipCode=" + citizenshipCode +
                ", docName=" + docName +
                ", docNumber=" + docNumber +
                ", docDate=" + docDate +
                ", docCode=" + docCode +
                '}';
    }
}