package edu.gduf.service.impl.publicity;

import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import edu.gduf.dao.StudentDao;
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
 *
 * @author 古市
 * @date 2020-04-26 17:45
 **/
@Service
public class FifthPublicityGenerateServiceImpl implements FileGenerateService {

    private StudentDao studentDao;

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
        //获取当前日期
        currentDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

        //获取数据列表,转换格式
        String[][] transfer = DataUtil.listTransfer(getInformation(list));

        //文件路径
        String filepath = null;
        try {
            filepath = generate(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultInfo.successInfo("生成第五榜公示成功，共"+transfer.length+"个预备党员", filepath);
    }


    private String generate(String[][] data) throws IOException {
        //加载模板文件
        Workbook workbook = new Workbook();
        InputStream in = Resources.getResourceAsStream("model/publicity/fifth_publicity_model.xlsx");
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
        //数据结果集
        List<List<String>> result = new ArrayList<>();
        //抽取所需数据，转化成数据集合
        for (String s : list) {
            Student student = studentDao.findStudentByNum(s);
            List<String> data = new ArrayList<>();
            data.add(student.getName());
            data.add(student.getSex());
            data.add(student.getNation());
            data.add(student.getStudentNative());
            String[] split = student.getBirth().split("-");
            data.add(split[0]+"年"+split[1]+"月");
            data.add(student.getDegreeOfEducation());
            data.add("预备党员");
            data.add(student.getClassNum());
            //现任职务留空后续补上
            data.add("");
            data.add(currentDate.substring(0, 8));
            result.add(data);
        }
        return result;
    }
}
