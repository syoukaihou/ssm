package com.snsprj.test;

import org.junit.Assert;
import org.junit.Test;

import com.snsprj.utils.RegularExpressionUtil;

/**
 * 测试正则表达式工具类
 * 
 * @author skh
 * @Date 2017年11月21日
 *
 */
public class TestRegEx {

    @Test
    public void testMail() {

        String mail1 = "xiaoA_011@baidu.com.cn";
        boolean result = RegularExpressionUtil.isMatch(mail1, RegularExpressionUtil.REGEX_MAIL);
        Assert.assertEquals(result, true);

        String mail2 = "2222_AAA@qq.com";
        boolean result2 = RegularExpressionUtil.isMatch(mail2, RegularExpressionUtil.REGEX_MAIL);
        Assert.assertEquals(result2, true);

        String mail3 = "AAA.22@qq.com";
        boolean result3 = RegularExpressionUtil.isMatch(mail3, RegularExpressionUtil.REGEX_MAIL);
        Assert.assertEquals(result3, true);
    }
}
