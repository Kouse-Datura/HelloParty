package edu.gduf.dao;

import edu.gduf.domain.StudentClass;

import java.util.List;

/**
 * Demo Class
 * 数据库student_class表的访问接口
 * @author 古市
 * @date 2020-03-01 11:06
 **/
public interface StudentClassDao {

    /**
     * 查询student_class表中所有信息
     * @return student_class表中数据的集合
     */
    List<StudentClass> findAll();

}
