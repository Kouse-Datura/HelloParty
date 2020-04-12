package edu.gduf.service;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-03-28 11:13
 **/
public interface InfoEntryService {

    /**
     * 读取文件并生成相应的对象
     * @param filepath 类路径下的文件路径
     * @return
     */
    boolean informationEntry(String filepath);


}
