package edu.gduf.web.controller;

import com.alibaba.fastjson.JSON;
import edu.gduf.domain.ResultInfo;
import edu.gduf.service.impl.political.PoliticalSituationGenerateServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-25 14:38
 **/
@WebServlet("/electric")
public class ElectricController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        PoliticalSituationGenerateServiceImpl service = context.getBean("politicalSituation", PoliticalSituationGenerateServiceImpl.class);
        List<String> list = new ArrayList<>();
        list.add("161543103");
        list.add("161543133");
        list.add("161543136");
        list.add("171543116");
        list.add("171543146");
        list.add("181548117");
        list.add("181548121");
        list.add("181548128");

        ResultInfo resultInfo = service.fileGenerate(list);
        String s = JSON.toJSONString(resultInfo);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(s);
    }
}
