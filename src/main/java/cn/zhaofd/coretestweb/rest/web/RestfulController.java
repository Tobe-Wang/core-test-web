/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.rest.web;

import cn.zhaofd.core.base.ObjectUtil;
import cn.zhaofd.coretestweb.rest.dto.Customer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public Customer postEntity(@RequestBody Customer customer) {
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
    public Customer postForm(@ModelAttribute Customer customer) {
        customer.setFirstName("保存form成功");
        return customer;
    }

    /**
     * 修改对象
     *
     * @param customer 修改对象
     * @return 修改后对象
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer putEntity(@RequestBody Customer customer) {
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
        if (ObjectUtil.exists(id)) {
            return 1;
        } else {
            return 0;
        }
    }
}
