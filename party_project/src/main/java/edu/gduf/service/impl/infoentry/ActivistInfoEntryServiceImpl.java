package edu.gduf.service.impl.infoentry;

import edu.gduf.dao.ActivistDao;
import edu.gduf.domain.Activist;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.InfoEntryService;
import edu.gduf.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 * activist表信息录入业务类
 * @author 古市
 * @date 2020-03-29 19:05
 **/
@Repository
public class ActivistInfoEntryServiceImpl implements InfoEntryService {


    private ActivistDao dao;

    @Autowired
    public void setDao(ActivistDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultInfo informationEntry(String filepath) {
        //获取文件的数据列表
        List<List<String>> lists = PoiUtil.readFile(filepath);
        //解析数据列表生成对象列表
        List<Activist> activistList = readList(lists);
        //从activist表中获取主键列num，防止主键冲突
        List<String> activistNums = dao.findAllNums();
        //从applicant表中获取主键列，防止外键冲突
        List<String> applicantNums = dao.findAllNumsFromApplicant();
        for (Activist activist : activistList) {
            String num = activist.getNum();
            if (activistNums.contains(num) || !applicantNums.contains(num)){
                return ResultInfo.failInfo("请检查数据合理性："+num);
            }
        }

        //往数据库中添加数据
        int i = dao.addActivistList(activistList);
        //更新student表中发展阶段的状态
        int j = dao.updateStage(activistList);

        if (i == j){
            return ResultInfo.successInfo("成功录入"+i+"条数据");
        }
        return ResultInfo.warnInfo("warn_message:\n" +
                "插入"+i+"条数据；更新"+j+"条发展状态");
    }

    private List<Activist> readList(List<List<String>> lists){
        List<Activist> activistList = new ArrayList<>();
        for (List<String> list : lists) {
            Activist activist = new Activist();
            activist.setNum(list.get(0));
            activist.setActivistOccupation(list.get(1));
            activist.setIdentifyingActivist(list.get(2));
            activist.setDirectingScore(Double.parseDouble(list.get(3)));
            activist.setCompositeRanking(Integer.parseInt(list.get(4)));
            activist.setAgree(Integer.parseInt(list.get(5)));
            activist.setDisagree(Integer.parseInt(list.get(6)));
            activist.setAbstain(Integer.parseInt(list.get(7)));
            activist.setThinkingReport(Integer.parseInt(list.get(8)));
            activist.setNeedNum(Integer.parseInt(list.get(9)));
            activist.setAttendNum(Integer.parseInt(list.get(10)));
            activist.setSuperintendent1(list.get(11));
            activist.setSuperintendent2(list.get(12));
            activist.setCultivateContacts(list.get(13));
            activist.setWhichVolume(Integer.parseInt(list.get(14)));
            activist.setInspectTime(list.get(15));
            activistList.add(activist);
        }
        return activistList;
    }


}
