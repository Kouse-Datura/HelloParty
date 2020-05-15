package edu.gduf.service.impl.political;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import edu.gduf.dao.StudentDao;
import edu.gduf.domain.Developer;
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
 * 用于生成政审电子表
 * @author 古市
 * @date 2020-04-28 19:45
 **/
@Service("politicalElectronic")
public class PoliticalElectronicGenerateServiceImpl implements FileGenerateService {

    private StudentDao dao;

    /**
     * 文件生成的当前日期
     */
    private String currentDate;

    @Autowired
    public void setDao(StudentDao dao) {
        this.dao = dao;
    }


    @Override
    public ResultInfo fileGenerate(List<String> list) {
        //获取当前日期
        currentDate = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

        //获取数据集
        List<List<String>> information = getInformation(list);
        //文件路径集合
        List<String> filePaths = new ArrayList<>();
        //遍历数据集生成文件
        for (List<String> data : information) {
            try {
                filePaths.add(generate(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //合并文件
        String filepath = this.getClass().getResource("/").getPath()+"output/political/"+
                UUID.randomUUID().toString().replace("-", "") +".docx";
        try {
            DataUtil.mergeDocFiles(filePaths, filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultInfo.successInfo("生成政审电子表"+list.size()+"份",filepath);
    }

    private String generate(List<String> list) throws IOException {
        //获取模板的输入流
        InputStream in = Resources.getResourceAsStream("model/political_electronic_table.doc");
        Document doc = new Document(in);

        //替换指定文本
        doc.replace("name", list.get(0), true, true);
        doc.replace("identityCard", list.get(1), true, true);
        doc.replace("sex", list.get(2), true, true);
        doc.replace("nation", list.get(3), true, true);
        doc.replace("birth", list.get(4), true, true);
        doc.replace("native", list.get(5), true, true);
        doc.replace("enterLeagueTime", list.get(6), true, true);
        doc.replace("profession", list.get(7), true, true);
        doc.replace("dadName", list.get(8), true, true);
        doc.replace("dadIdentity", list.get(9), true, true);
        doc.replace("dadStatus", list.get(10), true, true);
        doc.replace("momName", list.get(11), true, true);
        doc.replace("momIdentity", list.get(12), true, true);
        doc.replace("momStatus", list.get(13), true, true);
        doc.replace("timeOfApplication", list.get(14), true, true);
        doc.replace("whichVolume", list.get(15), true, true);
        doc.replace("currentTime", currentDate, true, true);

        //生成文件路径
        String filePath = this.getClass().getResource("/").getPath()+"output/political/"+
                UUID.randomUUID().toString().replace("-","")+".docx";
        File file = new File(filePath);
        //获取父目录
        File parent = file.getParentFile();
        if (!parent.exists()){
            //如果不存在就创建
            parent.mkdirs();
        }
        file.createNewFile();
        OutputStream out = new FileOutputStream(file);
        doc.saveToStream(out, FileFormat.Docx);
        out.close();
        doc.close();
        in.close();

        return filePath;
    }

    @Override
    public List<List<String>> getInformation(List<String> list) {

        List<List<String>> result = new ArrayList<>();

        for (String s : list) {
            Student student = dao.findStudentByNum(s);
            List<String> data = new ArrayList<>();
            data.add(student.getName());
            data.add(student.getIdentityCard());
            data.add(student.getSex());
            data.add(student.getNation());
            String[] split = student.getBirth().split("-");
            data.add(split[0]+"年"+split[1]+"月"+split[2]+"日");
            data.add(student.getStudentNative());
            Developer developer = student.getDeveloper();
            String[] split1 = developer.getEnterLeagueTime().split("-");
            data.add(split1[0]+"年"+split1[1]+"月");
            data.add(developer.getProfession());
            data.add(developer.getDadName());
            data.add(developer.getDadIdentity());
            data.add(developer.getDadStatus());
            data.add(developer.getMomName());
            data.add(developer.getMomIdentity());
            data.add(developer.getMomStatus());
            String[] split2 = student.getApplicant().getTimeOfApplication().split("-");
            data.add(split2[0]+"年"+split2[1]+"月"+split2[2]+"日");
            data.add(String.valueOf(student.getActivist().getWhichVolume()));
            result.add(data);
        }
        return result;
    }
    
}
