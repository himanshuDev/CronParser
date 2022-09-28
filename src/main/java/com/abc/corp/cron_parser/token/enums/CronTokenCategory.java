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

/**
 * Category of the cron token.
 */
public enum CronTokenCategory {

    //@formatter:off
    MINUTE("minutes", 1),
    HOUR("hour", 2),
    DAY_OF_MONTH("day of month", 3),
    MONTH("month", 4),
    DAY_OF_WEEK("day of week", 5),
    COMMAND("Shell Command", 6);

    //@formatter: on

    private String name;
    private Integer order;

    private CronTokenCategory(String name, Integer order) {
        this.name = name;
        this.order = order;
    }

    public Integer getOrder() {
        return this.order;
    }

}
