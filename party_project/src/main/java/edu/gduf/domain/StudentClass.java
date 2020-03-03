package edu.gduf.domain;

import java.io.Serializable;

/**
 * Demo Class
 * 班级对象
 * 对应数据库中student_class表
 * 用于存储班级信息
 * @author 古市
 * @date 2020-03-01 10:39
 **/
public class StudentClass implements Serializable {

    /**
     * 班级号
     */
    private String classNum;
    /**
     * 党员人数
     */
    private Integer partyMembers;
    /**
     * 班级人数
     */
    private Integer classMembers;


    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    public Integer getPartyMembers() {
        return partyMembers;
    }

    public void setPartyMembers(Integer partyMembers) {
        this.partyMembers = partyMembers;
    }

    public Integer getClassMembers() {
        return classMembers;
    }

    public void setClassMembers(Integer classMembers) {
        this.classMembers = classMembers;
    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "classNum='" + classNum + '\'' +
                ", partyMembers=" + partyMembers +
                ", classMembers=" + classMembers +
                '}';
    }
}
