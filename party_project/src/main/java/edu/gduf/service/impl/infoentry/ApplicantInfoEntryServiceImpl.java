package edu.gduf.service.impl.infoentry;

import edu.gduf.dao.ApplicantDao;
import edu.gduf.domain.Applicant;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.InfoEntryService;
import edu.gduf.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 * applicant表信息录入业务类
 * @author 古市
 * @date 2020-03-29 19:25
 **/
@Service
public class ApplicantInfoEntryServiceImpl implements InfoEntryService {

    private ApplicantDao dao;

    @Autowired
    public void setDao(ApplicantDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultInfo informationEntry(String filepath) {
        //获取文件的数据列表
        List<List<String>> lists = PoiUtil.readFile(filepath);
        //解析数据列表生成对象列表
        List<Applicant> applicantList = readList(lists);

        //从applicant表中获取num列数据，判断是否存在主键冲突，如果冲突停止录入，返回false
        List<String> applicantNums = dao.findAllNums();
        //从student表中获取主键，判断是否已经存在该学生的基本信息，如果不存在返回false，录入失败
        List<String> studentNums = dao.findAllNumsFromStudent();
        for (Applicant applicant : applicantList) {
            String num = applicant.getNum();
            if (applicantNums.contains(num) || !studentNums.contains(num)){
                return ResultInfo.failInfo("请检查数据合理性："+num);
            }
        }
        //经过判断不会出现问题之后，进行插入操作
        int i = dao.addApplicantList(applicantList);

        //更新student表中的发展阶段
        int j = dao.updateStage(applicantList);

        if (i == j){
            return ResultInfo.successInfo("成功录入"+i+"条数据");
        }
        return ResultInfo.warnInfo("warn_message:\n" +
                "插入"+i+"条数据；更新"+j+"条发展状态");
    }

    private List<Applicant> readList(List<List<String>> lists){
        List<Applicant> applicantList = new ArrayList<>();
        for (List<String> list : lists) {
            Applicant applicant = new Applicant();
            applicant.setNum(list.get(0));
            applicant.setTimeOfApplication(list.get(1));
            applicant.setSpeaker(list.get(2));
            applicant.setTalkTime(list.get(3));
            applicant.setApplicantOccupation(list.get(4));
            applicant.setIsAdult(Integer.parseInt(list.get(5)));
            applicantList.add(applicant);
        }
        return applicantList;
    }



}
