package edu.gduf.service.impl;

import edu.gduf.dao.MarkDao;
import edu.gduf.domain.Mark;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.InfoEntryService;
import edu.gduf.utils.MyBatisUtil;
import edu.gduf.utils.PoiUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-04 11:24
 **/
public class MarkInfoEntryServiceImpl implements InfoEntryService {
    @Override
    public ResultInfo informationEntry(String filepath) {
        //解析文件
        List<List<String>> lists = PoiUtil.readFile(filepath);
        //解析数据列表
        List<Mark> marks = readList(lists);

        //获取session对象
        SqlSession session = MyBatisUtil.getFactory().openSession();
        //获取dao对象
        MarkDao dao = session.getMapper(MarkDao.class);

        int i = dao.addMarkList(marks);

        return ResultInfo.successInfo("成功录入"+ i +"条数据");
    }


    private List<Mark> readList(List<List<String>> lists){
        List<Mark> marks = new ArrayList<>();
        for (List<String> list : lists) {
            Mark mark = new Mark();
            for (String s : list) {
                System.out.print(s+" ");
            }
            System.out.println();
            mark.setNum(list.get(0));
            mark.setSemester(list.get(1));
            mark.setDisciplineInspection(list.get(2));
            mark.setCompositeScore(Double.parseDouble(list.get(3)));
            mark.setCompositeRanking(Integer.parseInt(list.get(4)));
            mark.setAcademicScore(Double.parseDouble(list.get(5)));
            mark.setAcademicRanking(Double.parseDouble(list.get(6)));
            marks.add(mark);
        }
        return marks;
    }

}
