package edu.gduf.service.impl.publicity;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import edu.gduf.dao.StudentDao;
import edu.gduf.domain.Activist;
import edu.gduf.domain.ResultInfo;
import edu.gduf.domain.Student;
import edu.gduf.service.FileGenerateService;
import edu.gduf.utils.DataUtil;
import org.apache.ibatis.io.Resources;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-27 23:46
 **/
@Service
public class SecondPublicityGenerateServiceImpl implements FileGenerateService {

    private StudentDao studentDao;

    /**
     * 文件表头
     */
    private String title = null;

    /**
     * 文件生成的当前日期
     */
    private String currentDate;


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

        //生成文件，获得文件路径
        String filepath = null;
        try {
            filepath = generate(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        title = null;

        return ResultInfo.successInfo("生成第二榜公示成功，共"+data.length+"个入党积极分子", filepath);
    }


    private String generate(String[][] data) throws IOException {
        //加载模板文件
        Workbook workbook = new Workbook();
        InputStream in = Resources.getResourceAsStream("model/publicity/second_publicity_model.xlsx");
        workbook.loadFromStream(in);
        //获取工作表
        Worksheet worksheet = workbook.getWorksheets().get(0);

        //插入数据
        worksheet.insertArray(data, 4, 1);

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
            List<String> studentData = new ArrayList<>();
            Student student = studentDao.findStudentByNum(s);
            if (title == null) {
                title = "互联网金融与信息工程学院"+student.getPartyBranch()+"发展党员第二次公示\n" +
                        "（确定入党积极分子的公示）";
            }
            studentData.add(student.getName());
            studentData.add(student.getClassNum());
            studentData.add(student.getSex());
            studentData.add(student.getNation());
            studentData.add(student.getStudentNative());
            String[] split = student.getBirth().split("-");
            studentData.add(split[0]+"年"+split[1]+"月");
            studentData.add("高中");
            studentData.add("团员");
            //获取积极分子对象
            Activist activist = student.getActivist();
            studentData.add(activist.getActivistOccupation());
            String[] applicationTime = student.getApplicant().getTimeOfApplication().split("-");
            studentData.add(applicationTime[0]+"年"+applicationTime[1]+"月"+applicationTime[2]+"日");
            studentData.add(currentDate);
            result.add(studentData);
        }
        return result;
    }

}
