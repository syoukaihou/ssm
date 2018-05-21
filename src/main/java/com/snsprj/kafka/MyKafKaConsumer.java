package com.snsprj.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author SKH
 * @program ssm
 * @description
 * @date 2018-05-21 16:09
 **/
public class MyKafKaConsumer implements MessageListener<Integer, String> {

    @Override
    public void onMessage(ConsumerRecord<Integer, String> record) {

        System.out.println("获取kafka消息===>" + record);
    }

}
