package com.example;

import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.configuration.XMLConfiguration;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestClass {

    @Test
    public void test() throws Exception {
        StringBuilder sb = new StringBuilder("<configuration>");
        sb.append("<key0></key0>");
        sb.append("<key1>,</key1>");
        sb.append("<key2></key2>");
        sb.append("<key3></key3>");
        sb.append("</configuration>");

        XMLConfiguration config = new XMLConfiguration();
        //config.setDelimiterParsingDisabled(true); // Test succeeds if this is uncommented


        config.load(new StringReader(sb.toString()));

        assertTrue("Configuration has key key0", config.containsKey("key0"));
        assertTrue("Configuration has key key1", config.containsKey("key1"));
        assertTrue("Configuration has key key3", config.containsKey("key3"));

        assertTrue("Configuration has key key2", config.containsKey("key2")); // This one fails
    }

}
