package edu.gduf.service.impl;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.collections.RowCollection;
import com.spire.doc.fields.DocPicture;
import edu.gduf.dao.InspectorDao;
import edu.gduf.dao.StudentDao;
import edu.gduf.domain.*;
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
 * 生成考察报告的实现类
 * @author 古市
 * @date 2020-04-06 15:24
 **/
@Service
public class InspectReportGenerateServiceImpl implements FileGenerateService {

    private InspectorDao inspectorDao;
    private StudentDao studentDao;

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Autowired
    public void setInspectorDao(InspectorDao inspectorDao) {
        this.inspectorDao = inspectorDao;
    }

    @Override
    public ResultInfo fileGenerate(List<String> list) {

        List<String> nums = inspectorDao.findAllNums();
        for (String s : list) {
            if (!nums.contains(s)) {
                return ResultInfo.failInfo("考察对象中不存在学生：" + s + ",请检查名单是否合理合理性。");
            }
        }

        //根据当前日期生成表头的时间
        String time;
        final int judge = 6;
        String[] split = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
        int month = Integer.parseInt(split[1]);
        if (month <= judge) {
            time = split[0] + "上半年";
        } else {
            time = split[0] + "下半年";
        }


        List<List<String>> information = getInformation(list);

        List<String> filePathList = new ArrayList<>();
        for (List<String> strings : information) {
            try {

                filePathList.add(generate(time, strings));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //合并文件
        String filePath = this.getClass().getResource("/").getPath() + "output/inspect_report/" + UUID.randomUUID().toString().replace("-", "") + ".docx";
        try {
            DataUtil.mergeDocFiles(filePathList, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResultInfo.successInfo("成功生成考察报告" + list.size() + "份", filePath);
    }


    /**
     * 生成考察报告文件，后缀名为docx
     *
     * @param time           考察报告表头的时间
     * @param studentMessage 考察报告生成所需信息
     * @return 文件路径
     * @throws IOException
     */
    private String generate(String time, List<String> studentMessage) throws IOException {
        //获取模板的输入流
        InputStream in = Resources.getResourceAsStream("model/inspect_report_model.docx");
        Document doc = new Document(in);
        //获取section
        Section section = doc.getSections().get(0);
        int flag = 0;

        //替换指定文本
        doc.setReplaceFirst(true);
        doc.replace("time", time, true, true);
        doc.replace("party", studentMessage.get(flag++), true, true);
        doc.replace("class", studentMessage.get(flag++), true, true);

        //获取行集合
        RowCollection rows = section.getTables().get(0).getRows();

        //添加名字
        rows.get(0).getCells().get(1).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加性别
        rows.get(0).getCells().get(3).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加民族
        rows.get(0).getCells().get(5).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加籍贯
        rows.get(0).getCells().get(7).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加宿舍号
        rows.get(0).getCells().get(9).getFirstParagraph().setText(studentMessage.get(flag++));

        //添加身份证
        rows.get(1).getCells().get(1).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加学号
        rows.get(1).getCells().get(3).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加入党申请时间
        rows.get(1).getCells().get(5).getFirstParagraph().setText(studentMessage.get(flag++));

        //添加担任职务
        rows.get(2).getCells().get(1).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加平均分
        rows.get(2).getCells().get(3).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加班级排名
        rows.get(2).getCells().get(5).getFirstParagraph().setText(studentMessage.get(flag++));

        //添加经历
        rows.get(4).getCells().get(0).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加优点
        rows.get(4).getCells().get(1).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加缺点
        rows.get(4).getCells().get(2).getFirstParagraph().setText(studentMessage.get(flag++));

        //添加评优情况（获奖情况）
        rows.get(6).getCells().get(0).getFirstParagraph().setText(studentMessage.get(flag++));

        //添加推优到会人数
        rows.get(8).getCells().get(1).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加赞成票
        rows.get(8).getCells().get(3).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加反对票
        rows.get(8).getCells().get(5).getFirstParagraph().setText(studentMessage.get(flag++));
        //添加弃权票
        rows.get(8).getCells().get(7).getFirstParagraph().setText(studentMessage.get(flag));

        //插入图片
        InputStream pictureIn = Resources.getResourceAsStream("picture/" + studentMessage.get(8) + ".jpg");
        DocPicture picture = rows.get(0).getCells().get(10).getFirstParagraph().appendPicture(pictureIn);
        picture.setWidth(75);
        picture.setHeight(100);


        String filePath = this.getClass().getResource("/").getPath() + "output/inspect_report/" +
                studentMessage.get(8) + studentMessage.get(2) + ".docx";
        File file = new File(filePath);
        //获取父目录
        File parent = file.getParentFile();
        if (!parent.exists()) {
            //如果不存在就创建
            parent.mkdirs();
        }
        file.createNewFile();

        OutputStream out = new FileOutputStream(file);
        doc.saveToStream(out, FileFormat.Docx);

        //释放资源
        pictureIn.close();
        out.close();
        doc.close();
        in.close();
        return filePath;
    }


    /**
     * 解析student对象，生成精简的数据列表
     *
     * @param list 学号列表
     * @return student对象的数据列表
     */
    @Override
    public List<List<String>> getInformation(List<String> list) {
        //所有学生的数据列表
        List<List<String>> studentList = new ArrayList<>();

        for (String s : list) {
            Student student = studentDao.findStudentByNum(s);
            //单个学生的数据列表，数据全部用字符串存储
            List<String> studentMessage = new ArrayList<>();
            //添加班级和党支部的信息
            studentMessage.add(student.getPartyBranch());
            studentMessage.add(student.getClassNum());
            //添加学生基本信息
            studentMessage.add(student.getName());
            studentMessage.add(student.getSex());
            studentMessage.add(student.getNation());
            studentMessage.add(student.getStudentNative());
            studentMessage.add(student.getDormitory());
            studentMessage.add(student.getIdentityCard());
            studentMessage.add(student.getNum());
            studentMessage.add(student.getApplicant().getTimeOfApplication());
            //获取考察对象数据
            Inspector inspector = student.getInspector();
            studentMessage.add(inspector.getInspectorOccupation());
            //获取最新的成绩
            Mark mark = student.getMarkList().get(0);
            studentMessage.add(String.valueOf(mark.getAcademicScore()));
            studentMessage.add(String.valueOf(mark.getAcademicRanking()));
            studentMessage.add(inspector.getExperience());
            studentMessage.add(inspector.getAdvantage());
            studentMessage.add(inspector.getDisadvantage());
            studentMessage.add(inspector.getAward());
            //获取积极分子期间的数据
            Activist activist = student.getActivist();
            studentMessage.add(String.valueOf(activist.getAttendNum()));
            studentMessage.add(String.valueOf(activist.getAgree()));
            studentMessage.add(String.valueOf(activist.getDisagree()));
            studentMessage.add(String.valueOf(activist.getAbstain()));

            //完成数据转换
            studentList.add(studentMessage);
        }
        return studentList;
    }
}
