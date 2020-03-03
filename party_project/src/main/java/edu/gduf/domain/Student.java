package edu.gduf.domain;

import javax.swing.*;
import java.io.Serializable;
import java.util.Date;

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
    private Integer num;
    /**
     * 班级号
     */
    private String classNum;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 民族
     */
    private String nation;
    /**
     * 籍贯
     */
    private String studentNative;
    /**
     * 联系方式
     */
    private String telephone;
    /**
     * 身份证号
     */
    private String identityCard;
    /**
     * 出生年月日
     */
    private Date birth;
    /**
     * 文化程度
     */
    private String degreeOfEducation;
    /**
     * 政治面貌
     */
    private String paliticsStatus;
    /**
     * 发展阶段
     */
    private String stageOfDevelopment;
    /**
     * 入学时间
     */
    private Date admissionTime;
    /**
     * 宿舍号
     */
    private String dormitory;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
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

    public Date getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(Date admissionTime) {
        this.admissionTime = admissionTime;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
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
                ", birth=" + birth +
                ", degreeOfEducation='" + degreeOfEducation + '\'' +
                ", paliticsStatus='" + paliticsStatus + '\'' +
                ", stageOfDevelopment='" + stageOfDevelopment + '\'' +
                ", admissionTime=" + admissionTime +
                ", dormitory='" + dormitory + '\'' +
                '}';
    }
}
