package edu.gduf.service;

import edu.gduf.domain.ResultInfo;

import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-06 15:23
 **/
public interface FileGenerateService {

    /**
     * 生成工作文件
     * @param list 学号列表
     * @return 返回结果集，包含生成文件的文件路径集合
     */
    ResultInfo fileGenerate(List<String> list);


    /**
     * 获取数据并转换为字符串列表
     * @param list 学号列表
     * @return 数据列表
     */
    List<List<String>> getInformation(List<String> list);



}
