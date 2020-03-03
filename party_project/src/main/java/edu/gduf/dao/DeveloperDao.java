package edu.gduf.dao;

import edu.gduf.domain.Developer;

import java.util.List;

/**
 * Demo Class
 * 数据库developer表的访问接口
 * @author 古市
 * @date 2020-03-01 16:03
 **/
public interface DeveloperDao {

    /**
     * 查询developer表中所有数据
     * @return developer表中数据的集合
     */
    List<Developer> findAll();

}
