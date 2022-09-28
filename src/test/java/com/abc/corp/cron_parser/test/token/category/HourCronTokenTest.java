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

import com.abc.corp.cron_parser.token.type.HourCronToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class HourCronTokenTest {

    @Test
    public void test15HoursIntervalValue() {
        List<Integer> quarterIntervals = new HourCronToken("*/15").eval();
        List<Integer> expectedOutput = Stream.of(0, 15).toList();
        Assertions.assertTrue(expectedOutput.equals(quarterIntervals));
    }

    @Test
    public void test30HoursIntervalValue() {
        List<Integer> halfHoursIntervals = new HourCronToken("*/30").eval();
        List<Integer> expectedOutput = Stream.of(0).toList();
        Assertions.assertTrue(expectedOutput.equals(halfHoursIntervals));
    }

    @Test
    public void test2HoursIntervalValue() {
        List<Integer> halfHoursIntervals = new HourCronToken("*/2").eval();
        List<Integer> expectedOutput = Stream.of(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22).toList();
        Assertions.assertTrue(expectedOutput.equals(halfHoursIntervals));
    }

    @Test
    public void test5HoursIntervalValue() {
        List<Integer> halfHoursIntervals = new HourCronToken("*/5").eval();
        List<Integer> expectedOutput = Stream.of(0, 5, 10, 15, 20).toList();
        Assertions.assertTrue(expectedOutput.equals(halfHoursIntervals));
    }

    @Test
    public void testFixedValueStepHourIntervalValue() {
        List<Integer> twoMinInterval = new HourCronToken("5/2").eval();
        List<Integer> expectedOutput = Stream.of(5, 7, 9, 11, 13, 15, 17, 19, 21, 23).toList();
        Assertions.assertTrue(expectedOutput.equals(twoMinInterval));
    }

    @Test
    public void testRangeValueStepHourIntervalValue() {
        List<Integer> toToFiveHourExecuteEveryHourValues = new HourCronToken("1-5").eval();
        List<Integer> expectedOutput = Stream.of(1, 2, 3, 4, 5).toList();
        Assertions.assertTrue(expectedOutput.equals(toToFiveHourExecuteEveryHourValues));
    }

    @Test
    public void testRangeStepValueStepHourIntervalValue() {
        List<Integer> oneToFiveHourRangeWithStep2 = new HourCronToken("1-5/2").eval();
        List<Integer> expectedOutput = Stream.of(1, 3, 5).toList();
        Assertions.assertTrue(expectedOutput.equals(oneToFiveHourRangeWithStep2));
    }

    @Test
    public void testRangeStepValueLargeStepHourIntervalValue() {
        List<Integer> oneTo12RangeWithStep5 = new HourCronToken("1-12/5").eval();
        List<Integer> expectedOutput = Stream.of(1, 6, 11).toList();
        Assertions.assertTrue(expectedOutput.equals(oneTo12RangeWithStep5));
    }

    @Test
    public void testStarValue() {
        List<Integer> starRange = new HourCronToken("*").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        for (int i = 0; i < 24; i += 1) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(starRange));
    }

    @Test
    public void testFixedValue() {
        List<Integer> fixedValue = new HourCronToken("1").eval();
        List<Integer> expectedOutput = Stream.of(1).toList();
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void testFixedValueDifferentValue() {
        List<Integer> fixedValue = new HourCronToken("23").eval();
        List<Integer> expectedOutput = Stream.of(23).toList();
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void multiValueTest() {
        List<Integer> fixedValue = new HourCronToken("1, 4, 5").eval();
        List<Integer> expectedOutput = Stream.of(1, 4, 5).toList();
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void multiValueStepTest() {
        List<Integer> fixedValue = new HourCronToken("12, 13, 16/2").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(12);
        expectedOutput.add(13);
        for (int i = 16; i < 24; i += 2) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void multiValueRangeStepTest() {
        List<Integer> fixedValue = new HourCronToken("5, 10-13, 15/2").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(5);

        for (int i = 10; i <= 13; i += 1) {
            expectedOutput.add(i);
        }

        for (int i = 15; i < 24; i += 2) {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }

    @Test
    public void multiValueMixedRangeStepTest() {
        List<Integer> fixedValue = new HourCronToken("7, 10-16/2, 20/2").eval();
        List<Integer> expectedOutput = new ArrayList<>();
        expectedOutput.add(7);

        for (int i = 10; i <= 16; i += 2) {
            expectedOutput.add(i);
        }

        for (int i = 20; i < 24; i += 2)  {
            expectedOutput.add(i);
        }
        Assertions.assertTrue(expectedOutput.equals(fixedValue));
    }
}
