package edu.gduf.dao;

import edu.gduf.domain.PartyMember;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Demo Class
 * 数据库party_member表的访问接口
 * @author 古市
 * @date 2020-03-01 16:04
 **/
@Repository
public interface PartyMemberDao {

    /**
     * 查询party_member表中所有数据
     * @return party_member表中数据的集合
     */
    List<PartyMember> findAll();

    /**
     * 通过学号查询相应的信息
     * @param num 学号
     * @return 返回党员信息
     */
    PartyMember findPartyMemberByNum(String num);


    /**
     * 更新student表中的数据
     * @param partyMembers 本次更新的列表
     * @return 更新的条数
     */
    int updateStage(List<PartyMember> partyMembers);


    /**
     * 查找party_member表中的主键列
     * @return 主键列数据列表
     */
    List<String> findAllNums();

    /**
     * 从developer表中获取主键列
     * @return 主键列数据列表
     */
    List<String> findAllNumsFromDeveloper();


    /**
     * 更新党员数据
     * @param partyMembers 党员数据列表
     * @return 更新记录总数
     */
    int updatePartyMemberList(List<PartyMember> partyMembers);

    /**
     * 加入党员数据
     * @param partyMembers 党员数据列表
     * @return 插入记录总数
     */
    int addPartyMemberList(List<PartyMember> partyMembers);


}
