/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.modules.demo.web;

import cn.zhaofd.coretestweb.modules.demo.dto.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Excel导出示例
 */
@Controller
@RequestMapping("/excel")
public class CustomerExcelController {
    /**
     * excel导出顾客信息
     *
     * @return ModelAndView
     */
    @GetMapping("/customer")
    public ModelAndView downloadXml(ModelAndView modelAndView) {
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("zhao王");
        customer1.setLastName("fang dong");
        customer1.setRegTime(new Date());
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("王");
        customer2.setLastName("dong");

        // 绑定数据模型
        modelAndView.addObject("customerList", List.of(customer1, customer2));
        // 绑定视图
        modelAndView.setView(new CustomerExcelView());
        // 返回数据模型和视图
        return modelAndView;
    }
}
