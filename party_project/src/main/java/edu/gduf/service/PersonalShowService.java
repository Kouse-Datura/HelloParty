package edu.gduf.service;

import edu.gduf.domain.ResultInfo;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-03-27 15:22
 **/
public interface PersonalShowService {

    /**
     * 通过学号获取学生的所有信息
     * @param num 学号
     * @return 返回相应的学生对象,如果为null，则说明该学号不存在
     */
    ResultInfo getInformationByNum(String num);

}
