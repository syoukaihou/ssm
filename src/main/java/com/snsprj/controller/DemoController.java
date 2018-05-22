package com.snsprj.controller;

import com.snsprj.common.ServerResponse;
import com.snsprj.dto.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by skh on 2017/7/3.
 */
@Controller
@RequestMapping("/test")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);


    /**
     * 测试文件上传
     *
     * @param request HttpServletRequest
     * @return ServerResponse
     */
    @RequestMapping(value = "upload", method = {RequestMethod.POST})
    public ServerResponse<Object> testFileUpload(HttpServletRequest request)
        throws IOException, BiffException {

        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver =
            new CommonsMultipartResolver(request.getSession().getServletContext());

        if (multipartResolver.isMultipart(request)) {

            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

            Iterator<String> iterable = multiRequest.getFileNames();

            while (iterable.hasNext()) {
                MultipartFile file = multiRequest.getFile(iterable.next());

                if (file != null) {

                    InputStream inputStream = file.getInputStream();

                    // 获取excel对象
                    Workbook workbook = Workbook.getWorkbook(inputStream);

                    // 获取excel中所有的sheet
                    Sheet[] sheets = workbook.getSheets();

                    Cell cell = null;
                    String cellContent = null;
                    // 遍历sheet
                    for (Sheet sheet : sheets) {

                        // 遍历行
                        for (int rowIndex = 0; rowIndex < sheet.getRows(); rowIndex++) {

                            // 遍历列
                            for (int columnIndex = 0; columnIndex < sheet
                                .getColumns(); columnIndex++) {

                                // 获取第column列、第row行的数据
                                cell = sheet.getCell(columnIndex, rowIndex);
                                cellContent = cell.getContents();
                                System.out.print(cellContent);
                            }
                            System.out.println("");
                        }

                    }

                    logger.info("file name is " + file.getOriginalFilename());
                    logger.info("param-->" + file.getName());
                }
            }
        }

        return ServerResponse.createBySuccess();
    }

    /**
     * 返回页面
     *
     * @return view
     */
    @RequestMapping(value = "/html",method = {RequestMethod.GET})
    public String testHtml(){

        return "index";
    }
}
