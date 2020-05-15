package edu.gduf.domain;

import java.io.Serializable;

/**
 * Demo Class
 * 党员对象
 * 对应数据库中的party_member表
 * @author 古市
 * @String 2020-03-01 15:55
 **/
public class PartyMember implements Serializable {

    /**
     * 学号
     */
    private String num;
    /**
     * 志愿书编号
     */
    private String applicationNum;
    /**
     * 志愿书填写时间
     */
    private String applicationTime;
    /**
     * 入党时间
     */
    private String enterTime;
    /**
     * 转正时间
     */
    private String positiveTime;
    /**
     * 是否为正式党员
     */
    private Integer isOfficial;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getApplicationNum() {
        return applicationNum;
    }

    public void setApplicationNum(String applicationNum) {
        this.applicationNum = applicationNum;
    }

    public String getApplicationTime() {
        return applicationTime;
    }

    public void setApplicationTime(String applicationTime) {
        this.applicationTime = applicationTime;
    }

    public String getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(String enterTime) {
        this.enterTime = enterTime;
    }

    public String getPositiveTime() {
        return positiveTime;
    }

    public void setPositiveTime(String positiveTime) {
        this.positiveTime = positiveTime;
    }

    public Integer getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Integer isOfficial) {
        this.isOfficial = isOfficial;
    }

    @Override
    public String toString() {
        return "PartyMember{" +
                "num='" + num + '\'' +
                ", applicationNum='" + applicationNum + '\'' +
                ", applicationTime=" + applicationTime +
                ", enterTime=" + enterTime +
                ", positiveTime=" + positiveTime +
                ", isOfficial=" + isOfficial +
                '}';
    }
}
