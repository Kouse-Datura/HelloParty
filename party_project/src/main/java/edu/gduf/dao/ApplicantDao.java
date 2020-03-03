package edu.gduf.dao;

import edu.gduf.domain.Applicant;

import java.util.List;

/**
 * Demo Class
 * 数据库applicant表的访问接口
 * @author 古市
 * @date 2020-03-01 16:03
 **/
public interface ApplicantDao {

    /**
     * 查询applicant表中所有信息
     * @return applicant表中数据的集合
     */
    List<Applicant> findAll();

}
