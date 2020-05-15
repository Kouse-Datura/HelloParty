package edu.gduf.service.impl.publicity;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import edu.gduf.dao.DeveloperDao;
import edu.gduf.dao.StudentDao;
import edu.gduf.domain.Inspector;
import edu.gduf.domain.ResultInfo;
import edu.gduf.domain.Student;
import edu.gduf.service.FileGenerateService;
import edu.gduf.utils.DataUtil;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Demo Class
 * 第四榜公示生成业务类
 * @author 古市
 * @date 2020-04-25 19:36
 **/
@Service
public class FourthPublicityGenerateServiceImpl implements FileGenerateService {

    private StudentDao studentDao;

    private DeveloperDao developerDao;

    /**
     * 文件生成的当前日期
     */
    private String currentDate;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Autowired
    public void setDeveloperDao(DeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    @Override
    public ResultInfo fileGenerate(List<String> list) {

        //判断inspector表中是否存在该学生，如果没有则执行失败
        List<String> nums = developerDao.findAllNums();
        for (String s : list) {
            if (!nums.contains(s)){
                return ResultInfo.failInfo("发展对象中不存在学生："+s+",请检查名单是否合理合理性。");
            }
        }

        //获取生成公示的时间
        currentDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

        //获取数据列表
        String[][] data = DataUtil.listTransfer(getInformation(list));
        //生成文件
        String filepath = null;
        try {
            filepath = generate(data);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return ResultInfo.successInfo("生成第四榜公示成功，共"+data.length+"个拟发展对象", filepath);
    }

    private String generate(String[][] data) throws IOException{
        //加载模板文件
        Workbook workbook = new Workbook();
        InputStream in = Resources.getResourceAsStream("model/publicity/fourth_publicity_model.xlsx");
        workbook.loadFromStream(in);
        //获取工作表
        Worksheet worksheet = workbook.getWorksheets().get(0);

        //插入数据
        worksheet.insertArray(data, 4, 2);
        //修改落款时间
        worksheet.findString("time", true, true).setText(currentDate);

        //删除多余的空白行
        //公示生成的最大容量是一百人，如果超过有点麻烦
        worksheet.deleteRow(4 + data.length, 100-data.length);

        //生成文件
        String filePath = this.getClass().getResource("/").getPath()
                +"output/publicity/"+ UUID.randomUUID().toString().replace("-", "") +".xlsx";
        File file = new File(filePath);
        //获取父目录
        File parent = file.getParentFile();
        if (!parent.exists()){
            //如果不存在就创建
            parent.mkdirs();
        }
        file.createNewFile();

        //获取输出流保存文件
        OutputStream out = new FileOutputStream(file);
        workbook.saveToStream(out);

        //释放资源
        in.close();
        out.close();

        return filePath;
    }

    @Override
    public List<List<String>> getInformation(List<String> list) {
        //创建结果集
        List<List<String>> result = new ArrayList<>();
        //遍历学号，获取student对象，抽取所需数据
        for (String s : list) {
            Student student = studentDao.findStudentByNum(s);
            List<String> studentData = new ArrayList<>();
            studentData.add(student.getName());
            studentData.add(student.getSex());
            studentData.add(student.getNation());
            studentData.add(student.getStudentNative());
            String[] birth = student.getBirth().split("-");
            studentData.add(birth[0]+"年"+birth[1]+"月");
            studentData.add(student.getDegreeOfEducation());
            studentData.add(student.getPaliticsStatus());
            studentData.add(student.getClassNum());
            Inspector inspector = student.getInspector();
            studentData.add(inspector.getInspectorOccupation());
            studentData.add(student.getApplicant().getTimeOfApplication());
            studentData.add(inspector.getTrainingTime());
            studentData.add(String.valueOf(inspector.getPartyScore()));
            //考察时间
            String[] split = student.getActivist().getIdentifyingActivist().split("-");
            String identifyTime = split[0]+"年"+split[1]+"月"+split[2]+"日";
            //考察的结束时间用的是当前系统的时间，所以的话要在文件生成之后修改一下
            studentData.add(identifyTime+"至"+currentDate);
            //政审情况
            studentData.add("通过");
            studentData.add(student.getDeveloper().getIntroducer1());
            studentData.add(currentDate);
            result.add(studentData);
        }

        return result;
    }

}
