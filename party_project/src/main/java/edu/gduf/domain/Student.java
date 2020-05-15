package edu.gduf.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

/**
 * Demo Class
 * 学生对象
 * 对应数据库中的student表
 * 主要存储学生的个人基本信息
 * @author 古市
 * @date 2020-02-29 18:46
 **/
public class Student implements Serializable {
    /**
     * 学号
     */
    private String num;
    /**
     * 班级号
     */
    @JSONField(ordinal = 1)
    private String classNum;
    /**
     * 姓名
     */
    @JSONField(ordinal = 2)
    private String name;
    /**
     * 性别
     */
    @JSONField(ordinal = 3)
    private String sex;
    /**
     * 民族
     */
    @JSONField(ordinal = 4)
    private String nation;
    /**
     * 籍贯
     */
    @JSONField(ordinal = 5)
    private String studentNative;
    /**
     * 联系方式
     */
    @JSONField(ordinal = 6)
    private String telephone;
    /**
     * 身份证号
     */
    @JSONField(ordinal = 7)
    private String identityCard;
    /**
     * 出生年月日
     */
    @JSONField(ordinal = 8)
    private String birth;
    /**
     * 文化程度
     */
    @JSONField(ordinal = 9)
    private String degreeOfEducation;
    /**
     * 政治面貌
     */
    @JSONField(ordinal = 10)
    private String paliticsStatus;
    /**
     * 发展阶段
     */
    @JSONField(ordinal = 11)
    private String stageOfDevelopment;
    /**
     * 入学时间
     */
    @JSONField(ordinal = 12)
    private String admissionTime;
    /**
     * 宿舍号
     */
    @JSONField(ordinal = 13)
    private String dormitory;

    /**
     * 党支部
     */
    @JSONField(ordinal = 14)
    private String partyBranch;

    /**
     * 对应的申请人信息
     */
    @JSONField(ordinal = 15)
    private Applicant applicant;

    /**
     * 对应的积极分子信息
     */
    @JSONField(ordinal = 16)
    private Activist activist;

    /**
     * 对应的考察对象信息
     */
    @JSONField(ordinal = 17)
    private Inspector inspector;

    /**
     * 对应的发展对象信息
     */
    @JSONField(ordinal = 18)
    private Developer developer;

    /**
     * 对应的党员信息
     */
    @JSONField(ordinal = 19)
    private PartyMember partyMember;

    /**
     * 考试成绩
     */
    @JSONField(ordinal = 20)
    private List<Mark> markList;

    /**
     * 班级信息
     */
    @JSONField(ordinal = 21)
    private StudentClass studentClass;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getStudentNative() {
        return studentNative;
    }

    public void setStudentNative(String studentNative) {
        this.studentNative = studentNative;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getDegreeOfEducation() {
        return degreeOfEducation;
    }

    public void setDegreeOfEducation(String degreeOfEducation) {
        this.degreeOfEducation = degreeOfEducation;
    }

    public String getPaliticsStatus() {
        return paliticsStatus;
    }

    public void setPaliticsStatus(String paliticsStatus) {
        this.paliticsStatus = paliticsStatus;
    }

    public String getStageOfDevelopment() {
        return stageOfDevelopment;
    }

    public void setStageOfDevelopment(String stageOfDevelopment) {
        this.stageOfDevelopment = stageOfDevelopment;
    }

    public String getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Activist getActivist() {
        return activist;
    }

    public void setActivist(Activist activist) {
        this.activist = activist;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public PartyMember getPartyMember() {
        return partyMember;
    }

    public void setPartyMember(PartyMember partyMember) {
        this.partyMember = partyMember;
    }

    public List<Mark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<Mark> markList) {
        this.markList = markList;
    }

    public String getPartyBranch() {
        return partyBranch;
    }

    public void setPartyBranch(String partyBranch) {
        this.partyBranch = partyBranch;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "num=" + num +
                ", classNum='" + classNum + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", nation='" + nation + '\'' +
                ", studentNative='" + studentNative + '\'' +
                ", telephone='" + telephone + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", birth='" + birth + '\'' +
                ", degreeOfEducation='" + degreeOfEducation + '\'' +
                ", paliticsStatus='" + paliticsStatus + '\'' +
                ", stageOfDevelopment='" + stageOfDevelopment + '\'' +
                ", admissionTime='" + admissionTime + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", partyBranch='" + partyBranch + '\'' +
                ", applicant=" + applicant +
                ", activist=" + activist +
                ", inspector=" + inspector +
                ", developer=" + developer +
                ", partyMember=" + partyMember +
                ", markList=" + markList +
                ", studentClass=" + studentClass +
                '}';
    }
}
