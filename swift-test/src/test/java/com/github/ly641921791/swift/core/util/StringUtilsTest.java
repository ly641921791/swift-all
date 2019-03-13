package com.github.ly641921791.swift.core.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

/**
 * @author ly
 * @since 2019-03-13 13:26
 **/
public class StringUtilsTest {

    @Test
    public void testToUpperCamel() {
        Assert.state("SaveAll".equals(StringUtils.toUpperCamel("saveAll")));
    }

    @Test
    public void testToLowerCamel() {
        Assert.state("saveAll".equals(StringUtils.toLowerCamel("SaveAll")));
    }

}
