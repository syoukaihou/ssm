package com.snsprj.test;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestResourceBundle {

    private Logger logger = LoggerFactory.getLogger(TestResourceBundle.class);

    private static final String LANGUAGE_ZH = "zh";

    private static final String CITY_CN = "CN";

    private static final String LANGUAGE_EN = "en";

    private static final String CITY_US = "US";

    @Test
    public void testLng() {

        this.testLngDefault();
        this.testLngZH();
        this.testLngEN();

    }

    private void testLngDefault() {

        String baseName = "language.product";

        Locale localeDefault = Locale.getDefault();
        java.util.ResourceBundle rbDefault = ResourceBundle.getBundle(baseName, localeDefault);
        String result = rbDefault.getString("aaa");
        Assert.assertEquals("你好", result);
        logger.info("===> result is " + result);
    }

    private void testLngZH() {

        String baseName = "language.product";

        Locale localeZH = new Locale(LANGUAGE_ZH, CITY_CN);
        java.util.ResourceBundle rbZH = ResourceBundle.getBundle(baseName, localeZH);

        String result = rbZH.getString("aaa");
        Assert.assertEquals("你好", result);
        logger.info("===> result is " + result);
    }

    private void testLngEN() {

        String baseName = "language.product";

        Locale localeEN = new Locale(LANGUAGE_EN, CITY_US);
        java.util.ResourceBundle rbEN = ResourceBundle.getBundle(baseName, localeEN);

        String result = rbEN.getString("aaa");
        Assert.assertEquals("hello", result);
        logger.info("===> result is " + result);
    }


}
