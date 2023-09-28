package com.resumegenerator.Controller;

import com.resumegenerator.Serivce.GenerateReport;
import com.resumegenerator.VO.Carrer;
import com.resumegenerator.VO.Education;
import com.resumegenerator.VO.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
*/@SpringBootApplication(scanBasePackages = {"com.resumegenerator.Serivce"})//main클래스 하위에 있어 패키지 지정, 스캔
@Controller
public class ResumeController {

    private final GenerateReport generateReport;
    @Autowired
    public ResumeController(GenerateReport generateReport) {
        this.generateReport = generateReport;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ResumeController.class, args);

        localResume();
    }

    public static void localResume() throws Exception {

        PersonInfo person = new PersonInfo();
        ArrayList<Education> eduList = new ArrayList<Education>();
        ArrayList<Carrer> carerList = new ArrayList<Carrer>();
        String introduce = "";

        System.out.println("/************ 자기소개서 생성기 **************/");

        if (GenerateReport.getPersonInfo(person)) { System.out.println("/************ 개인정보 입력 종료 **************/"); }
        if (GenerateReport.getEduInfo(eduList)) { System.out.println("/************ 학력정보 입력 종료 **************/");}
        if (GenerateReport.getCarrerInfo(carerList)) { System.out.println("/************ 경력정보 입력 종료 **************/"); }

        try {
            introduce += GenerateReport.getIntroduceInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("************ 자기소개 입력 종료 **************");
        }

        System.out.println("자기소개 : " + introduce);

        //자기소개서 생성 요청
        GenerateReport.generate(person,eduList,carerList,introduce);

        //System.err.println("프로그램이 종료되었습니다.");
    }

    @RequestMapping("/gettingStart")
    /**
    * @methodName   : gettingStart
    * @author       : Jiyong Jung
    * @date         : 2023-09-28 :: 오후 8:09
    * @param        : [org.springframework.ui.Model] ([model]), java.lang.String
    * @desc         : 자기소개서 작성 페이지
    * ===========================================================
    * DATE              AUTHOR             NOTE
    * -----------------------------------------------------------
    * 2023-09-28 :: 오후 8:09        Jiyong Jung       최초 생성
    */
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
