package edu.gduf.web.controller;

import edu.gduf.domain.ResultInfo;
import edu.gduf.service.impl.SignInFormGenerateServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-04-23 17:41
 **/
@WebServlet("/inspectController")
public class InspectorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        SignInFormGenerateServiceImpl service = (SignInFormGenerateServiceImpl) context.getBean("sign");
        ArrayList<String> list = new ArrayList<>();


        ResultInfo resultInfo = service.fileGenerate(list);
        String data = (String) resultInfo.getData();
        System.out.println(data);
        resp.getWriter().write("success");
    }
}
