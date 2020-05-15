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
 * @date 2020-05-04 12:48
 **/
@Service
public class PartyMemberRecordGenerateServiceImpl implements FileGenerateService {

    private StudentDao studentDao;

    /**
     * 落款日期以及入党的日期
     */
    private String enterTime = null;

    /**
     * 生成的年份
     */
    String year;

    /**
     * 生成的季度
     */
    String season;

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

        //获取数据集
        List<List<String>> information = getInformation(list);
        String filepath = null;

        try {
            filepath = generate(information);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
         * 因为如果没用特殊声明，spring创建这些bean对象为单例，
         * 意味着enterTime这些值只会赋值一次其他情况下就会一直存在值，所以需要进行回溯
         */
        enterTime = null;

        return ResultInfo.successInfo("成功生成"+year+"年第"+season+"季度接收预备党员备案表",
                filepath);
    }

    private String generate(List<List<String>> information) throws IOException {
        //加载模板
        InputStream in = Resources.getResourceAsStream("model/record/partymember_record_table.docx");
        Document doc = new Document(in);

        String[] split = enterTime.split("-");
        year = split[0];
        final int judge = 6;
        if (Integer.parseInt(split[1]) > judge){
            season = "二";
        }else {
            season = "一";
        }
        enterTime = split[0]+"年"+split[1]+"月"+split[2]+"日";

        //替换指定文本
        doc.replace("time", enterTime, true, true);
        doc.replace("name", information.get(0).get(1), true, true);
        doc.replace("num", String.valueOf(information.size()), true, true);
        doc.replace("year",year,true,true);
        doc.replace("season",season,true,true);

        //获取table,即表格
        Section section = doc.getSections().get(0);
        Table table = section.getTables().get(0);

        for (List<String> data : information) {
            //添加行
            TableRow tableRow = table.addRow();
            //给添加的行填充值
            tableRow.getCells().get(0).addParagraph().setText(data.get(0));
            tableRow.getCells().get(1).addParagraph().setText(data.get(1));
            tableRow.getCells().get(2).addParagraph().setText(data.get(2));
            tableRow.getCells().get(3).addParagraph().setText(data.get(3));
            tableRow.getCells().get(4).addParagraph().setText(data.get(4));
            tableRow.getCells().get(5).addParagraph().setText("高中");
            tableRow.getCells().get(6).addParagraph().setText(data.get(5));
            tableRow.getCells().get(7).addParagraph().setText(data.get(6));
            tableRow.getCells().get(8).addParagraph().setText(data.get(7));
            tableRow.getCells().get(9).addParagraph().setText(data.get(8));
            tableRow.getCells().get(10).addParagraph().setText(data.get(9));
            tableRow.getCells().get(11).addParagraph().setText(enterTime);
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
            Student student = studentDao.findStudentByNum(s);
            if (enterTime == null) {
                enterTime = student.getPartyMember().getEnterTime();
            }
            List<String> data = new ArrayList<>();
            data.add(student.getPartyBranch());
            data.add(student.getName());
            data.add(student.getSex());
            String[] split = student.getBirth().split("-");
            data.add(split[0]+"年"+split[1]+"月"+split[2]+"日");
            data.add(student.getIdentityCard());
            data.add(student.getInspector().getInspectorOccupation());
            data.add(student.getDeveloper().getIntroducer1());
            //申请入党时间
            String[] split1 = student.getApplicant().getTimeOfApplication().split("-");
            data.add(split1[0]+"年"+split1[1]+"月"+split1[2]+"日");
            //确定为积极分子时间
            String[] split2 = student.getActivist().getIdentifyingActivist().split("-");
            data.add(split2[0]+"年"+split2[1]+"月"+split2[2]+"日");
            //确定为发展对象时间
            String[] split3 = student.getDeveloper().getIdentifyingDeveloper().split("-");
            data.add(split3[0]+"年"+split3[1]+"月"+split3[2]+"日");
            result.add(data);
        }
        return result;
    }
}
