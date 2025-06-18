/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.rest.web;

import cn.zhaofd.core.base.ObjectUtil;
import cn.zhaofd.core.base.StringUtil;
import cn.zhaofd.coretestweb.core.exception.HttpException;
import cn.zhaofd.coretestweb.rest.dto.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        // 输入参数验证
        if (!ObjectUtil.exists(id) || StringUtil.isNullOrTrimEmpty(firstName) || StringUtil.isNullOrTrimEmpty(lastName)) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }

        List<Customer> list = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(id);
        customer1.setFirstName(firstName);
        customer1.setLastName(lastName);
        list.add(customer1);
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
     * @return 保存后对象
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer postEntity(@RequestBody Customer customer) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }

        customer.setFirstName("保存json成功");
        return customer;
    }

    /**
     * 保存对象
     *
     * @param customer 保存对象
     * @return 保存后对象
     */
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer postForm(@ModelAttribute Customer customer) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }

        customer.setFirstName("保存form成功");
        return customer;
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 状态码
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file) {
        // 输入参数验证
        if (file == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "文件为空");
        }

        return "1";
    }

    /**
     * 修改对象
     *
     * @param customer 修改对象
     * @return 修改后对象
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer putEntity(@RequestBody Customer customer) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }

        customer.setFirstName("修改json成功");
        return customer;
    }

    /**
     * 修改对象
     *
     * @param customer 修改对象
     * @return 修改后对象
     */
    @PutMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer putForm(@ModelAttribute Customer customer) {
        // 输入参数验证
        if (customer == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST.value(), "接口参数不能为空");
        }

        customer.setFirstName("修改form成功");
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
