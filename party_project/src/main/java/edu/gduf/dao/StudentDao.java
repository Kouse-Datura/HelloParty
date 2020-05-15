package edu.gduf.dao;

import edu.gduf.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库student表的访问接口
 * @author 古市
 * @date 2020-02-29 20:11
 **/
@Repository
public interface StudentDao {

    /**
     * 查询student表中所有数据
     * @return student表中数据的集合
     */
    List<Student> findAll();

    /**
     * 通过学号查询相应的信息
     * @param num 学号
     * @return 返回学生
     */
    Student findStudentByNum(String num);

    /**
     * 插入学生数据
     * @param studentList 学生数据列表
     * @return
     */
    int addStudentList(List<Student> studentList);

    /**
     * 获取学生表的主键
     * @return
     */
    List<Integer> findAllNums();


    /**
     * 查询学生的部分信息
     * @param list 学号列表
     * @return 学生对象列表
     */
    List<Student> findPartInfoByList(List<String> list);

}
