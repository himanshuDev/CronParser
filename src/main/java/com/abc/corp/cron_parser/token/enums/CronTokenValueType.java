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

package com.abc.corp.cron_parser.token.enums;

import com.abc.corp.cron_parser.utils.CronParsingUtils;

/**
 * Represents the cron token value types.
 */
public enum CronTokenValueType {

    //@formatter:off
    RANGE_ONLY, //3-5
    STEP_ONLY, // 4/2
    RANGE_STEP, // 2-20/2
    DIGIT_ONLY, // 1
    ASTRIX,
    QUESTION_MARK,
    INVALID;
    //@formatter:on

    /**
     * @param tokenValue
     * @return The appropriate CronTokenValueType by understanding the token value
     */
    public static CronTokenValueType getTokenValueTypeFromTokenValue(String tokenValue) {
        CronTokenValueType cronTokenValueType = INVALID;

        if (CronParsingUtils.isDigitsOnly(tokenValue)) {
            cronTokenValueType = cronTokenValueType.DIGIT_ONLY;
        } else if (CronParsingUtils.isOnlyAstrix(tokenValue)) {
            cronTokenValueType = cronTokenValueType.ASTRIX;
        } else if (CronParsingUtils.isQuestionMark(tokenValue)) {
            cronTokenValueType = cronTokenValueType.QUESTION_MARK;
        } else if (CronParsingUtils.isOnlyRange(tokenValue)) {
            cronTokenValueType = cronTokenValueType.RANGE_ONLY;
        } else if (CronParsingUtils.isStepOnlyValue(tokenValue)) {
            cronTokenValueType = cronTokenValueType.STEP_ONLY;
        } else if (CronParsingUtils.isRangeStep(tokenValue)) {
            cronTokenValueType = cronTokenValueType.RANGE_STEP;
        }

        return cronTokenValueType;
    }
}
