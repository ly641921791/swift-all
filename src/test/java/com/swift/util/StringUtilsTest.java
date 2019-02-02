package com.swift.util;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

class StringUtilsTest {

    @Test
    void toLowerCamel() {
        String[] resources = {"SelectById"};
        String[] targets = {"selectById"};

        for (int i = 0; i < resources.length; i++) {
            String result = StringUtils.toLowerCamel(resources[i]);
            Assert.isTrue(targets[i].equals(result), "toLowerCamel ：want a string <" + targets[i] + "> but <" + result + ">");
        }
    }
}