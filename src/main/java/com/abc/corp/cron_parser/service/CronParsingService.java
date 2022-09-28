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

import com.abc.corp.cron_parser.token.factory.CronTokenFactory;
import com.abc.corp.cron_parser.token.type.AbstractCronToken;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * This is the core service impl. All public facing method are exposed in the cron parsing service.
 * This class parses the cron token and provides the list of parsed tokens as list of values.
 * Also its up to the use how to render the parsed values. By default a public method is added
 * to print the parsed value on console.
 * </p>
 */
@Service
public class CronParsingService {

    /**
     * This method parses the cron expression and prints the output on console.
     *
     * @param cronExpression
     */
    public void extractCronInfoAndPrintOnConsole(String cronExpression) {
        System.out.println(ConsoleFormatter.format(this.parse(cronExpression)));
    }

    /**
     * This method implements the core logic to perform the cron parsing.
     * It is done as following<br>
     * <ul>
     *     <li>Tokenize the cron token and obtain the corresponding token implementation of the cron token</li>
     *     <li>Check for validity of the token</li>
     *     <li>if input expression is valid then extract the values for each tokenimpl by calling eval()</li>
     * </ul>
     *
     * @param cronExpression
     * @return
     */
    public List<? extends AbstractCronToken<?>> parse(String cronExpression) {
        List<? extends AbstractCronToken<?>> tokenImpls = CronTokenFactory.getCronTokenInstances(cronExpression);
        if (this.areTokensValid(tokenImpls)) {
            tokenImpls.forEach(tokenImpl -> {
                tokenImpl.eval();
            });
        }
        return tokenImpls;
    }

    /**
     * Determines if the corn tokens are valid or not.
     * @param tokenImpls
     * @return
     */
    private boolean areTokensValid(List<? extends AbstractCronToken<?>> tokenImpls) {
        List<? extends AbstractCronToken<?>> invalidTokens = tokenImpls.stream().filter(tokenImpl -> !tokenImpl.isValid()).toList();
        return invalidTokens.isEmpty();
    }
}
