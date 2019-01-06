package com.swift.util;

import org.junit.Test;
import org.springframework.util.Assert;

public class StringUtilsTest {

    @Test
    public void toUpperCamel() {

        Assert.isTrue("Find".equals(StringUtils.toUpperCamel("find")));

    }

    @Test
    public void toLowerCamel() {

        Assert.isTrue("find".equals(StringUtils.toLowerCamel("Find")));

    }

    @Test
    public void toUnderscore() {
    }

    @Test
    public void trimLeft() {
    }
}
