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

package com.abc.corp.cron_parser.test.token.category;

import com.abc.corp.cron_parser.token.type.MinuteCronToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class DayOfMonthTokenTest {

    @Test
    public void testQuarterIntervalValue() {
        List<Integer> quarterIntervals = new MinuteCronToken("*/15").eval();
        List<Integer> expectedOutput = Stream.of(0, 15, 30, 45).toList();
        Assertions.assertTrue(expectedOutput.equals(quarterIntervals));
    }

    @Test
    public void testHalfHoursIntervalValue() {
        List<Integer> halfHoursIntervals = new MinuteCronToken("*/30").eval();
        List<Integer> expectedOutput = Stream.of(0, 30).toList();
        Assertions.assertTrue(expectedOutput.equals(halfHoursIntervals));
    }

    @Test
    public void test45minsIntervalValue() {
        List<Integer> halfHoursIntervals = new MinuteCronToken("*/45").eval();
        List<Integer> expectedOutput = Stream.of(0, 45).toList();
        Assertions.assertTrue(expectedOutput.equals(halfHoursIntervals));
    }

    @Test
    public void test5minsIntervalValue() {
        List<Integer> halfHoursIntervals = new MinuteCronToken("*/5").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        for (int i = 0; i < 60; i += 5) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(halfHoursIntervals));
    }

    @Test
    public void testFixedValueStepMinsIntervalValue() {
        List<Integer> twoMinInterval = new MinuteCronToken("5/2").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        for (int i = 5; i < 60; i += 2) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(twoMinInterval));
    }

    @Test
    public void testRangeValueStepMinsIntervalValue() {
        List<Integer> toToFiveMinExecuteEveryMinValues = new MinuteCronToken("1-5").eval();
        List<Integer> expectedOutput = Stream.of(1, 2, 3, 4, 5).toList();
        Assertions.assertTrue(expectedOutput.equals(toToFiveMinExecuteEveryMinValues));
    }

    @Test
    public void testRangeStepValueStepMinsIntervalValue() {
        List<Integer> oneToFiveMinRangeWithStep2 = new MinuteCronToken("1-5/2").eval();
        List<Integer> expectedOutput = Stream.of(1, 3, 5).toList();
        Assertions.assertTrue(expectedOutput.equals(oneToFiveMinRangeWithStep2));
    }

    @Test
    public void testRangeStepValueLargeStepMinsIntervalValue() {
        List<Integer> oneToFiftyMinRangeWithStep20 = new MinuteCronToken("1-50/20").eval();
        List<Integer> expectedOutput = Stream.of(1, 21, 41).toList();
        Assertions.assertTrue(expectedOutput.equals(oneToFiftyMinRangeWithStep20));
    }

    @Test
    public void testStarValue() {
        List<Integer> starRange = new MinuteCronToken("*").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        for (int i = 0; i < 60; i += 1) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(starRange));
    }

    @Test
    public void testFixedValue() {
        List<Integer> fixedValue = new MinuteCronToken("1").eval();
        List<Integer> expectedOutput = Stream.of(1).toList();
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void testFixedValueDifferentValue() {
        List<Integer> fixedValue = new MinuteCronToken("45").eval();
        List<Integer> expectedOutput = Stream.of(45).toList();
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }


    @Test
    public void multiValueTest() {
        List<Integer> fixedValue = new MinuteCronToken("45, 34, 46").eval();
        List<Integer> expectedOutput = Stream.of(45, 34, 46).toList();
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void multiValueStepTest() {
        List<Integer> fixedValue = new MinuteCronToken("12, 23, 34/2").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(12);
        expectedOutput.add(23);
        for (int i = 34; i < 60; i += 2) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void multiValueRangeStepTest() {
        List<Integer> fixedValue = new MinuteCronToken("12, 23-30, 34/2").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(12);

        for (int i = 23; i <= 30; i += 1) {
            expectedOutput.add(i);
        }

        for (int i = 34; i < 60; i += 2) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void multiValueMixedRangeStepTest() {
        List<Integer> fixedValue = new MinuteCronToken("12, 23-30/3, 34/2").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(12);

        for (int i = 23; i <= 30; i += 3) {
            expectedOutput.add(i);
        }

        for (int i = 34; i < 60; i += 2) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }
}
