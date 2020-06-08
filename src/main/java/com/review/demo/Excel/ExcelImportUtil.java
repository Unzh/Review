package com.review.demo.Excel;

import com.review.demo.Excel.model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

public class ExcelImportUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportUtil.class);

    @Resource
    private UserDao userDao;

    private Workbook getWorkBook(InputStream is, String fileName) {
        try {
            if (fileName == null) {
                return null;
            }
            if (fileName.endsWith(".xls")) {
                return new HSSFWorkbook(is);
            } else {
                return new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    private void importUserResource(InputStream is, String fileName) {
        Workbook wb = getWorkBook(is, fileName);
        if (wb == null) {
            return;
        }
        Sheet workSheet = wb.getSheetAt(0);
        User user = new User();
        for (int i = 1; ; i++) {
            Row row = workSheet.getRow(i);
            if (row == null) {
                break;
            }
            user.setName(row.getCell(0).getStringCellValue());
            user.setEmployeeId(row.getCell(1).getStringCellValue());
            user.setPost(row.getCell(2).getStringCellValue());
            userDao.insert(user);
        }
    }
}
