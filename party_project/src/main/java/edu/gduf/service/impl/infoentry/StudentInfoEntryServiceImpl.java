package edu.gduf.service.impl.infoentry;

import edu.gduf.dao.StudentDao;
import edu.gduf.domain.ResultInfo;
import edu.gduf.domain.Student;
import edu.gduf.service.InfoEntryService;
import edu.gduf.utils.PoiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 * student表信息录入业务类
 * @author 古市
 * @date 2020-03-28 11:15
 **/
@Service
public class StudentInfoEntryServiceImpl implements InfoEntryService {

    private StudentDao studentDao;

    @Autowired
    public void setStudentDao(StudentDao dao) {
        this.studentDao = dao;
    }


    @Override
    public ResultInfo informationEntry(String filepath) {
        //获取文件解析后返回的列表
        List<List<String>> lists = PoiUtil.readFile(filepath);

        //解析列表生成student对象列表
        List<Student> studentList = readList(lists);



        //判断是否存在重复主键
        List<Integer> allNum = studentDao.findAllNums();
        for (Student student : studentList) {
            if (allNum.contains(student.getNum())){
                return ResultInfo.failInfo("存在重复的学号" + student.getNum() + ",请进行检查");
            }
        }

        int i = studentDao.addStudentList(studentList);

        return ResultInfo.successInfo("成功录入"+ i +"条数据");
    }

    private List<Student> readList(List<List<String>> lists){
        List<Student> studentList = new ArrayList<>();
        for (List<String> list : lists) {
            Student student = new Student();
            //设置student对象的值
            student.setNum(list.get(0));
            student.setClassNum(list.get(1));
            student.setName(list.get(2));
            student.setSex(list.get(3));
            student.setNation(list.get(4));
            student.setStudentNative(list.get(5));
            student.setTelephone(list.get(6));
            student.setIdentityCard(list.get(7));
            student.setBirth(list.get(8));
            student.setDegreeOfEducation(list.get(9));
            student.setPaliticsStatus(list.get(10));
            student.setStageOfDevelopment(list.get(11));
            student.setAdmissionTime(list.get(12));
            student.setDormitory(list.get(13));
            student.setPartyBranch(list.get(14));
            studentList.add(student);
        }
        return studentList;
    }

}
