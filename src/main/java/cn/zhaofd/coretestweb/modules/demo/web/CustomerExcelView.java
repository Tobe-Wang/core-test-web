/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.modules.demo.web;

import cn.zhaofd.coretestweb.modules.demo.dto.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.List;
import java.util.Map;

/**
 * Excel视图继承AbstractXlsxView示例
 */
public class CustomerExcelView extends AbstractXlsxView {
    /**
     * 构建Excel文档
     *
     * @param model    数据模型
     * @param workbook 工作簿
     * @param request  HTTP请求
     * @param response HTTP相应
     * @throws Exception 异常
     */
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) throws Exception {
        // 创建工作表（Sheet）
        Sheet sheet = workbook.createSheet("顾客列表");
        // 创建行
        Row row1 = sheet.createRow(0);
        // 在第一行内，创建三个单元格，并设置表头
        row1.createCell(0).setCellValue("主键");
        row1.createCell(1).setCellValue("姓");
        row1.createCell(2).setCellValue("名");
        // 行索引
        int rowIdx = 1;
        // 从数据模型中获取数据
        //noinspection unchecked
        List<Customer> customerList = (List<Customer>) model.get("customerList");
        // 遍历数据，写入工作表
        for (Customer customer : customerList) {
            Row row = sheet.createRow(rowIdx);
            row.createCell(0).setCellValue(customer.getId());
            row.createCell(1).setCellValue(customer.getFirstName());
            row.createCell(2).setCellValue(customer.getLastName());
            rowIdx++;
        }
    }
}
