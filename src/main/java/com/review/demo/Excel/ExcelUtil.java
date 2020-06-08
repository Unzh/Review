package com.review.demo.Excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {

    /**
     * 判断Excel版本
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 判断Excel版本
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 获取Excel中单元格的数据
     *
     * @param cell
     * @return
     */
    public static String getStringValue(Cell cell) {
        return cell != null ? cell.getStringCellValue() : StringUtils.EMPTY;
    }

}
