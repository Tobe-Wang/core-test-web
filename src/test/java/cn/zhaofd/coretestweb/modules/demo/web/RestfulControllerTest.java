/*
 * Copyright (c) 2025. Tobe Wang
 */

package cn.zhaofd.coretestweb.modules.demo.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class RestfulControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getById() throws Exception {
        // @formatter:off
        mockMvc.perform(get("/rest/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{\"id\":1,\"firstName\":\"zhao王\",\"lastName\":\"fang dong\"}"));
        // @formatter:on
    }

    @Test
    void deleteById() throws Exception {
        // @formatter:off
        mockMvc.perform(delete("/rest/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
        // @formatter:on
    }
}
