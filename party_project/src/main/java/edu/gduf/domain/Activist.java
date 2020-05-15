package edu.gduf.domain;

import java.io.Serializable;

/**
 * Demo Class
 * 入党积极分子对象,
 * 对应数据库中activist表
 * @author 古市
 * @date 2020-03-01 14:42
 **/
public class Activist implements Serializable {

    /**
     * 学号
     */
    private String num;
    /**
     * 积极分子现任职务
     */
    private String activistOccupation;
    /**
     * 确定积极分子时间
     */
    private String identifyingActivist;
    /**
     * 推优成绩
     */
    private Double directingScore;
    /**
     * 推优综测排名
     */
    private Integer compositeRanking;
    /**
     * 赞成票
     */
    private Integer agree;
    /**
     * 反对票
     */
    private Integer disagree;
    /**
     * 弃权票
     */
    private Integer abstain;
    /**
     * 思想汇报
     */
    private Integer thinkingReport;
    /**
     * 应到人数
     */
    private Integer needNum;
    /**
     * 实到人数
     */
    private Integer attendNum;
    /**
     * 监督人1
     */
    private String superintendent1;
    /**
     * 监督人2
     */
    private String superintendent2;
    /**
     * 培养联系人
     */
    private String cultivateContacts;
    /**
     * 第几期积极分子
     */
    private Integer whichVolume;
    /**
     * 考察期
     */
    private String inspectTime;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getActivistOccupation() {
        return activistOccupation;
    }

    public void setActivistOccupation(String activistOccupation) {
        this.activistOccupation = activistOccupation;
    }

    public String getIdentifyingActivist() {
        return identifyingActivist;
    }

    public void setIdentifyingActivist(String identifyingActivist) {
        this.identifyingActivist = identifyingActivist;
    }

    public Double getDirectingScore() {
        return directingScore;
    }

    public void setDirectingScore(Double directingScore) {
        this.directingScore = directingScore;
    }

    public Integer getCompositeRanking() {
        return compositeRanking;
    }

    public void setCompositeRanking(Integer compositeRanking) {
        this.compositeRanking = compositeRanking;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getDisagree() {
        return disagree;
    }

    public void setDisagree(Integer disagree) {
        this.disagree = disagree;
    }

    public Integer getAbstain() {
        return abstain;
    }

    public void setAbstain(Integer abstain) {
        this.abstain = abstain;
    }

    public Integer getThinkingReport() {
        return thinkingReport;
    }

    public void setThinkingReport(Integer thinkingReport) {
        this.thinkingReport = thinkingReport;
    }

    public Integer getNeedNum() {
        return needNum;
    }

    public void setNeedNum(Integer needNum) {
        this.needNum = needNum;
    }

    public Integer getAttendNum() {
        return attendNum;
    }

    public void setAttendNum(Integer attendNum) {
        this.attendNum = attendNum;
    }

    public String getSuperintendent1() {
        return superintendent1;
    }

    public void setSuperintendent1(String superintendent1) {
        this.superintendent1 = superintendent1;
    }

    public String getSuperintendent2() {
        return superintendent2;
    }

    public void setSuperintendent2(String superintendent2) {
        this.superintendent2 = superintendent2;
    }

    public String getCultivateContacts() {
        return cultivateContacts;
    }

    public void setCultivateContacts(String cultivateContacts) {
        this.cultivateContacts = cultivateContacts;
    }

    public Integer getWhichVolume() {
        return whichVolume;
    }

    public void setWhichVolume(Integer whichVolume) {
        this.whichVolume = whichVolume;
    }

    public String getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(String inspectTime) {
        this.inspectTime = inspectTime;
    }

    @Override
    public String toString() {
        return "Activist{" +
                "num='" + num + '\'' +
                ", activistOccupation='" + activistOccupation + '\'' +
                ", identifyingActivist=" + identifyingActivist +
                ", directingScore=" + directingScore +
                ", compositeRanking=" + compositeRanking +
                ", agree=" + agree +
                ", disagree=" + disagree +
                ", abstain=" + abstain +
                ", thinkingReport=" + thinkingReport +
                ", needNum=" + needNum +
                ", attendNum=" + attendNum +
                ", superintendent1='" + superintendent1 + '\'' +
                ", superintendent2='" + superintendent2 + '\'' +
                ", cultivateContacts='" + cultivateContacts + '\'' +
                ", whichVolume=" + whichVolume +
                ", inspectTime='" + inspectTime + '\'' +
                '}';
    }

}
