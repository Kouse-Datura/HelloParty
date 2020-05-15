package edu.gduf.service.impl.publicity;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import edu.gduf.dao.StudentDao;
import edu.gduf.domain.Applicant;
import edu.gduf.domain.ResultInfo;
import edu.gduf.domain.Student;
import edu.gduf.service.FileGenerateService;
import edu.gduf.utils.DataUtil;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-27 22:24
 **/
@Service
public class FirstPublicityGenerateServiceImpl implements FileGenerateService {

    private StudentDao studentDao;

    /**
     * 文件表头
     */
    private String title = null;

    /**
     * 文件生成的当前日期
     */
    private String currentDate;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public ResultInfo fileGenerate(List<String> list) {

        //将学号进行一定顺序的排序
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //获取生成公示的时间
        currentDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        //获取数据
        String[][] data = DataUtil.listTransfer(getInformation(list));

        String filepath = null;

        try {
            filepath = generate(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        title = null;

        return ResultInfo.successInfo("生成第一榜公示成功，共"+data.length+"个入党申请人", filepath);
    }


    private String generate(String[][] data) throws IOException {
        //加载模板文件
        Workbook workbook = new Workbook();
        InputStream in = Resources.getResourceAsStream("model/publicity/first_publicity_model.xlsx");
        workbook.loadFromStream(in);
        //获取工作表
        Worksheet worksheet = workbook.getWorksheets().get(0);

        //插入数据
        worksheet.insertArray(data, 4, 2);

        //设置表头
        worksheet.findString("party", true, true).setText(title);
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

        for (String s : list) {
            Student student = studentDao.findStudentByNum(s);
            if (title == null) {
                title = "互联网金融与信息工程学院"+student.getPartyBranch()+"发展党员第一榜公示";
            }
            List<String> studentData = new ArrayList<>();
            studentData.add(student.getName());
            studentData.add(student.getNum());
            studentData.add(student.getSex());
            String[] split = student.getBirth().split("-");
            studentData.add(split[0]+"年"+split[1]+"月");
            //民族
            studentData.add(student.getNation());
            //入学时间
            String[] admissionTime = student.getAdmissionTime().split("-");
            studentData.add(admissionTime[0]+"年"+admissionTime[1]+"月");
            //籍贯
            studentData.add(student.getStudentNative());
            //联系方式
            studentData.add(student.getTelephone());
            //获取申请人对象
            Applicant applicant = student.getApplicant();
            //入党申请时间
            String[] application = applicant.getTimeOfApplication().split("-");
            studentData.add(application[0]+"年"+application[1]+"月"+application[2]+"日");
            studentData.add("是");
            studentData.add("团员");
            studentData.add(applicant.getApplicantOccupation());
            result.add(studentData);
        }
        return result;
    }


}
