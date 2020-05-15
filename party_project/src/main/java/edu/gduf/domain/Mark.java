package edu.gduf.domain;

import java.io.Serializable;

/**
 * Demo Class
 * 成绩对象,
 * 对应数据库中的mark表
 * @author 古市
 * @date 2020-03-01 11:52
 **/
public class Mark implements Serializable {

    /*
     * 关于Mark对象,
     * 通过学号+所在学期查找数据库对应的数据,每个学期都有不同的成绩,
     * 学期数据格式：2019-2020-1、2019-2020-2、2019-2020
     */

    /**
     * 学号
     */
    private String num;
    /**
     * 所在学期
     */
    private String semester;
    /**
     * 纪检情况
     */
    private String disciplineInspection;
    /**
     * 综测分数
     */
    private Double compositeScore;
    /**
     * 综测排名
     */
    private Integer compositeRanking;
    /**
     * 学业平均分
     */
    private Double academicScore;
    /**
     * 平均分排名
     */
    private Integer academicRanking;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getDisciplineInspection() {
        return disciplineInspection;
    }

    public void setDisciplineInspection(String disciplineInspection) {
        this.disciplineInspection = disciplineInspection;
    }

    public Double getCompositeScore() {
        return compositeScore;
    }

    public void setCompositeScore(Double compositeScore) {
        this.compositeScore = compositeScore;
    }

    public Integer getCompositeRanking() {
        return compositeRanking;
    }

    public void setCompositeRanking(Integer compositeRanking) {
        this.compositeRanking = compositeRanking;
    }

    public Double getAcademicScore() {
        return academicScore;
    }

    public void setAcademicScore(Double academicScore) {
        this.academicScore = academicScore;
    }

    public Integer getAcademicRanking() {
        return academicRanking;
    }

    public void setAcademicRanking(Integer academicRanking) {
        this.academicRanking = academicRanking;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "num='" + num + '\'' +
                ", semester='" + semester + '\'' +
                ", disciplineInspection='" + disciplineInspection + '\'' +
                ", compositeScore=" + compositeScore +
                ", compositeRanking=" + compositeRanking +
                ", academicScore=" + academicScore +
                ", academicRanking=" + academicRanking +
                '}';
    }
}
