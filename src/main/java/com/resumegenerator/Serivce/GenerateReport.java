package com.resumegenerator.Serivce;

import com.resumegenerator.VO.Carrer;
import com.resumegenerator.VO.Education;
import com.resumegenerator.VO.PersonInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
* @className    : GenerateReport
* @fileName     : GenerateReport.java
* @author       : Jiyong Jung
* @date         : 2023-09-26 :: 오후 4:03
* @desc         : 자기소개서 생성 서비스
* ===========================================================
* DATE              AUTHOR             NOTE
* -----------------------------------------------------------
* 2023-09-26 :: 오후 4:03        Jiyong Jung       최초 생성
*/

@Service("GenerateReport")
public class GenerateReport {

    static String retryStr = "N";

    public static void setRetryStr(String retryStr) {
        GenerateReport.retryStr = retryStr;
    }

    public static String getRetryStr() {
        return retryStr;
    }

    //로컬
    public static void generate(PersonInfo person, ArrayList<Education> eduList, ArrayList<Carrer> carerList,String introduce) throws Exception {

        //poi 라이브러리
        XSSFWorkbook wb = new XSSFWorkbook();

        //시트 생성
        Sheet sheet = wb.createSheet("이력서");

        //첫 열
        Row titleRow = sheet.createRow(0);

        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("테스트");

        //시트 스타일 정의
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER); //세로중앙정렬
        style.setAlignment(HorizontalAlignment.CENTER);//가로중앙정렬

        XSSFFont font = wb.createFont();
        font.setFontHeight((short) (20*20));
        style.setFont(font);
        //데이터 리딩

        //시트 데이터 매핑

        //파일 export
        File fb = new File("C:\\");
        String path = fb.getAbsolutePath();
        String fileLoc = path.substring(0,path.length() - 1) + "report.xlsx";

