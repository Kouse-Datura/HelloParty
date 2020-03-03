package edu.gduf.dao;

import edu.gduf.domain.Student;

import java.util.List;

/**
 * Demo Class
 * 数据库student表的访问接口
 * @author 古市
 * @date 2020-02-29 20:11
 **/
public interface StudentDao {

    /**
     * 查询student表中所有数据
     * @return student表中数据的集合
     */
    List<Student> findAll();

}
