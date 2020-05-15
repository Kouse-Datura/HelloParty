package edu.gduf.service.impl.infoentry;

import edu.gduf.dao.DeveloperDao;
import edu.gduf.domain.Developer;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.InfoEntryService;
import edu.gduf.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-03 21:05
 **/
@Service
public class DeveloperInfoEntryImpl implements InfoEntryService {

    private DeveloperDao dao;

    @Autowired
    public void setDao(DeveloperDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultInfo informationEntry(String filepath) {
        //解析文件
        List<List<String>> lists = PoiUtil.readFile(filepath);
        //解析字符串列表,生成对象
        List<Developer> developers = readList(lists);

        //获取developer表中的主键列
        List<String> allNums = dao.findAllNums();
        //获取inspector表中的主键列
        List<String> allNumsFromInspector = dao.findAllNumsFromInspector();
        //判断是否存在录入数据重复，如果存在重复则录入失败
        for (Developer developer : developers) {
            String num = developer.getNum();
            if (allNums.contains(num) || !allNumsFromInspector.contains(num)){
                return ResultInfo.failInfo("请检查数据合理性："+num);
            }
        }

        //不存在主键冲突则进行数据录入
        int i = dao.addDeveloperList(developers);
        //更新student表中的状态
        int j = dao.updateStage(developers);

        if (i == j){
            return ResultInfo.successInfo("成功录入"+i+"条数据");
        }
        return ResultInfo.warnInfo("warn_message:\n" +
                "插入"+i+"条数据；更新"+j+"条发展状态");
    }

    private List<Developer> readList(List<List<String>> lists){
        List<Developer> list = new ArrayList<>();
        for (List<String> strings : lists) {
            Developer developer = new Developer();
            developer.setNum(strings.get(0));
            developer.setIdentifyingDeveloper(strings.get(1));
            developer.setProfession(strings.get(2));
            developer.setDadName(strings.get(3));
            developer.setDadIdentity(strings.get(4));
            developer.setDadStatus(strings.get(5));
            developer.setMomName(strings.get(6));
            developer.setMomIdentity(strings.get(7));
            developer.setMomStatus(strings.get(8));
            developer.setEnterLeagueTime(strings.get(9));
            developer.setIntroducer1(strings.get(10));
            developer.setIntroducer2(strings.get(11));
            list.add(developer);
        }
        return list;
    }
}
