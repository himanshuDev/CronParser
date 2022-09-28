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

package com.abc.corp.cron_parser;

import com.abc.corp.cron_parser.service.CronParsingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;


/**
 * <p>
 * This is the entry point of application, Just the standard Spring boot launcher class.
 * This is a command line utility. However this application is written in such a way
 * that the core business logic can used in web services and other interfaces too.
 * To understand the system design of the application refer
 * CronExpression/src/main/resources/CronParser System Design Diagrams.pdf
 * </p>
 *
 * @author himanshuupadhyay
 */
@SpringBootApplication
public class CronParserApplication implements CommandLineRunner {

    @Autowired
    private CronParsingService cronParsingService;

    public static void main(String[] args) {
        SpringApplication.run(CronParserApplication.class, args);
    }

    /**
     * This method get invoked when the application is invoked form the command line.
     *
     * @param args
     */
    @Override
    public void run(String... args) {
        //Added this logic to make sure the test cases run. i.e. if we don't put the
        //Null args check the application will throw an error while building.
        if (Objects.nonNull(args) && args.length == 1) {
            this.cronParsingService.extractCronInfoAndPrintOnConsole(args[0]);
        }

    }
}
