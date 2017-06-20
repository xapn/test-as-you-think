/*-
 * #%L
 * Test As You Think
 * %%
 * Copyright (C) 2017 Xavier Pigeon and TestAsYouThink contributors
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */

package testasyouthink;

import testasyouthink.function.CheckedConsumer;
import testasyouthink.function.CheckedFunction;
import testasyouthink.function.Functions;

import static org.assertj.core.api.Assertions.fail;

class Event<$SystemUnderTest, $Result> {

    private final Functions functions = Functions.INSTANCE;
    private final $SystemUnderTest systemUnderTest;
    private final CheckedFunction<$SystemUnderTest, $Result> whenStep;

    Event($SystemUnderTest systemUnderTest, CheckedFunction<$SystemUnderTest, $Result> whenStep) {
        this.systemUnderTest = systemUnderTest;
        this.whenStep = whenStep;
    }

    Event($SystemUnderTest systemUnderTest, CheckedConsumer<$SystemUnderTest> whenStep) {
        this.systemUnderTest = systemUnderTest;
        this.whenStep = functions.toFunction(whenStep);
    }

    $Result happen() {
        $Result result = null;
        try {
            result = whenStep.apply(systemUnderTest);
        } catch (Throwable throwable) {
            fail("Unexpected exception happened!", throwable);
        }

        return result;
    }
}