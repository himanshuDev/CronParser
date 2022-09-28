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

package com.abc.corp.cron_parser.utils;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Utility class for Cron parsing.
 */
public class CronParsingUtils {
    private static final Pattern DigitsOnly = Pattern.compile("^\\d+$");

    private static final Pattern StepOnlyValue = Pattern.compile("^\\d+\\/\\d+$");

    private static final Pattern StepOnlyWithStarValue = Pattern.compile("^\\*\\/\\d+$");

    private static final Pattern RangeOnlyValue = Pattern.compile("^\\d+\\-\\d+$");

    private static final Pattern RangeStep = Pattern.compile("^\\d+\\-\\d+\\/\\d+$");

    public static final String Astrix = "*";

    public static final String QuestionMark = "?";

    public static final String listOfValueSeparator = ",";

    public static final String rangeSeparator = "-";

    public static final String stepValueSeparator = "\\/";

    public static final String SPACE = " ";

    public static boolean isDigitsOnly(String input) {
        return CronParsingUtils.DigitsOnly.matcher(input).find();
    }

    public static boolean isStepOnlyValue(String input) {
        return CronParsingUtils.StepOnlyValue.matcher(input).find() ||
                CronParsingUtils.StepOnlyWithStarValue.matcher(input).find();
    }

    public static boolean isOnlyRange(String input) {
        return CronParsingUtils.RangeOnlyValue.matcher(input).find();
    }

    public static boolean isQuestionMark(String input) {
        return CronParsingUtils.QuestionMark.equals(input);
    }

    public static boolean isOnlyAstrix(String input) {
        return CronParsingUtils.Astrix.equals(input);
    }

    public static boolean isRangeStep(String input) {
        return CronParsingUtils.RangeStep.matcher(input).find();
    }

    public static boolean isListOfValues(String value) {
        return value.contains(listOfValueSeparator);
    }

    public static List<String> extractListOfValue(String value) {
        String[] splitValues = value.split(listOfValueSeparator);
        return Stream.of(splitValues).map(String::trim).toList();
    }
}
