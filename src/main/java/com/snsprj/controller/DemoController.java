package com.snsprj.controller;

import com.snsprj.common.ServerResponse;
import com.snsprj.dto.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by skh on 2017/7/3.
 */
@Controller
@RequestMapping("/test/")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    ServletContext context;

    /**
     * 测试文件上传
     *
     * @param request HttpServletRequest
     * @return ServerResponse
     */
    @RequestMapping(value = "upload", method = {RequestMethod.POST})
    @ResponseBody
    public ServerResponse<Object> testFileUpload(HttpServletRequest request)
        throws IOException {

        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver =
            new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator<String> iterable = multiRequest.getFileNames();

            while (iterable.hasNext()) {
                MultipartFile file = multiRequest.getFile(iterable.next());

                if (file != null) {

                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    logger.info("====> file name is "+ myFileName);

                    InputStream inputStream = file.getInputStream();

                    String[] headers = {"id","type","value"};
                    CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(headers);
                    CSVParser parser =  CSVParser.parse(inputStream, Charset.forName("GBK"),csvFormat);

                    for (CSVRecord record : parser)
                    {
                        if (StringUtils.equals(headers[0],record.get(headers[0]))){
                            continue;
                        }
                        Integer id = Integer.valueOf(record.get("id"));
                        String type = record.get("type");
                        String value = record.get("value");

                        logger.info("====> id is " + id);
                        logger.info("====> type is " + type);
                        logger.info("====> value is " + value);
                    }
                    logger.info("file name is " + file.getOriginalFilename());
                    logger.info("param-->" + file.getName());
                }
            }
        } else{
            logger.error("====> upload file is null!");
        }
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "upload2",method = {RequestMethod.POST})
    @ResponseBody
    public ServerResponse<String> uploadFile(@RequestParam("file")MultipartFile uploadFile,
      HttpSession session)
      throws IOException {

        if (null != uploadFile){
            String fileName = uploadFile.getOriginalFilename();
            logger.info("====>fileName is " + fileName);

            String filePath = session.getServletContext().getRealPath("/");
            logger.info("====> file path is " + filePath);

            File file = new File(filePath,fileName);

            uploadFile.transferTo(file);
        } else{
            logger.error("====> upload file is null!");
        }
        return ServerResponse.createBySuccess();
    }
    /**
     * 返回页面
     *
     * @return view
     */
    @RequestMapping(value = "html",method = {RequestMethod.GET})
    public String testHtml(){

        return "index";
    }

    @RequestMapping(value = "response")
    @ResponseBody
    public ServerResponse<String> testResponse(){

        return  ServerResponse.createBySuccess();
    }

}
