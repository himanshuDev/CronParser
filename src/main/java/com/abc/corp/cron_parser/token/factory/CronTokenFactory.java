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

package com.abc.corp.cron_parser.token.factory;

import com.abc.corp.cron_parser.token.enums.CronTokenCategory;
import com.abc.corp.cron_parser.token.type.*;
import com.abc.corp.cron_parser.utils.Pair;

import java.util.List;

/**
 * Factory class for getting the right token class for each cron token
 */
public class CronTokenFactory {

    private static CronTokenizer cronTokenizer = new CronTokenizer();

    public static List<? extends AbstractCronToken<?>> getCronTokenInstances(String cronExpression) {
        return cronTokenizer.tokenize(cronExpression).stream().map(CronTokenFactory::getCronTokenImplFromEnum).toList();
    }

    /**
     * Resolves the right TokenImplementation from a pair of (CronTokenCategory, token value)
     *
     * @param tokenTypeTokenValuePair
     * @return AbstractCronToken
     */
    private static AbstractCronToken<?> getCronTokenImplFromEnum(Pair<CronTokenCategory, String> tokenTypeTokenValuePair) {
        String value = tokenTypeTokenValuePair.getValue();
        AbstractCronToken<?> tokenImpl = switch (tokenTypeTokenValuePair.getKey()) {
            case MINUTE -> new MinuteCronToken(value);
            case HOUR -> new HourCronToken(value);
            case DAY_OF_MONTH -> new DayOfMonthCronToken(value);
            case MONTH -> new MonthCronToken(value);
            case DAY_OF_WEEK -> new DayOfWeekCronToken(value);
            case COMMAND -> new CommandCronToken(value);
        };
        return tokenImpl;
    }

}
