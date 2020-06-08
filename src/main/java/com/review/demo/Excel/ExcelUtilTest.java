package com.review.demo.Excel;

import com.review.demo.Excel.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtilTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtilTest.class);
    public static void main(String[] args) {
        ExcelExportUtil util = new ExcelExportUtil();
        List<String> titleList = new ArrayList<>();
        titleList.add("名字");
        titleList.add("工号");
        titleList.add("岗位");
        List<User> dataList = new ArrayList<>();
        dataList.add(new User("z3", "0211", "部门主管"));
        dataList.add(new User("l4", "0455", "部门经理"));
        dataList.add(new User("w5", "0982", "开发主管"));
        try {
            util.export(titleList, dataList, "导出", "C:\\Users\\Hao.zhou05\\Desktop\\export.xls");
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
