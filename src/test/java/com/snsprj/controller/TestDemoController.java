package com.snsprj.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alibaba.fastjson.JSONObject;
import com.snsprj.base.BaseControllerTest;

public class TestDemoController extends BaseControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private DemoController testController;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void testLog() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "xxx");
        jsonObject.put("username", "xxx");
        jsonObject.put("password", "xxx");
        jsonObject.put("dn", "xxx");
        String jsonData = jsonObject.toJSONString();

        String uri = "/test/log";
        try {
            String result =
                    mockMvc.perform(MockMvcRequestBuilders.get(uri).param("jsonData", jsonData))
                            .andReturn().getResponse().getContentAsString();

            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
