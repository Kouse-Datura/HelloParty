package edu.gduf.dao;

import edu.gduf.domain.Activist;

import java.util.List;

/**
 * Demo Class
 * 数据库activist表的访问接口
 * @author 古市
 * @date 2020-03-01 16:02
 **/
public interface ActivistDao {

    /**
     * 查询activist表中所有数据
     * @return activist表中数据的集合
     */
    List<Activist> findAll();

}
