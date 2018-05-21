package com.snsprj.controller;

import com.alibaba.fastjson.JSONObject;
import com.snsprj.common.RedisUtil;
import com.snsprj.common.ServerResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/test")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping(value = "log")
    @ResponseBody
    public String testLog(HttpServletRequest request) {

        String jsonStr = request.getParameter("jsonData");

        logger.info("jsonStr is " + jsonStr);

        for (int i = 0; i < 10000; i++) {
            logger.debug("------> test debug log");
            logger.info("------> test info log");
            logger.error("------> test error log");
        }

        return "OK";
    }


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


    @RequestMapping(value = "/httpclient", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<JSONObject> testHttpClientPost(
        @RequestParam(name = "username") String username,
        @RequestParam(name = "password") String password) {

        logger.info("username is " + username);
        logger.info("password is " + password);

        JSONObject responseData = new JSONObject();
        responseData.put("username", "用户名：" + username);
        responseData.put("password", "密码：" + password);

        return ServerResponse.createBySuccess(responseData);
    }

    @RequestMapping(value = "/cookie")
    @ResponseBody
    public String testCookie(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();

        JSONObject jsonObject = new JSONObject();

        if (null == cookies) {
            logger.info("=========> cookie is null");
        } else {

            for (Cookie cookie : cookies) {
                jsonObject.put(cookie.getName(),cookie.getValue());
            }
        }

        this.addCookie(response, "123123");

        return jsonObject.toJSONString();
    }

    private void addCookie(HttpServletResponse response, String sessionId){

        String domain = "snsprj.cn";
        //创建新cookie
        Cookie cookie = new Cookie("JSESSIONID",sessionId);

        // 设置存在时间为20分钟
        cookie.setMaxAge(20 * 60);
        cookie.setPath("/");//设置作用域
        cookie.setDomain(domain);
        response.addCookie(cookie);//将cookie添加到response的cookie数组中返回给客户端

    }

    @RequestMapping(value = "/spring/session")
    @ResponseBody
    public String testSpringSession(HttpServletRequest request){

        JSONObject jsonObject = new JSONObject();

        Cookie[] cookies = request.getCookies();

        HttpSession session = request.getSession();

        String sessionId = session.getId();
        logger.info("====>session is " + sessionId);

        if (null == cookies) {

            logger.info("=========> cookie is null");
        } else {

            for (Cookie cookie : cookies) {
                jsonObject.put(cookie.getName(),cookie.getValue());
            }
        }

        return jsonObject.toJSONString();
    }

    @RequestMapping(value = "login")
    @ResponseBody
    public String testIAM(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession(true);

        session.setAttribute("userName", "admin");
        session.setAttribute("userId",1);


        RedisUtil.set("","", 3000L);

        this.addCookie(response, session.getId());

        return "login success!";
    }


    @RequestMapping(value = "/html",method = {RequestMethod.GET})
    public String testHtml(){

        return "index";
    }

    @RequestMapping(value = "/cros")
    @ResponseBody
    public ServerResponse<String> testCros(){

        logger.info("====> cros run!");

        return ServerResponse.createBySuccess();
    }
}
