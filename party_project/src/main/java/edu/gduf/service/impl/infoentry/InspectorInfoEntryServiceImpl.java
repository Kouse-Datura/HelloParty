package edu.gduf.service.impl;

import edu.gduf.dao.InspectorDao;
import edu.gduf.domain.Inspector;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.InfoEntryService;
import edu.gduf.utils.MyBatisUtil;
import edu.gduf.utils.PoiUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 * inspector表信息录入业务类
 * 因为inspector表可能需要分两次录入，所以需要对传进来的数据进行判断
 * 共分三种情况
 * 1.只有前三项数据，学号，党课成绩，党课培训时间，则为第一种情况
 * 2.除了党课成绩，党课培训时间，其他全部存在，则为第二种情况
 * 3.全部数据都存在，则为第三种情况
 * 如果为第一种情况，dao使用insert
 * 如果为第二种情况，dao使用update
 * 如果为第三种情况，dao使用insert，默认为插入新的数据
 *
 * insert则需要判断当前表是否冲突
 * update需要判断当前表是否存在主键
 *
 * @author 古市
 * @date 2020-04-01 22:05
 **/
public class InspectorInfoEntryServiceImpl implements InfoEntryService {

    int updateStageNum = 0;

    @Override
    public ResultInfo informationEntry(String filepath) {
        //获取文件中的数据列表
        List<List<String>> lists = PoiUtil.readFile(filepath);
        //解析数据列表
        List<Inspector> inspectorList = readList(lists);
        //获取session对象
        SqlSession session = MyBatisUtil.getFactory().openSession();
        //获取dao对象
        InspectorDao dao = session.getMapper(InspectorDao.class);
        //查找activist中的nums列，确保外键存在
        List<String> numsFromActivist = dao.findAllNumsFromActivist();
        for (Inspector inspector : inspectorList) {
            if (!numsFromActivist.contains(inspector.getNum())){
                return ResultInfo.failInfo("请检查数据合理性："+inspector.getNum());
            }
        }
        List<String> allNums = dao.findAllNums();
        //插入数据条数
        int addNum = 0;
        //更新数据条数
        int updateNum = 0;
        //判断为哪个阶段
        String trainingTime = inspectorList.get(0).getTrainingTime();
        String experience = inspectorList.get(0).getExperience();
        if ("".equals(trainingTime)){
            //第二种情况
            //判断num主键是否存在，如果不存在，返回false
            for (Inspector inspector : inspectorList) {
                if (!allNums.contains(inspector.getNum())){
                    return ResultInfo.failInfo("请检查数据合理性："+inspector.getNum());
                }
            }
            //执行更新操作
            updateNum = updateInspectorList(inspectorList, dao);
        }else if ("".equals(experience)){
            //第一种情况
            //判断主键是否存在，如果存在，主键冲突，返回false
            for (Inspector inspector : inspectorList) {
                if (allNums.contains(inspector.getNum())){
                    return ResultInfo.failInfo("请检查数据合理性："+inspector.getNum());
                }
            }
            //执行插入操作
            addNum = addInspectorList(inspectorList, dao);
        }else {
            //第三种情况
            //判断主键是否存在，如果存在，使用update，不存在使用insert
            //如果有一个nums存在，则默认整个表的num都已经存在
            //所以录入的数据需要规范
            boolean judge = false;
            for (Inspector inspector : inspectorList) {
                if (allNums.contains(inspector.getNum())){
                    judge = true;
                    break;
                }
            }
            if (judge){
                //存在,使用update
                updateNum = updateInspectorList(inspectorList, dao);
            }else {
                //不存在,使用insert
                addNum = addInspectorList(inspectorList, dao);
            }
        }

        //提交事务
        session.commit();
        session.close();

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

    private int updateInspectorList(List<Inspector> inspectors, InspectorDao dao){
        return dao.updateInspectorList(inspectors);
    }


    private int addInspectorList(List<Inspector> inspectors, InspectorDao dao){
        int i = dao.addInspectorList(inspectors);
        //执行完插入操作后对student表的发展阶段进行更新
        updateStageNum = dao.updateStage(inspectors);
        return i;
    }


    private List<Inspector> readList(List<List<String>> lists){
        List<Inspector> inspectorList = new ArrayList<>();
        for (List<String> list : lists) {
            Inspector inspector = new Inspector();
            inspector.setNum(list.get(0));
            String s1 = list.get(1);
            if (!"".equals(s1)){
                //如果不是空字符串则set，否则不进行赋值
                inspector.setPartyScore(Double.parseDouble(s1));
            }
            inspector.setTrainingTime(list.get(2));
            inspector.setExperience(list.get(3));
            inspector.setAdvantage(list.get(4));
            inspector.setDisadvantage(list.get(5));
            inspector.setAward(list.get(6));

            String s2 = list.get(7);
            if (!"".equals(s2)){
                //如果不是空字符串则set，否则不进行赋值
                inspector.setCompetitiveScore(Double.parseDouble(s2));
            }

            inspector.setInspectorOccupation(list.get(8));
            inspectorList.add(inspector);
        }
        return inspectorList;
    }

}
