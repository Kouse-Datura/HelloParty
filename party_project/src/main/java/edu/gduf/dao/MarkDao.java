package edu.gduf.dao;

import edu.gduf.domain.Mark;

import java.util.List;

/**
 * Demo Class
 * 数据库mark表的访问接口
 * @author 古市
 * @date 2020-03-01 16:04
 **/
public interface MarkDao {

    /**
     * 查询mark表中所有数据
     * @return activist表中数据的集合
     */
    List<Mark> findAll();

}
