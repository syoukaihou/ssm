package com.snsprj.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author SKH
 * @program ssm
 * @description
 * @date 2018-05-21 16:09
 **/
public class MyKafKaConsumer implements MessageListener<Integer, String> {

    private Logger logger = LoggerFactory.getLogger(MyKafKaConsumer.class);

    @Override
    public void onMessage(ConsumerRecord<Integer, String> record) {

        logger.info("获取kafka消息===>" + record);
    }

}
