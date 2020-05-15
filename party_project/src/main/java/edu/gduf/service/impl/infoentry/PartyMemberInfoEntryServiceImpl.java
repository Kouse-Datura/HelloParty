package edu.gduf.service.impl.infoentry;

import edu.gduf.dao.PartyMemberDao;
import edu.gduf.domain.PartyMember;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.InfoEntryService;
import edu.gduf.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 * 数据录入有几种情况：
 * 1.第一次录入，全部数据列录入。
 * 2.第一次录入，除转正时间其他全部录入
 * 3.第二次录入，全部数据列录入
 * 3.第二次录入，录入学号、转正时间、是否为正式党员（推荐）
 * 出现一次主键冲突则默认是第二次录入，然后更新是判断属性是否为空，为空不更新，不为空则覆盖之前的操作
 * @author 古市
 * @date 2020-04-03 22:38
 **/
@Service
public class PartyMemberInfoEntryServiceImpl implements InfoEntryService {

    private PartyMemberDao partyMemberDao;

    @Autowired
    public void setPartyMemberDao(PartyMemberDao dao) {
        this.partyMemberDao = dao;
    }


    @Override
    public ResultInfo informationEntry(String filepath) {
        //解析文件
        List<List<String>> lists = PoiUtil.readFile(filepath);
        //解析数据列表
        List<PartyMember> partyMemberList = readList(lists);


        //获取party_member表中的主键列
        List<String> allNums =partyMemberDao.findAllNums();
        //获取developer表中的主键列
        List<String> allNumsFromDeveloper = partyMemberDao.findAllNumsFromDeveloper();

        boolean isUpdate = false;
        //判断是否存在外键，没有外键则条件不成立，停止录入
        for (PartyMember partyMember : partyMemberList) {
            String num = partyMember.getNum();
            if (!allNumsFromDeveloper.contains(num)){
                return ResultInfo.failInfo("请检查数据合理性："+num);
            }
            if (!isUpdate && allNums.contains(num)){
                isUpdate = true;
            }else if (isUpdate && !allNums.contains(num)){
                //确认为更新操作，却出现主键不存在
                return ResultInfo.failInfo("请检查数据合理性："+num);
            }
        }
        //插入数据条数
        int addNum = 0;
        //更新数据条数
        int updateNum = 0;
        //更新状态条数
        int updateStageNum = 0;
        //判断采用更新还是插入操作
        if (isUpdate){
            //更新操作
            updateNum = partyMemberDao.updatePartyMemberList(partyMemberList);
        }else {
            //插入操作
            addNum = partyMemberDao.addPartyMemberList(partyMemberList);
            //第一次插入数据，对student表的发展状态进行更新
            updateStageNum = partyMemberDao.updateStage(partyMemberList);
        }


        if (updateNum > 0){
            return ResultInfo.successInfo("成功更新"+updateNum+"条数据");
        }

        if (addNum > 0 && addNum != updateStageNum){
            return ResultInfo.warnInfo("warn_message:\n" +
                    "插入"+addNum+"条数据；更新"+updateStageNum+"条发展状态");
        }else {
            return ResultInfo.successInfo("成功录入"+addNum+"条数据");
        }
    }

    private List<PartyMember> readList(List<List<String>> lists){
        List<PartyMember> partyMembers = new ArrayList<>();
        for (List<String> list : lists) {
            PartyMember partyMember = new PartyMember();
            partyMember.setNum(list.get(0));
            partyMember.setApplicationNum(list.get(1));
            partyMember.setApplicationTime(list.get(2));
            partyMember.setEnterTime(list.get(3));
            partyMember.setPositiveTime(list.get(4));
            partyMember.setIsOfficial(Integer.parseInt(list.get(5)));
            partyMembers.add(partyMember);
        }
        return partyMembers;
    }
}
