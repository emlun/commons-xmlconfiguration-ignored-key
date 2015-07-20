/*
   Copyright 2015 Emil Lundberg <lundberg.emil@gmail.com>

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.example;

import java.io.IOException;
import java.io.StringReader;

import org.apache.commons.configuration.XMLConfiguration;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestClass {

    public void checkConfiguration(XMLConfiguration config) {
        assertTrue("Configuration has key key0", config.containsKey("key0"));
        assertTrue("Configuration has key key1", config.containsKey("key1"));
        assertTrue("Configuration has key key3", config.containsKey("key3"));

        assertTrue("Configuration has key key2", config.containsKey("key2")); // This one fails
    }

    @Test
    public void testWithNoComma() throws Exception {
        String source = "<configuration><key0></key0><key1></key1><key2></key2><key3></key3></configuration>";
        XMLConfiguration config = new XMLConfiguration();

        config.load(new StringReader(source));
        checkConfiguration(config);
    }

    @Test
    public void testWithOnlyComma() throws Exception {
        String source = "<configuration><key0></key0><key1>,</key1><key2></key2><key3></key3></configuration>";
        XMLConfiguration config = new XMLConfiguration();

        config.load(new StringReader(source));
        checkConfiguration(config);
    }

    @Test
    public void testWithCommaSeparatedList() throws Exception {
        String source = "<configuration><key0></key0><key1>a,b</key1><key2></key2><key3></key3></configuration>";
        XMLConfiguration config = new XMLConfiguration();

        config.load(new StringReader(source));
        checkConfiguration(config);
    }

    @Test
    public void testWithSeparatingWhitespace() throws Exception {
        String source = "<configuration><key0></key0><key1>,</key1> <key2></key2><key3></key3></configuration>";
        XMLConfiguration config = new XMLConfiguration();

        config.load(new StringReader(source));
        checkConfiguration(config);
    }

    @Test
    public void testWithSeparatingNonWhitespace() throws Exception {
        String source = "<configuration><key0></key0><key1>,</key1>A<key2></key2><key3></key3></configuration>";
        XMLConfiguration config = new XMLConfiguration();

        config.load(new StringReader(source));
        checkConfiguration(config);
    }

    @Test
    public void testWithOnlyCommaWithoutDelimiterParsing() throws Exception {
        String source = "<configuration><key0></key0><key1>,</key1><key2></key2><key3></key3></configuration>";
        XMLConfiguration config = new XMLConfiguration();
        config.setDelimiterParsingDisabled(true);

        config.load(new StringReader(source));
        checkConfiguration(config);
    }

    @Test
    public void testWithOnlyCommaWithStringBuilder() throws Exception {
        StringBuilder sourceBuilder = new StringBuilder("<configuration>");
            sourceBuilder.append("<key0></key0>");
            sourceBuilder.append("<key1>,</key1>");
            sourceBuilder.append("<key2></key2>");
            sourceBuilder.append("<key3></key3>");
            sourceBuilder.append("</configuration>");
        XMLConfiguration config = new XMLConfiguration();

        config.load(new StringReader(sourceBuilder.toString()));
        checkConfiguration(config);
    }

    @Test
    public void testWithOnlyCommaWithStringBuilderWithoutDelimiterParsing() throws Exception {
        StringBuilder sourceBuilder = new StringBuilder("<configuration>");
            sourceBuilder.append("<key0></key0>");
            sourceBuilder.append("<key1>,</key1>");
            sourceBuilder.append("<key2></key2>");
            sourceBuilder.append("<key3></key3>");
            sourceBuilder.append("</configuration>");
        XMLConfiguration config = new XMLConfiguration();
        config.setDelimiterParsingDisabled(true);

        config.load(new StringReader(sourceBuilder.toString()));
        checkConfiguration(config);
    }

}
