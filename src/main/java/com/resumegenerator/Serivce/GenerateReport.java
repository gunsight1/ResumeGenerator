package com.resumegenerator.Serivce;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

public class GenerateReport {
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
