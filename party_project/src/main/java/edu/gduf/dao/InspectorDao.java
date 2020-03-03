package edu.gduf.dao;

import edu.gduf.domain.Inspector;

import java.util.List;

/**
 * Demo Class
 * 数据库Inspector表的访问接口
 * @author 古市
 * @date 2020-03-01 16:03
 **/
public interface InspectorDao {

    /**
     * 查询Inspector表中的所有数据
     * @return Inspector表中数据的集合
     */
    List<Inspector> findAll();

}
