package com.snsprj.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author SKH
 * @program ssm
 * @description Kafka Producer
 * @date 2018-05-18 17:53
 **/
public class MyKafkaProducer {

    private Properties props;

    public MyKafkaProducer(Properties props) {
        super();
        this.props = props;
    }

    public Properties getProperties() {
        return props;
    }

    public void setProperties(Properties props) {
        this.props = props;
    }

    public void sendMessage(String msg) {

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
            props.getProperty("topic"),
            msg);
        producer.send(record);

        producer.close();

    }

}
