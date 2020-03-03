package edu.gduf.dao;

import edu.gduf.domain.PartyMember;

import java.util.List;

/**
 * Demo Class
 * 数据库party_member表的访问接口
 * @author 古市
 * @date 2020-03-01 16:04
 **/
public interface PartyMemberDao {

    /**
     * 查询party_member表中所有数据
     * @return party_member表中数据的集合
     */
    List<PartyMember> findAll();

}
