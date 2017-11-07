package com.snsprj.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * properties文件属性读取工具类，依赖spring。
 * @author skh
 * @Date 2017年11月7日
 *
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer{

	private static Map<String,String> propertyMap;
	
	@Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertyMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    public static String getProperty(String name) {
        return propertyMap.get(name);
    }
}
