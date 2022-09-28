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

import com.abc.corp.cron_parser.exception.BoundaryLimitViolationException;
import com.abc.corp.cron_parser.utils.CronParsingUtils;
import com.abc.corp.cron_parser.utils.PermittedValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StepOnlyResolver extends AbstractTokenValueResolver<List<Integer>> {

    Integer initialValue;
    Integer step;
    Integer limit;

    public StepOnlyResolver(Integer offset, Integer limit, Integer step, PermittedValue permittedValue) {
        super(permittedValue);
        this.initialValue = offset;
        this.limit = limit;
        this.step = step;
    }

    public StepOnlyResolver(String value, PermittedValue permittedValue) {
        super(permittedValue);
        String[] splitValues = value.split(CronParsingUtils.stepValueSeparator);
        if (CronParsingUtils.isOnlyAstrix(splitValues[0])) {
            this.initialValue = this.permittedValue.getOffset();
        } else {
            this.initialValue = Integer.parseInt(splitValues[0]);
            this.limit = this.permittedValue.getLimit();
            if (initialValue > this.permittedValue.getLimit()) {
                throw new BoundaryLimitViolationException("Initial value " + initialValue + " exceeds limit " + this.permittedValue.getLimit());
            }
        }
        this.limit = this.permittedValue.getLimit();
        this.step = Integer.parseInt(splitValues[1]);
    }

    @Override
    public List<Integer> resolve() {
        List<Integer> steps = new ArrayList<>();
        for (int stepIndex = this.initialValue; stepIndex <= this.limit; stepIndex += this.step) {
            steps.add(stepIndex);
        }
        return steps;
    }

    public static Boolean isStepConfigValid(String value, PermittedValue permittedValue) {
        String[] splitValues = value.split(CronParsingUtils.stepValueSeparator);
        if (CronParsingUtils.isDigitsOnly(splitValues[0]) &&
                CronParsingUtils.isDigitsOnly(splitValues[1]) &&
                AbstractTokenValueResolver.isPermitted(permittedValue, Integer.parseInt(splitValues[0])) &&
                AbstractTokenValueResolver.isPermitted(permittedValue, Integer.parseInt(splitValues[1]))) {
            return true;
        } else if (CronParsingUtils.isOnlyAstrix(splitValues[0]) &&
                CronParsingUtils.isDigitsOnly(splitValues[1]) &&
                AbstractTokenValueResolver.isPermitted(permittedValue, Integer.parseInt(splitValues[1]))) {
            return true;
        }
        return false;
    }

}
