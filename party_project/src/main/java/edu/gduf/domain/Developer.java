package edu.gduf.domain;

import java.util.Date;

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
    private Date identifyingDeveloper;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Date getIdentifyingDeveloper() {
        return identifyingDeveloper;
    }

    public void setIdentifyingDeveloper(Date identifyingDeveloper) {
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

    @Override
    public String toString() {
        return "Developer{" +
                "num='" + num + '\'' +
                ", identifyingDeveloper=" + identifyingDeveloper +
                ", profession='" + profession + '\'' +
                ", dadName='" + dadName + '\'' +
                ", dadIdentity='" + dadIdentity + '\'' +
                ", dadStatus='" + dadStatus + '\'' +
                ", momName='" + momName + '\'' +
                ", momIdentity='" + momIdentity + '\'' +
                ", momStatus='" + momStatus + '\'' +
                '}';
    }
}
