package com.resumegenerator.Controller;

import com.resumegenerator.Serivce.GenerateReport;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
* @className    : ResumeController
* @fileName     : ResumeController.java
* @author       : Jiyong Jung
* @date         : 2023-09-26 :: 오후 2:00
* @desc         : 이력서 자동생성 컨트롤러
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023-09-26 :: 오후 2:00        Jiyong Jung       최초 생성
*/@SpringBootApplication
@Controller
public class ResumeController {

    public static void main(String[] args) {
        SpringApplication.run(ResumeController.class, args);
    }

    @RequestMapping("/gettingStart")
    public String gettingStart(Model model) throws Exception{

        model.addAttribute("data","this is data");
        return "gettingStart";
    }

    //자기소개서 입력 html
    public String startResume(){

        return "startResume";
    }

    //자기소개서 생성 요청
    @PostMapping("/requestResume")
    public String requestResume(@RequestParam HashMap<String, Object> param) throws Exception{

        System.out.println(param);

        GenerateReport.generate(param);

        return "";

    }

    //생성 파일 다운로드
    public void downloadResume(){

    }
}
