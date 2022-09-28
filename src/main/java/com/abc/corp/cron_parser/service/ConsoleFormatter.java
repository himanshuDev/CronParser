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

package com.abc.corp.cron_parser.service;

import com.abc.corp.cron_parser.token.type.AbstractCronToken;
import com.abc.corp.cron_parser.utils.CronParsingUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleFormatter {

    public static String format(List<? extends AbstractCronToken<?>> response) {
        return response.stream().sorted(Comparator.comparing((AbstractCronToken<?> r) -> r.getOrder())).map(ConsoleFormatter::formatCronTokenForConsoleDisplay).collect(Collectors.joining(System.lineSeparator()));
    }

    private static String formatCronTokenForConsoleDisplay(AbstractCronToken<?> cronTokenImpl) {
        String tokenCategory = cronTokenImpl.getCronTokenCategory().toString().replace("_", " ").toLowerCase();
        String tokenValue = cronTokenImpl.getProcessedStringValue();
        if (tokenCategory.length() > 14) {
            tokenCategory = tokenCategory.substring(0, 13);
        } else if (tokenCategory.length() < 14) {
            tokenCategory = String.format("%-13s", tokenCategory);
        }
        tokenCategory += CronParsingUtils.SPACE;
        return tokenCategory + tokenValue;
    }
}
