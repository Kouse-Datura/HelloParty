package edu.gduf.web.controller;

import edu.gduf.domain.ResultInfo;
import edu.gduf.service.impl.record.ActivistRecordGenerateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-05-04 01:38
 **/
@Controller
@RequestMapping("/record")
public class RecordController {


    private ActivistRecordGenerateServiceImpl service;

    @Autowired
    public void setService(ActivistRecordGenerateServiceImpl service) {
        this.service = service;
    }

    @ResponseBody
    @RequestMapping("/developer")
    public ResultInfo testDeveloper(HttpServletResponse response){
        List<String> list = new ArrayList<>();

        list.add("161543103");
        list.add("161543133");
        list.add("161543136");
        list.add("171543116");
        list.add("171543146");
        list.add("181548128");
        list.add("181548117");
        list.add("181548121");
        list.add("171543445");
        list.add("181548113");
        list.add("191549101");

        return service.fileGenerate(list);
    }






}
