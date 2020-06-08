package com.review.demo.Excel;

import com.review.demo.Excel.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Title : ExcelUtil.java
 * Package : com.review.demo.Excel
 * Description : <p>
 * 利用POI实现Excel的导入导出
 * poi -V:3.17;commons-io -V:2.4
 * </p>
 * Create on : 2020/6/5
 */
public class ExcelExportUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelExportUtil.class);

    public void responseExport(HttpServletResponse response, List<String> title, List<User> data, String fileName) throws IOException {
        OutputStream os = null;
        Workbook workbook = getWorkbook(fileName);
        Sheet sheet = workbook.createSheet("sheet1");
        try {
            // 设定输出文件头
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("gb2312"), "iso8859-1") + ".xls");
            // 定义输出类型
            response.setContentType("application/msexcel");
            //创建头文件
            createSheetHeader(workbook, sheet, title);
            //填充数据
            fillData(sheet, data);
            os = response.getOutputStream();
            workbook.write(os);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            workbook.close();
        }
    }


    public void export(List<String> title, List<User> data, String sheetName, String filePath) throws IOException {
        Workbook workbook = getWorkbook(filePath);
        Sheet sheet = workbook.createSheet(sheetName);
        createSheetHeader(workbook, sheet, title);
        fillData(sheet, data);
        File file = new File(filePath);
        FileOutputStream stream = null;
        try {
            file.createNewFile();
            stream = FileUtils.openOutputStream(file);
            workbook.write(stream);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            stream.close();
        }
    }

    private void createSheetHeader(Workbook workbook, Sheet sheet, List<String> titles) {
        if (!CollectionUtils.isEmpty(titles)) {
            //设置默认长度
            sheet.setDefaultColumnWidth(25);
            int colIndex = 0;
            Row row = sheet.createRow(0);
            CellStyle cs = workbook.createCellStyle();
            //设置居中
            cs.setAlignment(HorizontalAlignment.CENTER);
            //设置颜色
            cs.setFillForegroundColor(new HSSFColor.PALE_BLUE().getIndex());
            cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            for (String title : titles) {
                Cell cell = row.createCell(colIndex++);
                cell.setCellValue(title);
                cell.setCellStyle(cs);
            }
        }
    }

    private void fillData(Sheet sheet, List<User> data) {
        if (!CollectionUtils.isEmpty(data)) {
            int rowIndex = 1;
            for (User user : data) {
                Row row = sheet.createRow(rowIndex++);
                Cell cell = row.createCell(0);
                cell.setCellValue(user.getName());
                cell = row.createCell(1);
                cell.setCellValue(user.getEmployeeId());
                cell = row.createCell(2);
                cell.setCellValue(user.getPost());
            }
        }
    }


    private Workbook getWorkbook(String fileName) {
        if (fileName.endsWith("xls")) {
            return new HSSFWorkbook();
        }
        return new XSSFWorkbook();
    }


}
