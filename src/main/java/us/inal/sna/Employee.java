package us.inal.sna;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.Set;

@NodeEntity
public class Employee {

    @Relationship(type = "TAKDIR_ETTI")
    private Set<Takdir> takdirettis;

    @Relationship(type="TAKDIR_ALDI", direction=Relationship.INCOMING)
    private Set<Takdir> takdiraldis;


	@GraphId private Long id;

	private Integer lmsMemberID;
	private Integer gender;
    private Integer uID;
    private Boolean isCompleteProfile;
    private String fullName;
    private String surname;
    private String name;
    private String password;
    private String avatar;
    private String aciklama;
    private String activationCode;
    private String activationShortCode;
    private String lms_secretCode;
    private String email;
    private Integer userType;
    private Boolean isRegisterLMS;
    private Integer isActive;
    private Boolean isFirstLoginOK;

    @DateString("yyyy-MM-dd\'T\'HH:mm:ss.SSS")
    private Date lmsRegisterDate;
    @DateString("yyyy-MM-dd\'T\'HH:mm:ss")
    private Date birtDate;

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getActivationShortCode() {
        return activationShortCode;
    }

    public void setActivationShortCode(String activationShortCode) {
        this.activationShortCode = activationShortCode;
    }

    public String getLms_secretCode() {
        return lms_secretCode;
    }

    public void setLms_secretCode(String lms_secretCode) {
        this.lms_secretCode = lms_secretCode;
    }

    public Date getLmsRegisterDate() {
        return lmsRegisterDate;
    }

    public void setLmsRegisterDate(Date lmsRegisterDate) {
        this.lmsRegisterDate = lmsRegisterDate;
    }

    public Date getBirtDate() {
        return birtDate;
    }

    public void setBirthDate(Date birtDate) {
        this.birtDate = birtDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLmsMemberID() {
        return lmsMemberID;
    }

    public void setLmsMemberID(Integer lmsMemberID) {
        this.lmsMemberID = lmsMemberID;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Boolean getIsCompleteProfile() {
        return isCompleteProfile;
    }

    public void setIsCompleteProfile(Boolean isCompleteProfile) {
        this.isCompleteProfile = isCompleteProfile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Boolean getIsRegisterLMS() {
        return isRegisterLMS;
    }

    public void setIsRegisterLMS(Boolean isRegisterLMS) {
        this.isRegisterLMS = isRegisterLMS;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsFirstLoginOK() {
        return isFirstLoginOK;
    }

    public void setIsFirstLoginOK(Boolean isFirstLoginOK) {
        this.isFirstLoginOK = isFirstLoginOK;
    }
}
