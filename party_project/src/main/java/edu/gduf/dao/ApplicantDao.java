package edu.gduf.dao;

import edu.gduf.domain.Applicant;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库applicant表的访问接口
 * @author 古市
 * @date 2020-03-01 16:03
 **/
@Repository
public interface ApplicantDao {

    /**
     * 查询applicant表中所有信息
     * @return applicant表中数据的集合
     */
    List<Applicant> findAll();

    /**
     * 通过学号查询相应的信息
     * @param num 学号
     * @return 返回申请人
     */
    Applicant findApplicantByNum(String num);

    /**
     * 从student表中获取全部num数据
     * @return 学号列表
     */
    List<String> findAllNumsFromStudent();

    /**
     * 获取applicant表的主键,
     * @return 返回num列表
     */
    List<String> findAllNums();

    /**
     * 往applicant表中添加数据
     * @param applicantList applicant数据列表
     * @return 插入记录总数
     */
    int addApplicantList(List<Applicant> applicantList);

    /**
     * 更新student表中stageOfDevelopment的信息
     * @param applicantList 此次添加的applicant列表
     * @return 更新记录总数
     */
    int updateStage(List<Applicant> applicantList);
}
