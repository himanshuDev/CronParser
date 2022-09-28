/*
 *
 *  * *
 *  *  * The MIT License (MIT)
 *  *  * <p>
 *  *  * Copyright (c) 2022
 *  *  * <p>
 *  *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  *  * of this software and associated documentation files (the "Software"), to deal
 *  *  * in the Software without restriction, including without limitation the rights
 *  *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  *  * copies of the Software, and to permit persons to whom the Software is
 *  *  * furnished to do so, subject to the following conditions:
 *  *  * <p>
 *  *  * The above copyright notice and this permission notice shall be included in all
 *  *  * copies or substantial portions of the Software.
 *  *  * <p>
 *  *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  *  * SOFTWARE.
 *  *
 *
 */

package com.abc.corp.cron_parser.test.utils;

import com.abc.corp.cron_parser.utils.CronParsingUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CronParsingUtilsTest {

    @Test
    public void testValidDigitsOnlyValue() {
        Assertions.assertTrue(CronParsingUtils.isDigitsOnly("9"));
        Assertions.assertTrue(CronParsingUtils.isDigitsOnly("99"));
        Assertions.assertTrue(CronParsingUtils.isDigitsOnly("191"));
        Assertions.assertTrue(CronParsingUtils.isDigitsOnly("93434"));
    }

    @Test
    public void testInvalidDigitsOnlyValue() {
        Assertions.assertFalse(CronParsingUtils.isDigitsOnly("9p"));
        Assertions.assertFalse(CronParsingUtils.isDigitsOnly("p9"));
        Assertions.assertFalse(CronParsingUtils.isDigitsOnly("*"));
        Assertions.assertFalse(CronParsingUtils.isDigitsOnly("a"));
        Assertions.assertFalse(CronParsingUtils.isDigitsOnly("0.5"));
    }

    @Test
    public void testValidStepOnlyValue() {
        Assertions.assertTrue(CronParsingUtils.isStepOnlyValue("9/1"));
        Assertions.assertTrue(CronParsingUtils.isStepOnlyValue("19/1"));
        Assertions.assertTrue(CronParsingUtils.isStepOnlyValue("9/12"));
        Assertions.assertTrue(CronParsingUtils.isStepOnlyValue("*/12"));
    }

    @Test
    public void testInValidStepOnlyValue() {
        Assertions.assertFalse(CronParsingUtils.isStepOnlyValue("9/1*"));
        Assertions.assertFalse(CronParsingUtils.isStepOnlyValue("1*/1"));
        Assertions.assertFalse(CronParsingUtils.isStepOnlyValue("*9/12"));
        Assertions.assertFalse(CronParsingUtils.isStepOnlyValue("a-2/12"));
    }

}
