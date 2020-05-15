package edu.gduf.dao;

import edu.gduf.domain.Inspector;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库Inspector表的访问接口
 * @author 古市
 * @date 2020-03-01 16:03
 **/
@Repository
public interface InspectorDao {

    /**
     * 查询Inspector表中的所有数据
     * @return Inspector表中数据的集合
     */
    List<Inspector> findAll();

    /**
     * 通过学号查询相应的信息
     * @param num 学号
     * @return 返回考察对象
     */
    Inspector findInspectorByNum(String num);


    /**
     * 从activist表中获取所有的num
     * @return 学号列表
     */
    List<String> findAllNumsFromActivist();

    /**
     * 获取Inspector表的主键,
     * @return 返回num列表
     */
    List<String> findAllNums();

    /**
     * 往inspector表中添加数据
     * @param inspectorList inspector数据列表
     * @return 插入记录总数
     */
    int addInspectorList(List<Inspector> inspectorList);

    /**
     * 更新inspector表中的数据
     * @param inspectors 数据列表
     * @return
     */
    int updateInspectorList(List<Inspector> inspectors);


    /**
     * 更新student表中stageOfDevelopment的信息
     * @param inspectors 此次添加的inspector列表
     * @return 更新记录总数
     */
    int updateStage(List<Inspector> inspectors);
}
