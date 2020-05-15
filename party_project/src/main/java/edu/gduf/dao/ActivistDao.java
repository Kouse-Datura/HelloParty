package edu.gduf.dao;

import edu.gduf.domain.Activist;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库activist表的访问接口
 * @author 古市
 * @date 2020-03-01 16:02
 **/
@Repository
public interface ActivistDao {

    /**
     * 查询activist表中所有数据
     * @return activist表中数据的集合
     */
    List<Activist> findAll();

    /**
     * 通过学号查询相应的信息
     * @param num 学号
     * @return 返回积极分子
     */
    Activist findActivistByNum(String num);

    /**
     * 获取activist的主键列
     * @return
     */
    List<String> findAllNums();

    /**
     * 获取applicant的主键列
     * @return
     */
    List<String> findAllNumsFromApplicant();

    /**
     * 往activist表中添加数据
     * @param activistList 对象列表
     * @return 添加记录数量
     */
    int addActivistList(List<Activist> activistList);

    /**
     * 更新student表中stageOfDevelopment的信息
     * @param activistList 此次添加的activist列表
     * @return 更新记录总数
     */
    int updateStage(List<Activist> activistList);

}
