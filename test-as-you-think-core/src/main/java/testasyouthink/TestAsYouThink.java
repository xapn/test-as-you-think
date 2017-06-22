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

import testasyouthink.GivenWhenThenDsl.PreparationStage.Given;
import testasyouthink.GivenWhenThenDsl.VerificationStage.Then;
import testasyouthink.function.CheckedConsumer;
import testasyouthink.function.CheckedFunction;

import java.util.function.Supplier;

public class TestAsYouThink {

    private static ThenStepFactory thenStepFactory = ThenStepFactory.INSTANCE;

    public static <$SystemUnderTest> Given<$SystemUnderTest> givenSut($SystemUnderTest systemUnderTest) {
        return new GivenWhenSteps<>(systemUnderTest);
    }

    public static <$SystemUnderTest> Given<$SystemUnderTest> givenSutClass(Class<$SystemUnderTest> sutClass) {
        try {
            return new GivenWhenSteps<>(sutClass.newInstance());
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    public static ThenWithoutResultStep<Void> when(Runnable whenStep) {
        CheckedConsumer<Void> whenStepAsVoidConsumer = Void -> whenStep.run();
        return thenStepFactory.createThenStep(whenStepAsVoidConsumer);
    }

    public static <$Result> Then<Void, $Result> when(Supplier<$Result> whenStep) {
        CheckedFunction<Void, $Result> whenStepAsFunction = Void -> whenStep.get();
        return thenStepFactory.createThenStep(whenStepAsFunction);
    }

    public static <$SystemUnderTest, $Result> CheckedFunction<$SystemUnderTest, $Result> withReturn(
            CheckedFunction<$SystemUnderTest, $Result> whenStep) {
        return whenStep;
    }
}
