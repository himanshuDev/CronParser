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
import com.abc.corp.cron_parser.token.factory.TokenValueResolverFactory;
import com.abc.corp.cron_parser.token.interfaces.Evaluable;
import com.abc.corp.cron_parser.token.interfaces.Ordered;
import com.abc.corp.cron_parser.token.interfaces.Validator;
import com.abc.corp.cron_parser.token.value_type.AbstractTokenValueResolver;
import com.abc.corp.cron_parser.utils.CronParsingUtils;
import com.abc.corp.cron_parser.utils.PermittedValue;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * This is an abstract class representing the individual cron token.
 * This class contains the basic token parsing mechanisms for each type of token.
 * Every token varies in terms of parsing of its value, For example minute token
 * cannot have value more than 59 etc.
 * Those specific details will be provided by the implementation class. If the parsing
 * is varying a lot, then override the eval() method and provide the custom parsing.
 * </p>
 *
 * @param <T>
 */
@Data
public abstract class AbstractCronToken<T> implements Evaluable<T>, Ordered, Validator {

    protected CronTokenCategory cronTokenCategory;

    protected String tokenValue;

    protected T evaluatedValue;

    protected AbstractTokenValueResolver<T> tokenValueResolver;

    protected String validationFailureMessage;

    public AbstractCronToken(CronTokenCategory cronTokenCategory, String tokenValue) {
        this.cronTokenCategory = cronTokenCategory;
        this.tokenValue = tokenValue;
    }

    /**
     * <p>Override & Provide the permitted value for the token
     * e.g. for minute permitted value will be 0-59 etc.</p>
     *
     * @return
     */
    protected abstract PermittedValue getPermittedValue();

    /**
     * <p>Flattens List<T> to T</p>
     *
     * @param tokens of type List<T>
     * @return T
     */
    protected abstract T flattenMultiList(List<T> tokens);

    /**
     * @return the order of the current token in cron expression.
     */
    @Override
    public Integer getOrder() {
        return this.cronTokenCategory.getOrder();
    }

    /**
     * Return the String displayable value of the token.
     * This will be used when output needs to shown as string.
     *
     * @return
     */
    public abstract String getProcessedStringValue();

    /**
     * This method parses the cron token value
     *
     * @return The resolved value of the cron token.
     */
    @Override
    public T eval() {
        T resolvedValues;
        if (CronParsingUtils.isListOfValues(this.tokenValue)) {
            resolvedValues = this.flattenMultiList(this.resolveMultipleTokens());
        } else {
            resolvedValues = this.resolveSingleToken(this.tokenValue);
        }
        this.setEvaluatedValue(resolvedValues);
        return resolvedValues;
    }

    /**
     * Resolved the token value when the token value is multiple. For example 12, 15, 25/5 etc.
     *
     * @return
     */
    protected List<T> resolveMultipleTokens() {
        List<String> tokenValue = CronParsingUtils.extractListOfValue(this.tokenValue);
        List<T> resolvedValues = tokenValue.stream().map(this::resolveSingleToken).collect(Collectors.toList());
        return resolvedValues;
    }

    /**
     * <p>Resolves the single token value.
     * This method extracts the right resolver for the token and then
     * resolved the values of the based on the value permitted by the token boundary.
     * </p>
     *
     * @param tokenValue
     * @return Resolved value of the token.
     */
    protected T resolveSingleToken(String tokenValue) {
        return (T) TokenValueResolverFactory.getTokenValueResolver(tokenValue, this.getPermittedValue()).resolve();
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String toString() {
        return "AbstractCronToken{" + "cronTokenCategory=" + cronTokenCategory + ", tokenValue='" + tokenValue + '\'' + ", evaluatedValue=" + evaluatedValue + '}';
    }
}
