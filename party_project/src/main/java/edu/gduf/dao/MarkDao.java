package edu.gduf.dao;

import edu.gduf.domain.Mark;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库mark表的访问接口
 * @author 古市
 * @date 2020-03-01 16:04
 **/
@Repository
public interface MarkDao {

    /**
     * 查询mark表中所有数据
     * @return activist表中数据的集合
     */
    List<Mark> findAll();

    /**
     * 通过学号查询相应的信息
     * @param num 学号
     * @return 返回成绩列表
     */
    List<Mark> findMarkByNum(String num);


    /**
     * 插入mark数据
     * @param marks mark数据列表
     * @return 插入记录总条数
     */
    int addMarkList(List<Mark> marks);

}
