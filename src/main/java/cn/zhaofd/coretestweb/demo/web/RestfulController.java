/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.demo.web;

import cn.zhaofd.core.base.ObjectUtil;
import cn.zhaofd.core.json.JacksonUtil;
import cn.zhaofd.core.spring.validation.ValidationUtil;
import cn.zhaofd.coretestweb.core.exception.HttpException;
import cn.zhaofd.coretestweb.demo.dto.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * RESTful接口Controller
 */
@RestController
@RequestMapping("/rest")
public class RestfulController {
    /**
     * 获取单个对象
     *
     * @param id 主键
     * @return Customer
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getById(@PathVariable Integer id) {
        // 输入参数验证
        if (!ObjectUtil.exists(id)) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "参数id不能为空");
        }

        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("zhao王");
        customer.setLastName("fang dong");
        customer.setRegTime(new Date());
        return customer;
    }

    /**
     * 获取列表
     *
     * @param params 查询参数
     * @return 列表
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getList(@RequestParam Map<String, Object> params) {
        Integer id = ObjectUtil.convert(params.get("id"), Integer.class);
        String firstName = ObjectUtil.convert(params.get("firstName"), String.class);
        String lastName = ObjectUtil.convert(params.get("lastName"), String.class);

        List<Customer> list = new ArrayList<>();
        if (ObjectUtil.exists(id)) {
            Customer customer1 = new Customer();
            customer1.setId(id);
            customer1.setFirstName(firstName);
            customer1.setLastName(lastName);
            list.add(customer1);
        }
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setFirstName("zhao王");
        customer2.setLastName("fang dong");
        list.add(customer2);

        return list;
    }

    /**
     * 保存对象
     *
     * @param customer 保存对象
     * @param errors   Errors对象
     * @return 保存后对象
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer postEntity(@Valid @RequestBody Customer customer, Errors errors) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }
        if (errors.hasErrors()) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), ValidationUtil.getFieldErrorMsg(errors));
        }

        customer.setFirstName("保存json成功");
        return customer;
    }

    /**
     * 保存对象
     *
     * @param customer 保存对象
     * @param errors   Errors对象
     * @return 保存后对象
     */
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer postForm(@Valid @ModelAttribute Customer customer, Errors errors) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }
        if (errors.hasErrors()) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), ValidationUtil.getFieldErrorMsg(errors));
        }

        customer.setFirstName("保存form成功");
        return customer;
    }

    /**
     * 修改对象
     * <br />全量替换更新
     *
     * @param customer 修改对象
     * @param errors   Errors对象
     * @return 修改后对象
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer putEntity(@Valid @RequestBody Customer customer, Errors errors) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }
        if (errors.hasErrors()) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), ValidationUtil.getFieldErrorMsg(errors));
        }

        customer.setFirstName("修改json成功");
        return customer;
    }

    /**
     * 修改对象
     * <br />全量替换更新
     *
     * @param customer 修改对象
     * @param errors   Errors对象
     * @return 修改后对象
     */
    @PutMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer putForm(@Valid @ModelAttribute Customer customer, Errors errors) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }
        if (errors.hasErrors()) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), ValidationUtil.getFieldErrorMsg(errors));
        }

        customer.setFirstName("修改form成功");
        return customer;
    }

    /**
     * 修改对象
     * <br />部分更新
     *
     * @param id     主键
     * @param params 修改的字段数据
     * @return 修改后对象
     */
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer patchEntity(@PathVariable Integer id, @RequestBody Map<String, Object> params) {
        // 输入参数验证
        if (!ObjectUtil.exists(id)) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "参数id不能为空");
        }
        if (params == null || params.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "修改的字段数据不能为空");
        }

        // 模拟数据库查询到的数据
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("zhao王");
        customer.setLastName("fang dong");
        // 部分更新
        Customer patchedCustomer = JacksonUtil.convertValue(params, Customer.class);
        Class<Customer> CustomerClazz = Customer.class;
        for (String key : params.keySet()) {
            try {
                Field field = CustomerClazz.getDeclaredField(key);
                field.setAccessible(true);
                field.set(customer, field.get(patchedCustomer));
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
                throw new HttpException(HttpStatus.BAD_REQUEST.value(), "对象修改的字段名(“ + key + ”)不匹配");
            }
        }

        return customer;
    }

    /**
     * 删除对象
     *
     * @param id 主键
     * @return 删除数量
     */
    @DeleteMapping(value = "/{id}")
    public Integer deleteById(@PathVariable Integer id) {
        // 输入参数验证
        if (!ObjectUtil.exists(id)) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "参数id不能为空");
        }

        return 1;
    }
}
