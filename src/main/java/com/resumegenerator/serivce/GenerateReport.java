package com.resumegenerator.serivce;

import com.resumegenerator.VO.Carrer;
import com.resumegenerator.VO.Education;
import com.resumegenerator.VO.PersonInfo;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

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

    //로컬
    public static void generate(PersonInfo person, ArrayList<Education> eduList, ArrayList<Carrer> carrerList,String introduce) throws Exception {

        String imgPath = "C:\\" + person.getPhotoKey() + ".jpg";

        //poi 라이브러리
        XSSFWorkbook wb = new XSSFWorkbook();

        //생성된 줄 개수를 측정
        int GenerateRowsCnt = 0;

        //시트 생성
        Sheet personSheet = wb.createSheet("이력서");
        Sheet IntroduceSheet = wb.createSheet("자기소개서");

        // 이미지 파일 로드
        InputStream inputStream = new FileInputStream(imgPath);
        byte[] bytes = IOUtils.toByteArray(inputStream);
        int pictureIdx = wb.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_JPEG);
        inputStream.close();

        XSSFCreationHelper helper = wb.getCreationHelper();
        XSSFDrawing drawing = (XSSFDrawing) personSheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = helper.createClientAnchor();

        // 이미지를 출력할 CELL 위치 선정
        anchor.setCol1(1);
        anchor.setRow1(2);

        // 이미지 그리기
        XSSFPicture pict = drawing.createPicture(anchor, pictureIdx);

        // 이미지 사이즈 비율 설정
        //pict.resize();

        //개인정보 타이틀
        Row PersonTitleRow = personSheet.createRow(GenerateRowsCnt);
        GenerateRowsCnt++;
        //개인정보 값
        Row PersonValueRow = personSheet.createRow(GenerateRowsCnt);
        GenerateRowsCnt++;
        //개인정보 값 세팅
        setPersonCellValue(PersonTitleRow,PersonValueRow,person);

        //학력사항 타이틀
        Row EduTitleRow = personSheet.createRow(GenerateRowsCnt);
        GenerateRowsCnt++;
        //학력사항 값세팅, 생성된 줄 개수 가져옴
        GenerateRowsCnt = setEduCellValue(EduTitleRow,personSheet,eduList,GenerateRowsCnt);

        //경력사항 타이틀
        Row CarrerTitleRow = personSheet.createRow(GenerateRowsCnt);
        GenerateRowsCnt++;
        //경력사항 값세팅, 생성된 줄 개수 가져옴
        GenerateRowsCnt = setCarrerCellValue(CarrerTitleRow,personSheet,carrerList,GenerateRowsCnt);

        //자기소개 타이틀
        Row IntroduceTitleRow = IntroduceSheet.createRow(0);
        Cell IntroduceTitleCell = IntroduceTitleRow.createCell(0);
        IntroduceTitleCell.setCellValue("자기소개서");

        //자기소개 값세팅
        Row IntroduceValueRow = IntroduceSheet.createRow(1);
        Cell IntroduceValueCell = IntroduceValueRow.createCell(0);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);

        IntroduceValueCell.setCellValue(introduce);
        IntroduceValueCell.setCellStyle(cellStyle);


        //시트 스타일 정의
        CellStyle RowStyle = wb.createCellStyle();
        setPoiStyle(wb,RowStyle);

        PersonTitleRow.setRowStyle(RowStyle);

        //파일 export
        File fb = new File("C:\\");
        String path = fb.getAbsolutePath();
        String fileLoc = path + "report.xlsx";

        FileOutputStream fos = new FileOutputStream(fileLoc);
        wb.write(fos);
        wb.close();

        System.err.println("이력서가 생성되었습니다.");
    }

    private static void setPoiStyle(XSSFWorkbook wb, CellStyle style) {
        style.setVerticalAlignment(VerticalAlignment.CENTER); //세로중앙정렬
        style.setAlignment(HorizontalAlignment.CENTER);//가로중앙정렬

        XSSFFont font = wb.createFont();
        font.setFontHeight((short) (20*20));
        style.setFont(font);

    }

    /**
     * @param :  (),
     * @param
     * @methodName : setPersonCellValue
     * @author : Jiyong Jung
     * @date : 2023-10-02 :: 오후 3:41
     * @desc : 개인정보 값 세팅. 하드코딩 스타일
     * ===========================================================
     * DATE              AUTHOR             NOTE
     * -----------------------------------------------------------
     * 2023-10-02 :: 오후 3:41        Jiyong Jung       최초 생성
     */
    private static void setPersonCellValue(Row personTitleRow, Row personValueRow, PersonInfo person) {

        Cell PersonTitleCell = personTitleRow.createCell(0);

        PersonTitleCell = personTitleRow.createCell(0);
        PersonTitleCell.setCellValue("사진");

        PersonTitleCell = personTitleRow.createCell(1);
        PersonTitleCell.setCellValue("이름");

        PersonTitleCell = personTitleRow.createCell(2);
        PersonTitleCell.setCellValue("이메일");

        PersonTitleCell = personTitleRow.createCell(3);
        PersonTitleCell.setCellValue("주소");

        PersonTitleCell = personTitleRow.createCell(4);
        PersonTitleCell.setCellValue("전화번호");

        PersonTitleCell = personTitleRow.createCell(5);
        PersonTitleCell.setCellValue("생년월일");

        //사진
        Cell PersonValueCell = personValueRow.createCell(0);
        PersonValueCell.setCellValue(person.getPhotoKey());

        //이름
        PersonValueCell = personValueRow.createCell(1);
        PersonValueCell.setCellValue(person.getName());

        //이메일
        PersonValueCell = personValueRow.createCell(2);
        PersonValueCell.setCellValue(person.getEmail());

        //주소
        PersonValueCell = personValueRow.createCell(3);
        PersonValueCell.setCellValue(person.getAddr());

        //전화번호
        PersonValueCell = personValueRow.createCell(4);
        PersonValueCell.setCellValue(person.getPhone());

        //생년월일
        PersonValueCell = personValueRow.createCell(5);
        PersonValueCell.setCellValue(person.getBirth());

    }
    /**
    * @methodName   : setEduCellValue
    * @author       : Jiyong Jung
    * @date         : 2023-10-02 :: 오후 3:41
    * @param        :  (),
    * @desc         : 교육정보 값 세팅. 교육정보 개수에 따라 가변적으로 열 추가
    * ===========================================================
    * DATE              AUTHOR             NOTE
    * -----------------------------------------------------------
    * 2023-10-02 :: 오후 3:41        Jiyong Jung       최초 생성
    */
    private static int setEduCellValue(Row eduTitleRow, Sheet sheet, ArrayList<Education> eduList, int generateRowsCnt) {

        int GenEduValCnt = generateRowsCnt; // 여태 생성된 줄 개수를 가져옴.

        Cell EduTitleCell = eduTitleRow.createCell(0);
        EduTitleCell.setCellValue("졸업년도");

        EduTitleCell = eduTitleRow.createCell(1);
        EduTitleCell.setCellValue("학교명");

        EduTitleCell = eduTitleRow.createCell(2);
        EduTitleCell.setCellValue("전공");

        EduTitleCell = eduTitleRow.createCell(3);
        EduTitleCell.setCellValue("졸업여부");

        for(Education edu : eduList){
            Row EduValueRow = sheet.createRow(GenEduValCnt);
            //졸업년도
            Cell EduValueCell = EduValueRow.createCell(0);
            EduValueCell.setCellValue(edu.getGraduationYear());

            //학교명
            EduValueCell = EduValueRow.createCell(1);
            EduValueCell.setCellValue(edu.getSchoolName());

            //전공
            EduValueCell = EduValueRow.createCell(2);
            EduValueCell.setCellValue(edu.getMajor());

            //졸업여부
            EduValueCell = EduValueRow.createCell(3);
            EduValueCell.setCellValue(edu.getGraduationStatus());

            GenEduValCnt++;
        }
        return GenEduValCnt;
    }

    /**
    * @methodName   : setCarrerCellValue
    * @author       : Jiyong Jung
    * @date         : 2023-10-02 :: 오후 3:42
    * @param        :  (),
    * @desc         : 경력정보 세팅. 타이틀을 하드코딩 하지않고 가변적으로 처리한다. (값도 가변적 처리하면 좋을듯)
    * ===========================================================
    * DATE              AUTHOR             NOTE
    * -----------------------------------------------------------
    * 2023-10-02 :: 오후 3:42        Jiyong Jung       최초 생성
    */
    private static int setCarrerCellValue(Row carrerTitleRow, Sheet sheet, ArrayList<Carrer> carrerList, int generateRowsCnt) {
        int GenCarrerValCnt = generateRowsCnt;
        int CarrerTitleRowCnt = 0;

        for(Field field : Carrer.class.getDeclaredFields()){
            Cell CarrerTitleCell = carrerTitleRow.createCell(CarrerTitleRowCnt);
            String name = field.getName();
            CarrerTitleCell.setCellValue(name);
            CarrerTitleRowCnt++;
        }

        for(Carrer carrer : carrerList){
            Row CarrerValueRow = sheet.createRow(GenCarrerValCnt);

            //근무기간
            Cell CarrerValueCell = CarrerValueRow.createCell(0);
            CarrerValueCell.setCellValue(carrer.getWorkPeriod());

            //근무처
            CarrerValueCell = CarrerValueRow.createCell(1);
            CarrerValueCell.setCellValue(carrer.getCompanyName());

            //담당업무
            CarrerValueCell = CarrerValueRow.createCell(2);
            CarrerValueCell.setCellValue(carrer.getJobTitle());

            //근속연수
            CarrerValueCell = CarrerValueRow.createCell(3);
            CarrerValueCell.setCellValue(carrer.getEmployYears());

            GenCarrerValCnt++;
        }

        return GenCarrerValCnt;
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

}
