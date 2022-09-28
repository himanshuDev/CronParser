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
import com.abc.corp.cron_parser.utils.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * This class tokenizes the cron token and associates the corn token with the token category.
 */
@Component
public class CronTokenizer {

    private static final String delimitator = " ";

    private static final Map<Integer, CronTokenCategory> tokenIndexMapper = new HashMap<>();

    static {
        tokenIndexMapper.put(0, CronTokenCategory.MINUTE);
        tokenIndexMapper.put(1, CronTokenCategory.HOUR);
        tokenIndexMapper.put(2, CronTokenCategory.DAY_OF_MONTH);
        tokenIndexMapper.put(3, CronTokenCategory.MONTH);
        tokenIndexMapper.put(4, CronTokenCategory.DAY_OF_WEEK);
        tokenIndexMapper.put(5, CronTokenCategory.COMMAND);
    }

    /**
     * This method takes input as cron token, tokenizes and return the token along with its category
     * @param cronExpression
     * @return A list of Pairs where each pair is represented as (CronTokenCategory, Token Value)
     */
    public List<Pair<CronTokenCategory, String>> tokenize(String cronExpression) {
        List<Pair<CronTokenCategory, String>> tokenList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(cronExpression, delimitator);
        int index = 0;
        while (st.hasMoreTokens()) {
            tokenList.add(Pair.of(tokenIndexMapper.get(index), st.nextToken()));
            index++;
        }
        return tokenList;
    }

}
