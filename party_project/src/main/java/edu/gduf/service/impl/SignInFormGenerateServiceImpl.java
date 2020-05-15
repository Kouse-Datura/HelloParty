package edu.gduf.service.impl;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.collections.RowCollection;
import edu.gduf.dao.StudentDao;
import edu.gduf.domain.ResultInfo;
import edu.gduf.domain.Student;
import edu.gduf.service.FileGenerateService;
import edu.gduf.utils.DataUtil;
import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Demo Class
 * 用于生成签到表
 * @author 古市
 * @date 2020-04-30 23:09
 **/
@Service
public class SignInFormGenerateServiceImpl implements FileGenerateService {

    private StudentDao dao;

    final int count = 24;

    @Autowired
    public void setDao(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultInfo fileGenerate(List<String> list) {
        //获取最终数据列表
        List<List<String>> information = getInformation(list);
        //生成的文件路径
        List<String> filePaths = new ArrayList<>();
        //分批生成签到表
        while (!information.isEmpty()){
            List<List<String>> need = new ArrayList<>();
            while (need.size() <= count && !information.isEmpty()){
                need.add(information.remove(0));
            }
            if (need.size() == 0){
                break;
            }else {
                try {
                    filePaths.add(generate(need));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //指定最终文件的路径+文件名
        String filepath = this.getClass().getResource("/").getPath()+"output/sign/"+ UUID.randomUUID().toString().replace("-", "") +".docx";
        //合并文件
        if (filePaths.size() > 1){
            try {
                DataUtil.mergeDocFiles(filePaths, filepath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            filepath = filePaths.get(0);
        }

        return ResultInfo.successInfo("成功生成签到表",filepath);
    }

    private String generate(List<List<String>> need) throws IOException {
        //获取模板的输入流
        InputStream in = Resources.getResourceAsStream("model/sign_in_form_model.docx");

        Document doc = new Document(in);

        //获取section
        Section section = doc.getSections().get(0);
        //获取行集合
        RowCollection rows = section.getTables().get(0).getRows();

        int row,col;
        row = 4;
        col = 1;

        //需要保证list的长度不超过24
        for (List<String> s : need) {
            //添加班级
            rows.get(row).getCells().get(col).getFirstParagraph().setText(s.get(0));
            //添加姓名
            rows.get(row).getCells().get(col+1).getFirstParagraph().setText(s.get(1));
            //添加联系方式
            rows.get(row).getCells().get(col+2).getFirstParagraph().setText(s.get(2));
            ++row;
            if (row == 16){
                row = 4;
                col = 5;
            }
        }

        //生成文件路径
        String filePath = this.getClass().getResource("/").getPath()+"output/sign/"+
                UUID.randomUUID().toString().replace("-","")+".docx";
        File file = new File(filePath);
        //获取父目录
        File parent = file.getParentFile();
        if (!parent.exists()){
            //如果不存在就创建
            parent.mkdirs();
        }
        file.createNewFile();
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        doc.saveToStream(out, FileFormat.Docx);
        out.close();
        doc.close();
        in.close();

        return filePath;
    }


    /**
     * 查询数据并转化数据格式
     * @param list 学号列表
     * @return
     */
    @Override
    public List<List<String>> getInformation(List<String> list) {
        List<Student> students = dao.findPartInfoByList(list);

        List<List<String>> result = new ArrayList<>();

        for (Student student : students) {
            List<String> strings = new ArrayList<>();
            strings.add(student.getClassNum());
            strings.add(student.getName());
            strings.add(student.getTelephone());
            result.add(strings);
        }
        return result;
    }
}
