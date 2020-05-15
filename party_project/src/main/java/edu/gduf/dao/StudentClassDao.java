package edu.gduf.dao;

import edu.gduf.domain.StudentClass;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库student_class表的访问接口
 * @author 古市
 * @date 2020-03-01 11:06
 **/
@Repository
public interface StudentClassDao {

    /**
     * 查询student_class表中所有信息
     * @return student_class表中数据的集合
     */
    List<StudentClass> findAll();

    /**
     * 通过班级号查询相应的班级信息
     * @param classNum 班级号
     * @return 学生班级对象
     */
    StudentClass findClassByNum(String classNum);

}
