package edu.gduf.dao;

import edu.gduf.domain.Developer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库developer表的访问接口
 * @author 古市
 * @date 2020-03-01 16:03
 **/
@Repository
public interface DeveloperDao {

    /**
     * 查询developer表中所有数据
     * @return developer表中数据的集合
     */
    List<Developer> findAll();

    /**
     * 通过学号查询相应的信息
     * @param num 学号
     * @return 返回发展对象
     */
    Developer findDeveloperByNum(String num);

    /**
     * 从developer表中获取num列
     * @return 学号列表
     */
    List<String> findAllNums();

    /**
     * 从inspector表中获取num列
     * @return 学号列表
     */
    List<String> findAllNumsFromInspector();

    /**
     * 添加数据
     * @param developers 对象列表
     * @return 添加数据的行数
     */
    int addDeveloperList(List<Developer> developers);


    /**
     * 更新student表中stageOfDevelopment的信息
     * @param developers 此次添加的developer列表
     * @return 更新记录总数
     */
    int updateStage(List<Developer> developers);

}
