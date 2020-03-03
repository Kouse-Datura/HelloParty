package edu.gduf.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Demo Class
 * 入党申请人对象
 * 对应数据库中applicant表
 * @author 古市
 * @date 2020-03-01 12:20
 **/
public class Applicant implements Serializable {

    /**
     * 学号
     */
    private String num;
    /**
     * 入党申请时间
     */
    private Date timeOfApplication;
    /**
     * 谈话人
     */
    private String speaker;
    /**
     * 派人谈话时间
     */
    private Date talkTime;
    /**
     * 申请人现任职务
     */
    private String applicantOccupation;
    /**
     * 是否成年
     */
    private Integer isAdult;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getTimeOfApplication() {
        return timeOfApplication;
    }

    public void setTimeOfApplication(Date timeOfApplication) {
        this.timeOfApplication = timeOfApplication;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Date getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Date talkTime) {
        this.talkTime = talkTime;
    }

    public String getApplicantOccupation() {
        return applicantOccupation;
    }

    public void setApplicantOccupation(String applicantOccupation) {
        this.applicantOccupation = applicantOccupation;
    }

    public Integer getIsAdult() {
        return isAdult;
    }

    public void setIsAdult(Integer isAdult) {
        this.isAdult = isAdult;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "num='" + num + '\'' +
                ", timeOfApplication=" + timeOfApplication +
                ", speaker='" + speaker + '\'' +
                ", talkTime=" + talkTime +
                ", applicantOccupation='" + applicantOccupation + '\'' +
                ", isAdult=" + isAdult +
                '}';
    }
}
