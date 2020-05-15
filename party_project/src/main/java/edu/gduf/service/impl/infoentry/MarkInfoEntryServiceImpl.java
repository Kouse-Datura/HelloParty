package edu.gduf.service.impl.infoentry;

import edu.gduf.dao.MarkDao;
import edu.gduf.domain.Mark;
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
 * @date 2020-04-04 11:24
 **/
@Service
public class MarkInfoEntryServiceImpl implements InfoEntryService {

    private MarkDao dao;

    @Autowired
    public void setDao(MarkDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultInfo informationEntry(String filepath) {
        //解析文件
        List<List<String>> lists = PoiUtil.readFile(filepath);
        //解析数据列表
        List<Mark> marks = readList(lists);
        int i = dao.addMarkList(marks);
        return ResultInfo.successInfo("成功录入"+ i +"条数据");
    }


    private List<Mark> readList(List<List<String>> lists){
        List<Mark> marks = new ArrayList<>();
        for (List<String> list : lists) {
            Mark mark = new Mark();
            mark.setNum(list.get(0));
            mark.setSemester(list.get(1));
            if ("无".equals(list.get(2))){
                mark.setDisciplineInspection("");
            }else{
                mark.setDisciplineInspection(list.get(2));
            }
            mark.setCompositeScore(Double.parseDouble(list.get(3)));
            mark.setCompositeRanking(Integer.parseInt(list.get(4)));
            mark.setAcademicScore(Double.parseDouble(list.get(5)));
            mark.setAcademicRanking(Integer.parseInt(list.get(6)));
            marks.add(mark);
        }
        return marks;
    }

}
