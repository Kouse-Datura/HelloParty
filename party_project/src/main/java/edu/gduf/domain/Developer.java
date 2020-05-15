package edu.gduf.domain;

/**
 * Demo Class
 * 发展对象 对象
 * 对应数据库中的developer表
 * @author 古市
 * @date 2020-03-01 15:43
 **/
public class Developer {

    /**
     * 学号
     */
    private String num;
    /**
     * 确定为发展对象时间
     */
    private String identifyingDeveloper;
    /**
     * 专业
     */
    private String profession;
    /**
     * 父亲姓名
     */
    private String dadName;
    /**
     * 父亲身份证
     */
    private String dadIdentity;
    /**
     * 父亲政治面貌
     */
    private String dadStatus;
    /**
     * 母亲姓名
     */
    private String momName;
    /**
     * 母亲身份证
     */
    private String momIdentity;
    /**
     * 母亲政治面貌
     */
    private String momStatus;
    /**
     * 入团时间
     */
    private String enterLeagueTime;
    /**
     * 入党介绍人1
     */
    private String introducer1;
    /**
     * 入党介绍人2
     */
    private String introducer2;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIdentifyingDeveloper() {
        return identifyingDeveloper;
    }

    public void setIdentifyingDeveloper(String identifyingDeveloper) {
        this.identifyingDeveloper = identifyingDeveloper;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getDadName() {
        return dadName;
    }

    public void setDadName(String dadName) {
        this.dadName = dadName;
    }

    public String getDadIdentity() {
        return dadIdentity;
    }

    public void setDadIdentity(String dadIdentity) {
        this.dadIdentity = dadIdentity;
    }

    public String getDadStatus() {
        return dadStatus;
    }

    public void setDadStatus(String dadStatus) {
        this.dadStatus = dadStatus;
    }

    public String getMomName() {
        return momName;
    }

    public void setMomName(String momName) {
        this.momName = momName;
    }

    public String getMomIdentity() {
        return momIdentity;
    }

    public void setMomIdentity(String momIdentity) {
        this.momIdentity = momIdentity;
    }

    public String getMomStatus() {
        return momStatus;
    }

    public void setMomStatus(String momStatus) {
        this.momStatus = momStatus;
    }

    public String getEnterLeagueTime() {
        return enterLeagueTime;
    }

    public void setEnterLeagueTime(String enterLeagueTime) {
        this.enterLeagueTime = enterLeagueTime;
    }

    public String getIntroducer1() {
        return introducer1;
    }

    public void setIntroducer1(String introducer1) {
        this.introducer1 = introducer1;
    }

    public String getIntroducer2() {
        return introducer2;
    }

    public void setIntroducer2(String introducer2) {
        this.introducer2 = introducer2;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "num='" + num + '\'' +
                ", identifyingDeveloper='" + identifyingDeveloper + '\'' +
                ", profession='" + profession + '\'' +
                ", dadName='" + dadName + '\'' +
                ", dadIdentity='" + dadIdentity + '\'' +
                ", dadStatus='" + dadStatus + '\'' +
                ", momName='" + momName + '\'' +
                ", momIdentity='" + momIdentity + '\'' +
                ", momStatus='" + momStatus + '\'' +
                ", enterLeagueTime='" + enterLeagueTime + '\'' +
                ", introducer1='" + introducer1 + '\'' +
                ", introducer2='" + introducer2 + '\'' +
                '}';
    }
}
