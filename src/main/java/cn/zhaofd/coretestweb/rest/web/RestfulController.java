/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.rest.web;

import cn.zhaofd.coretestweb.rest.dto.Customer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
public class RestfulController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getObj(@RequestParam Integer id) {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("zhao");
        customer.setLastName("fang dong");
        return customer;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer postObj(@RequestBody Customer customer) {
        customer.setFirstName("保存成功");
        return customer;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer putObj(@RequestBody Customer customer) {
        customer.setFirstName("修改成功");
        return customer;
    }

    @DeleteMapping(value = "/{id}")
    public String deleteObj(@PathVariable Integer id) {
        return "删除成功";
    }
}
