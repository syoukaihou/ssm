package com.snsprj.test;

import java.util.Map;

import org.junit.Test;

import com.snsprj.utils.XmlResolveUtil;

public class TestXMLResolveUtil {

    @Test
    public void testXml() {

        String strXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><PARAM><DBID>35</DBID><SEQUENCE>atgtca</SEQUENCE><MAXNS>10</MAXNS><MINIDENTITIES>90</MINIDENTITIES><MAXEVALUE>10</MAXEVALUE><USERNAME>admin</USERNAME><PASSWORD>111111</PASSWORD><TYPE>P</TYPE><RETURN_TYPE>2</RETURN_TYPE></PARAM>";
        Map<String, String> data = XmlResolveUtil.xmlToMap(strXML);

        for(Map.Entry<String, String> entry : data.entrySet()){
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
