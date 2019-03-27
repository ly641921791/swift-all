package com.github.ly641921791.swift.core.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

/**
 * @author ly
 * @since 1.0.0
 **/
public class StringUtilsTest {

    @Test
    public void testToUpperCamel() {
        Assert.assertEquals("SaveAll", StringUtils.toUpperCamel("saveAll"));
    }

    @Test
    public void testToLowerCamel() {
        Assert.assertEquals("saveAll", StringUtils.toLowerCamel("SaveAll"));
    }

}
