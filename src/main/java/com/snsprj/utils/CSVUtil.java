package com.snsprj.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CSVUtil {

    private static Logger logger = LoggerFactory.getLogger(CSVUtil.class);

    /**
     *
     * 解析csv文件
     *
     * @param headers
     * @param localFilePath
     * @return
     * @throws IOException
     */
    public static List<String[]> parseCSVFile(String[] headers, String localFilePath) throws IOException {

        File file = new File(localFilePath);

        return parseCSVFile(headers,file);
    }

    /**
     *
     * 解析csv文件,编码格式utf-8
     *
     * @param headers
     * @param file
     * @return
     * @throws IOException
     */
    public static List<String[]> parseCSVFile(String[] headers, File file) throws IOException {

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headers);
        CSVParser parser = CSVParser
          .parse(file, Charset.forName("UTF-8"), csvFormat);

        List<String[]> resultList = new ArrayList<>();

        int arrLength = headers.length;
        for (CSVRecord record : parser) {
            if (StringUtils.equals(headers[0], record.get(headers[0]))) {
                continue;
            }

            String[] recordArr = new String[arrLength];
            for (int index = 0; index < arrLength; index++) {
                String value = record.get(headers[index]);
                recordArr[index] = value;
                logger.info("====> value is " + value);
            }
            resultList.add(recordArr);
        }
        parser.close();
        return resultList;
    }
}
