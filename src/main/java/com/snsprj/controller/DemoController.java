package com.snsprj.controller;

import com.snsprj.common.ServerResponse;
import com.snsprj.dto.User;
import com.snsprj.utils.CSVUtil;
import com.snsprj.utils.CompressUtil;
import com.snsprj.utils.SeparatorUtils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
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

    private static final int BUFFER_SIZE = 1024;

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

        String fileSeparator = SeparatorUtils.getFileSeparator();

        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver =
            new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            String timeStamp = String.valueOf(new Date().getTime());
            String targetDirPath =
              request.getSession().getServletContext().getRealPath("/") + timeStamp + fileSeparator;

            Iterator<String> iterable = multiRequest.getFileNames();
            while (iterable.hasNext()) {
                MultipartFile file = multiRequest.getFile(iterable.next());

                if (file != null) {

                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    logger.info("====> file name is "+ myFileName);

                    InputStream inputStream = file.getInputStream();

                    // 解压zip包
                    List<String> filePathList = CompressUtil.unZip(inputStream,targetDirPath);

                    // 解析CSV
                    for (String localFilePath : filePathList) {

                        String[] headers = {"id", "type", "value"};
                        CSVUtil.parseCSVFile(headers, localFilePath);
                    }

                    // 临时文件根目录
                    File localFileDir = new File(targetDirPath);
                    // 删除临时文件目录
                    FileUtils.deleteDirectory(localFileDir);
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
      HttpSession session) throws IOException {

        String fileSeparator = SeparatorUtils.getFileSeparator();

        if (null != uploadFile){
            String fileName = uploadFile.getOriginalFilename();
            logger.info("====>fileName is " + fileName);

            String filePath = session.getServletContext().getRealPath(fileSeparator);
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
