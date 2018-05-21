package com.snsprj.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author SKH
 * @program ssm
 * @description
 * @date 2018-05-18 18:15
 **/
@Controller
public class KafkaController {

    private Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping(value = "/kafka/send")
    @ResponseBody
    public String send(){

        logger.info("====> sending ...");

        kafkaTemplate.sendDefault(" baoge test message!!!");

        return "send message success!";
    }

}
