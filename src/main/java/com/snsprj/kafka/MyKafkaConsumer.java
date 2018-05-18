package com.snsprj.kafka;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

/**
 * @author SKH
 * @program ssm
 * @description Kafka Consumer
 * @date 2018-05-18 17:56
 **/
public class MyKafkaConsumer {

    private Properties props;

    public MyKafkaConsumer(Properties props) {
        super();
        this.props = props;
    }

    public Properties getProps() {
        return props;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

    public String receive() {

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(props.getProperty("topic")));

        String msg = "";
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                msg += consumerRecord.value();
            }
            consumer.close();
            return msg;
        }
    }

}
