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

package com.abc.corp.cron_parser.token.value_type;

import com.abc.corp.cron_parser.utils.CronParsingUtils;
import com.abc.corp.cron_parser.utils.PermittedValue;
import lombok.Data;

import java.util.List;

@Data
public class RangeStepResolver extends AbstractTokenValueResolver<List<Integer>> {
    private Integer start;
    private Integer end;
    private Integer step;

    public RangeStepResolver(String value, PermittedValue permittedValue) {
        super(permittedValue);
        String[] separatedValues = value.split(CronParsingUtils.stepValueSeparator);
        String[] ranges = separatedValues[0].split(CronParsingUtils.rangeSeparator);
        this.start = Integer.parseInt(ranges[0]);
        this.end = Integer.parseInt(ranges[1]);
        this.step = Integer.parseInt(separatedValues[1]);
    }

    public RangeStepResolver(String value, Integer step, PermittedValue permittedValue) {
        super(permittedValue);
        String[] ranges = value.split(CronParsingUtils.rangeSeparator);
        this.start = Integer.parseInt(ranges[0]);
        this.end = Integer.parseInt(ranges[1]);
        this.step = step;
    }

    public List<Integer> resolve() {
        return new StepOnlyResolver(start, end, step, this.permittedValue).resolve();
    }

    public static Boolean isRangeValidForPermittedValues(String value, PermittedValue permittedValue) {
        String[] ranges = value.split(CronParsingUtils.rangeSeparator);
        if (ranges.length != 2) {
            return false;
        }
        Integer start = null;
        Integer end = null;
        try {
            start = Integer.parseInt(ranges[0]);
            end = Integer.parseInt(ranges[1]);
        } catch (Exception exp) {
            return false;
        }
        if (!AbstractTokenValueResolver.isPermitted(permittedValue, start) ||
               !AbstractTokenValueResolver.isPermitted(permittedValue, end)) {
            return false;
        }
        return true;
    }
}
