package edu.gduf.utils;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-25 14:11
 **/
public class DataUtil {

    /**
     * 将数据列表转换为一个二维数组，用于在excel表格中的写入
     * @param list 数据列表
     * @return 一个二维数组
     */
    public static String[][] listTransfer(List<List<String>> list){
        List<String[]> temp = new ArrayList<>();
        for (List<String> strings : list) {
            temp.add(strings.toArray(new String[]{}));
        }
        return temp.toArray(new String[][]{});
    }


    /**
     * 合并文件集
     * @param filePathList 文件集
     * @param filePath 生成的文件路径
     * @throws IOException
     */
    public static void mergeDocFiles(List<String> filePathList, String filePath) throws IOException {

        //合并文档
        String s = filePathList.get(0);
        InputStream fileInputStream = new FileInputStream(new File(s));
        Document doc = new Document(fileInputStream);
        for (int i = 1; i < filePathList.size(); i++) {
            InputStream in = new FileInputStream(new File(filePathList.get(i)));
            doc.insertTextFromStream(in, FileFormat.Docx);
            in.close();
        }

//        //生成最终文件
//
        File file = new File(filePath);
        //获取父目录
        File parent = file.getParentFile();
        if (!parent.exists()){
            //如果不存在就创建
            parent.mkdirs();
        }
        //创建文件
        file.createNewFile();
        //获取输出流
        FileOutputStream out = new FileOutputStream(file);
        doc.saveToStream(out, FileFormat.Docx);
        out.close();
        doc.close();
        fileInputStream.close();

        //删除多余的文件
        for (String filepath : filePathList) {
            File deleteFile = new File(filepath);
            deleteFile.delete();
        }
    }
}
