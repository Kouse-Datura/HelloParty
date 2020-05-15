package edu.gduf.service.impl;

import edu.gduf.dao.StudentDao;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.PersonalShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-03-27 15:20
 **/
@Service
public class PersonalShowServiceImpl implements PersonalShowService {

    private StudentDao dao;

    @Autowired
    public void setDao(StudentDao dao) {
        this.dao = dao;
    }

    @Override
    public ResultInfo getInformationByNum(String num){
        return ResultInfo.successInfo(null, dao.findStudentByNum(num));
    }

}
