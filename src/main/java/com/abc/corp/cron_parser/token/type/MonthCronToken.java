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

package com.abc.corp.cron_parser.token.type;

import com.abc.corp.cron_parser.token.enums.CronTokenCategory;
import com.abc.corp.cron_parser.utils.CronParsingUtils;
import com.abc.corp.cron_parser.utils.PermittedValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MonthCronToken extends AbstractCronToken<List<Integer>> {

    public MonthCronToken(String tokenValue) {
        super(CronTokenCategory.MONTH, tokenValue);
    }

    @Override
    protected List<Integer> flattenMultiList(List<List<Integer>> tokens) {
        return tokens.stream().flatMap(Collection::stream).toList();
    }

    @Override
    public String getProcessedStringValue() {
        return this.getEvaluatedValue().stream().map(val -> val.toString()).collect(Collectors.joining(CronParsingUtils.SPACE));
    }

    @Override
    protected PermittedValue getPermittedValue() {
        return PermittedValue.of(1, 12);
    }

    @Override
    public boolean isSpecialCharAllowed(String ch) {
        return false;
    }

    @Override
    public String getValidationFailureMessage() {
        return null;
    }

}