        FileOutputStream fos = new FileOutputStream(fileLoc);
        wb.write(fos);
        wb.close();
    }

    //웹 호출
    public static void generate(HashMap<String, Object> param) throws Exception {

        //poi 라이브러리
        XSSFWorkbook wb = new XSSFWorkbook();

        //시트 생성
        Sheet sheet = wb.createSheet("이력서");

        //첫 열
        Row titleRow = sheet.createRow(0);

        Cell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("테스트");

        //시트 스타일 정의
        CellStyle style = wb.createCellStyle();
        style.setVerticalAlignment(VerticalAlignment.CENTER); //세로중앙정렬
        style.setAlignment(HorizontalAlignment.CENTER);//가로중앙정렬

        XSSFFont font = wb.createFont();
        font.setFontHeight((short) (20*20));
        style.setFont(font);
        //데이터 리딩

        //시트 데이터 매핑

        //파일 export
        File fb = new File("C:\\");
        String path = fb.getAbsolutePath();
        String fileLoc = path.substring(0,path.length() - 1) + "report.xlsx";

        FileOutputStream fos = new FileOutputStream(fileLoc);
        wb.write(fos);
        wb.close();

    }

     /**
    * @methodName   : getPersonInfo
    * @author       : Jiyong Jung
    * @date         : 2023-09-28 :: 오후 7:54
    * @desc         : 개인정보 입력
    * ===========================================================
    * DATE              AUTHOR             NOTE
    * -----------------------------------------------------------
    * 2023-09-28 :: 오후 7:54        Jiyong Jung       최초 생성
    */
    public static boolean getPersonInfo(PersonInfo pi) {

        Scanner typing = new Scanner(System.in);

        while(true) {

            System.out.println("/************ 개인정보 입력 **************/");

            System.out.print("이름을 입력하세요 : ");
            pi.setName(typing.nextLine());
            System.out.print("이메일을 입력하세요 : ");
            pi.setEmail(typing.nextLine());
            System.out.print("주소를 입력하세요. : ");
            pi.setAddr(typing.nextLine());
            System.out.print("전화번호를 입력하세요. : ");
            pi.setPhone(typing.nextLine());
            System.out.print("생년월일을 입력하세요. : ");
            pi.setBirth(typing.nextLine());

            String outline = "입력하신 정보 일람입니다. \n     ▶ 이름 : " + pi.getName()
                    + "\n   ▶ 이메일 : " + pi.getEmail()
                    + "\n   ▶ 주소 : " + pi.getAddr()
                    + "\n   ▶ 전화번호 : " + pi.getPhone()
                    + "\n   ▶ 생년월일 : " + pi.getBirth()
                    + "입니다. \n 맞으면 Y, 틀리면 N (재입력) : ";

            System.out.println(outline);

            while (true) {
                setRetryStr(typing.nextLine());

                if (!"Y".equalsIgnoreCase(getRetryStr()) && !"N".equalsIgnoreCase(getRetryStr())) {
                    System.out.println("Y 또는 N만 입력 가능합니다");
                    continue;
                } else {
                    break;
                }
            }
            if("Y".equalsIgnoreCase(getRetryStr())){ break; }
        }

        return "Y".equalsIgnoreCase(getRetryStr()) ? true : false;
    }

    /**
     * @methodName   : getEduInfo
     * @author       : Jiyong Jung
     * @date         : 2023-09-28 :: 오후 7:54
     * @desc         : 학력정보 입력
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     * 2023-09-28 :: 오후 7:54        Jiyong Jung       최초 생성
     */
    public static boolean getEduInfo(ArrayList<Education> eduList) {

        Scanner typing = new Scanner(System.in);
        int cnt = 0;
        while(true){
            Education edu = new Education();
            System.out.println("/************" + (cnt+1) + "번째 학력정보 입력 **************/");
            System.out.print("졸업년도를 입력하세요 : ");     edu.setGraduationYear(typing.nextLine());
            System.out.print("학교 명을 입력하세요 : ");     edu.setSchoolName(typing.nextLine());
            System.out.print("전공을 입력하세요. : ");       edu.setMajor(typing.nextLine());
            System.out.print("졸업여부를 입력하세요. : ");    edu.setGraduationStatus(typing.nextLine());

            String outline = "입력하신 정보 일람입니다. \n     ▶ 졸업년도 : " + edu.getGraduationYear()
                    + "\n   ▶ 학교 명 : " + edu.getSchoolName()
                    + "\n   ▶ 전공 : " + edu.getMajor()
                    + "\n   ▶ 졸업여부 : " + edu.getGraduationStatus()
                    + "입니다. \n 맞으면 Y (추가입력) , 틀리면 N : , 경력정보로 넘어가기 Q : ";

            System.out.println(outline);

            while(true) {
                setRetryStr(typing.nextLine());

                boolean checker = (!"Y".equalsIgnoreCase(getRetryStr()) && !"N".equalsIgnoreCase(getRetryStr()) && !"Q".equalsIgnoreCase(getRetryStr())) ? true : false;

                if (checker) {
                    System.out.println("Y , N , Q만 입력 가능합니다");
                    continue;
                }else{
                    break;
                }
            }

            if("Y".equalsIgnoreCase(getRetryStr())){ eduList.add(cnt,edu); cnt++; }
            if("Q".equalsIgnoreCase(getRetryStr())){ eduList.add(cnt,edu); break; }

        }
        return true;
    }

    /**
    * @methodName   : getCarrerInfo
    * @author       : Jiyong Jung
    * @date         : 2023-09-28 :: 오후 7:53
    * @desc         : 경력정보 입력
    * ===========================================================
    * DATE              AUTHOR             NOTE
    * -----------------------------------------------------------
    * 2023-09-28 :: 오후 7:53        Jiyong Jung       최초 생성
    */
    public static boolean getCarrerInfo(ArrayList<Carrer> carerList) {

        Scanner typing = new Scanner(System.in);

        int cnt = 0;

        while(true){
            Carrer cr = new Carrer();

            System.out.println("/************" + (cnt+1) + "번째 경력정보 입력 **************/");

            System.out.print("근무기간을 입력하세요 : ");
            cr.setWorkPeriod(typing.nextLine());
            System.out.print("근무처를 입력하세요 : ");
            cr.setCompanyName(typing.nextLine());
            System.out.print("담당업무를 입력하세요. : ");
            cr.setJobTitle(typing.nextLine());
            System.out.print("근속연수를 입력하세요. : ");
            cr.setEmployYears(typing.nextLine());

            String outline = "입력하신 정보 일람입니다. \n     ▶ 근무기간 : " + cr.getWorkPeriod()
                    + "\n   ▶ 근무처 : " + cr.getCompanyName()
                    + "\n   ▶ 담당업무 : " + cr.getJobTitle()
                    + "\n   ▶ 근속연수 : " + cr.getEmployYears()
                    + "입니다. \n 맞으면 Y (추가입력) , 틀리면 N : , 자기소개 입력 Q : ";

            System.out.println(outline);

            while(true) {
                setRetryStr(typing.nextLine());

                boolean checker = (!"Y".equalsIgnoreCase(getRetryStr()) && !"N".equalsIgnoreCase(getRetryStr()) && !"Q".equalsIgnoreCase(getRetryStr())) ? true : false;

                if (checker) {
                    System.out.println("Y , N , Q만 입력 가능합니다");
                    continue;
                }else{
                    break;
                }
            }
            if("Y".equalsIgnoreCase(getRetryStr())){ carerList.add(cnt,cr); cnt++; }
            if("Q".equalsIgnoreCase(getRetryStr())){ carerList.add(cnt,cr); break; }

        }
        return false;
    }


    /**
    * @methodName   : getIntroduceInfo
    * @author       : Jiyong Jung
    * @date         : 2023-09-28 :: 오후 7:52
    * @desc         : 자기소개 입력
    * ===========================================================
    * DATE              AUTHOR             NOTE
    * -----------------------------------------------------------
    * 2023-09-28 :: 오후 7:52        Jiyong Jung       최초 생성
    */
    public static String getIntroduceInfo() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Scanner typing = new Scanner(System.in);

        String oneLine;

        System.out.println("/************ 자기소개 입력 종료는 엔터 두번 **************/");
        while( (oneLine = br.readLine()).length() > 0 ) {
            sb.append(oneLine).append("\n");
        }

        String outline = "입력하신 자기소개 일람입니다.    ▶  : \n" + sb + " \n 입니다. \n 맞으면 Y, 틀리면 N (재입력) : ";

        System.out.println(outline);

        while(true) {
            setRetryStr(typing.nextLine());

            if (!"Y".equalsIgnoreCase(getRetryStr()) && !"N".equalsIgnoreCase(getRetryStr())) {
                System.out.println("Y 또는 N만 입력 가능합니다");
                continue;
            }else{
                break;
            }
        }

        return sb.toString();
    }

}
