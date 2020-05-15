package edu.gduf.web.controller;

import edu.gduf.domain.ResultInfo;
import edu.gduf.service.impl.publicity.ThirdPublicityGenerateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-25 14:38
 **/
@Controller
@RequestMapping("/publicity")
public class PublicityController extends HttpServlet {

    private ThirdPublicityGenerateServiceImpl thirdPublicityGenerateService;

    @Autowired
    public void setThirdPublicityGenerateService(ThirdPublicityGenerateServiceImpl thirdPublicityGenerateService) {
        this.thirdPublicityGenerateService = thirdPublicityGenerateService;
    }

    @ResponseBody
    @RequestMapping("/third")
    public ResultInfo generate(){
        List<String> list = new ArrayList<>();
        list.add("171543110");
        list.add("171543213");
        list.add("171543330");
        list.add("171543402");
        list.add("171543435");
        list.add("181543212");
        list.add("181543203");
        list.add("181543329");
        list.add("181543404");
        list.add("171541130");
        list.add("171541231");
        list.add("17154A104");
        list.add("17154A204");
        list.add("17154A243");
        list.add("181541110");
        list.add("181541124");
        list.add("18154A119");
        list.add("18154A125");
        list.add("18154A214");
        list.add("18154A225");
        list.add("18154A201");
        list.add("18154A248");
        list.add("181541102");
        list.add("18154A303");
        list.add("18154A417");
        list.add("18154A449");
        list.add("18154A429");
        list.add("18154A433");
        list.add("171549201");
        list.add("181548128");
        list.add("181548117");
        list.add("181548204");
        list.add("181548227");
        list.add("181549206");
        list.add("181549217");
        list.add("181549245");
        list.add("181549250");
        list.add("181549402");

        return thirdPublicityGenerateService.fileGenerate(list);
    }



}
