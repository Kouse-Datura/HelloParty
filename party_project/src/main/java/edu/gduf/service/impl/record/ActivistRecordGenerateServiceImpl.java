package edu.gduf.service.impl.record;

import com.spire.doc.*;
import edu.gduf.dao.StudentDao;
import edu.gduf.domain.ResultInfo;
import edu.gduf.domain.Student;
import edu.gduf.service.FileGenerateService;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-05-04 00:30
 **/
@Service
public class ActivistRecordGenerateServiceImpl implements FileGenerateService {

    private StudentDao dao;

    /**
     * 落款日期以及确定为积极分子的日期
     */
    private String identifyingActivistTime = null;

    /**
     * 当前生成的文件所属的支部
     */
    private String branch = null;

    @Autowired
    public void setDao(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultInfo fileGenerate(List<String> list) {

        /*
         * 因为如果没用特殊声明，spring创建这些bean对象为单例，
         * 意味着branch这些值只会赋值一次其他情况下就会一直存在值，所以需要进行回溯
         */
        branch = null;
        identifyingActivistTime = null;

        //将学号进行一定顺序的排序
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        //获取数据集
        List<List<String>> information = getInformation(list);
        //
        String filepath = null;
        //生成文件
        try {
             filepath = generate(information);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultInfo.successInfo("成功生成"+branch+"确定入党积极分子备案表",filepath);
    }

    private String generate(List<List<String>> information) throws IOException {
        //加载模板
        InputStream in = Resources.getResourceAsStream("model/record/activist_record_model.docx");
        Document doc = new Document(in);

        //替换指定文本
        doc.replace("branch", branch, true, true);
        doc.replace("time", identifyingActivistTime, true, true);
        doc.replace("name", information.get(0).get(0), true, true);
        doc.replace("num", String.valueOf(information.size()), true, true);

        //获取table,即表格
        Section section = doc.getSections().get(0);
        Table table = section.getTables().get(0);

        for (List<String> data : information) {
            //添加行
            TableRow tableRow = table.addRow();
            //给添加的行填充值
            tableRow.getCells().get(0).addParagraph().setText(branch);
            tableRow.getCells().get(1).addParagraph().setText(data.get(0));
            tableRow.getCells().get(2).addParagraph().setText(data.get(1));
            tableRow.getCells().get(3).addParagraph().setText(data.get(2));
            tableRow.getCells().get(4).addParagraph().setText(data.get(3));
            tableRow.getCells().get(5).addParagraph().setText("高中");
            tableRow.getCells().get(6).addParagraph().setText(data.get(4));
            tableRow.getCells().get(7).addParagraph().setText(data.get(5));
            tableRow.getCells().get(8).addParagraph().setText("班级推优");
            tableRow.getCells().get(9).addParagraph().setText(identifyingActivistTime);
            tableRow.getCells().get(10).addParagraph().setText(data.get(6));
        }


        String filePath = this.getClass().getResource("/").getPath()+"output/record_table/"+
                UUID.randomUUID().toString().replace("-","") +".docx";
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

        //释放资源
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
            if (branch == null && identifyingActivistTime == null) {
                branch = student.getPartyBranch();
                String[] split = student.getActivist().getIdentifyingActivist().split("-");
                identifyingActivistTime = split[0]+"年"+split[1]+"月"+split[2]+"日";
            }
            List<String> data = new ArrayList<>();
            data.add(student.getName());
            data.add(student.getSex());
            String[] split = student.getBirth().split("-");
            data.add(split[0]+"年"+split[1]+"月"+split[2]+"日");
            data.add(student.getIdentityCard());
            data.add(student.getActivist().getActivistOccupation());
            String[] split1 = student.getApplicant().getTimeOfApplication().split("-");
            data.add(split1[0]+"年"+split1[1]+"月"+split1[2]+"日");
            data.add(student.getActivist().getCultivateContacts());
            result.add(data);
        }
        return result;
    }
}
