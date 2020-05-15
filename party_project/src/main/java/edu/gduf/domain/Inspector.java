package edu.gduf.domain;

import java.io.Serializable;

/**
 * Demo Class
 * 考察对象 对象，
 * 对应数据库中的inspector表
 * @author 古市
 * @date 2020-03-01 15:13
 **/
public class Inspector implements Serializable {

    /**
     * 学号
     */
    private String num;
    /**
     * 党课成绩
     */
    private Double partyScore;
    /**
     * 党课培训时间
     */
    private String trainingTime;
    /**
     * 经历
     */
    private String experience;
    /**
     * 优点
     */
    private String advantage;
    /**
     * 缺点
     */
    private String disadvantage;
    /**
     * 获奖情况
     */
    private String award;

    /**
     * 考察对象现任职务
     */
    private String inspectorOccupation;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Double getPartyScore() {
        return partyScore;
    }

    public void setPartyScore(Double partyScore) {
        this.partyScore = partyScore;
    }

    public String getTrainingTime() {
        return trainingTime;
    }

    public void setTrainingTime(String trainingTime) {
        this.trainingTime = trainingTime;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAdvantage() {
        return advantage;
    }

    public void setAdvantage(String advantage) {
        this.advantage = advantage;
    }

    public String getDisadvantage() {
        return disadvantage;
    }

    public void setDisadvantage(String disadvantage) {
        this.disadvantage = disadvantage;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }


    public String getInspectorOccupation() {
        return inspectorOccupation;
    }

    public void setInspectorOccupation(String inspectorOccupation) {
        this.inspectorOccupation = inspectorOccupation;
    }

    @Override
    public String toString() {
        return "Inspector{" +
                "num='" + num + '\'' +
                ", partyScore=" + partyScore +
                ", trainingTime=" + trainingTime +
                ", experience='" + experience + '\'' +
                ", advantage='" + advantage + '\'' +
                ", disadvantage='" + disadvantage + '\'' +
                ", award='" + award + '\'' +
                ", inspectorOccupation='" + inspectorOccupation + '\'' +
                '}';
    }

}
