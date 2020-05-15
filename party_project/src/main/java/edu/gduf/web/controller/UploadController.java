package edu.gduf.web.controller;

import edu.gduf.domain.ResultInfo;
import edu.gduf.service.impl.infoentry.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Demo Class
 *
 * @author 古市
 * @date 2020-05-11 17:31
 **/
@Controller
@RequestMapping("/upload")
public class UploadController {


    private StudentInfoEntryServiceImpl studentInfoEntryService;

    private ApplicantInfoEntryServiceImpl applicantInfoEntryService;

    private ActivistInfoEntryServiceImpl activistInfoEntryService;

    private InspectorInfoEntryServiceImpl inspectorInfoEntryService;

    private DeveloperInfoEntryImpl developerInfoEntry;

    private PartyMemberInfoEntryServiceImpl partyMemberInfoEntryService;

    private MarkInfoEntryServiceImpl markInfoEntryService;

    @Autowired
    public void setStudentInfoEntryService(StudentInfoEntryServiceImpl studentInfoEntryService) {
        this.studentInfoEntryService = studentInfoEntryService;
    }

    @Autowired
    public void setApplicantInfoEntryService(ApplicantInfoEntryServiceImpl applicantInfoEntryService) {
        this.applicantInfoEntryService = applicantInfoEntryService;
    }

    @Autowired
    public void setActivistInfoEntryService(ActivistInfoEntryServiceImpl activistInfoEntryService) {
        this.activistInfoEntryService = activistInfoEntryService;
    }

    @Autowired
    public void setInspectorInfoEntryService(InspectorInfoEntryServiceImpl inspectorInfoEntryService) {
        this.inspectorInfoEntryService = inspectorInfoEntryService;
    }

    @Autowired
    public void setDeveloperInfoEntry(DeveloperInfoEntryImpl developerInfoEntry) {
        this.developerInfoEntry = developerInfoEntry;
    }

    @Autowired
    public void setPartyMemberInfoEntryService(PartyMemberInfoEntryServiceImpl partyMemberInfoEntryService) {
        this.partyMemberInfoEntryService = partyMemberInfoEntryService;
    }

    @Autowired
    public void setMarkInfoEntryService(MarkInfoEntryServiceImpl markInfoEntryService) {
        this.markInfoEntryService = markInfoEntryService;
    }

    private String transfer(String path, MultipartFile upload){
        //判断路径是否存在
        File file = new File(path);
        if (!file.exists()){
            //创建该文件夹
            file.mkdirs();
        }

        // 上传文件项
        // 获取上传文件的名称
        String filename = upload.getOriginalFilename();
        //生成最终的文件名
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid+"_"+filename;
        //完成文件上传
        try {
            upload.transferTo(new File(path, filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }


    @ResponseBody
    @RequestMapping("/studentData")
    public ResultInfo studentDataUpload(HttpServletRequest request, MultipartFile upload){
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");

        String filename = transfer(path, upload);

        //录入数据并返回json
        return studentInfoEntryService.informationEntry(path + filename);

    }

    @ResponseBody
    @RequestMapping("/applicantData")
    public ResultInfo applicantDataUpload(HttpServletRequest request, MultipartFile upload){
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //文件名
        String filename = transfer(path, upload);
        //录入数据并返回json
        return applicantInfoEntryService.informationEntry(path + filename);
    }

    @ResponseBody
    @RequestMapping("/activistData")
    public ResultInfo activistDataUpload(HttpServletRequest request, MultipartFile upload){
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //文件名
        String filename = transfer(path, upload);
        //录入数据并返回json
        return activistInfoEntryService.informationEntry(path + filename);
    }

    @ResponseBody
    @RequestMapping("/inspectorData")
    public ResultInfo inspectorDataUpload(HttpServletRequest request, MultipartFile upload){
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        //文件名
        String filename = transfer(path, upload);
        //录入数据并返回json
        return inspectorInfoEntryService.informationEntry(path + filename);
    }




}
