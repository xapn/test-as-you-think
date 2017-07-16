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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import testasyouthink.execution.ExecutionError;
import testasyouthink.fixture.ExpectedException;
import testasyouthink.fixture.GivenWhenThenDefinition;
import testasyouthink.fixture.SystemUnderTest;
import testasyouthink.fixture.UnexpectedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.strictMock;
import static org.easymock.EasyMock.verify;
import static testasyouthink.TestAsYouThink.givenSut;

public class ThenFailuresTest {

    private static final String EXPECTED_MESSAGE = "expected message";
    private static final String UNEXPECTED_MESSAGE = "unexpected message";
    private static final String EXPECTED_ERROR_MESSAGE = "Fails to execute the target method " //
            + "of the system under test because of an unexpected exception!";
    private SystemUnderTest systemUnderTestMock;
    private GivenWhenThenDefinition givenWhenThenDefinitionMock;

    @Before
    public void prepareFixtures() {
        // GIVEN
        systemUnderTestMock = strictMock(SystemUnderTest.class);
    }

    @After
    public void verifyMocks() {
        // THEN
        verify(systemUnderTestMock);
    }

    @Test
    public void should_verify_the_sut_fails() throws Throwable {
        // GIVEN
        expect(systemUnderTestMock.methodWithThrowsClause()).andThrow(new ExpectedException());
        replay(systemUnderTestMock);

        // WHEN
        givenSut(systemUnderTestMock)
                .whenSutRunsOutsideOperatingConditions(SystemUnderTest::methodWithThrowsClause)
                .thenItFails();
    }

    @Test
    public void should_verify_the_sut_fails_by_raising_an_expected_exception() throws Throwable {
        // GIVEN
        expect(systemUnderTestMock.methodWithThrowsClause()).andThrow(new ExpectedException());
        replay(systemUnderTestMock);

        // WHEN
        givenSut(systemUnderTestMock)
                .whenSutRunsOutsideOperatingConditions(SystemUnderTest::methodWithThrowsClause)
                .thenItFails()
                .becauseOf(ExpectedException.class);
    }

    @Test
    public void should_fail_given_an_unexpected_exception() throws Throwable {
        // GIVEN
        expect(systemUnderTestMock.methodWithThrowsClause()).andThrow(new UnexpectedException());
        replay(systemUnderTestMock);

        // WHEN
        Throwable thrown = catchThrowable(() -> givenSut(systemUnderTestMock)
                .whenSutRunsOutsideOperatingConditions(SystemUnderTest::methodWithThrowsClause)
                .thenItFails()
                .becauseOf(ExpectedException.class));

        // THEN
        thrown.printStackTrace();
        assertThat(thrown).isInstanceOf(AssertionError.class);
    }

    @Test
    public void should_verify_the_sut_fails_by_raising_an_expected_exception_with_an_expected_message() throws
            Throwable {
        // GIVEN
        expect(systemUnderTestMock.methodWithThrowsClause()).andThrow(new ExpectedException(EXPECTED_MESSAGE));
        replay(systemUnderTestMock);

        // WHEN
        givenSut(systemUnderTestMock)
                .whenSutRunsOutsideOperatingConditions(SystemUnderTest::methodWithThrowsClause)
                .thenItFails()
                .becauseOf(ExpectedException.class)
                .withMessage(EXPECTED_MESSAGE);
    }

    @Test
    public void should_fail_given_an_unexpected_message() throws Throwable {
        // GIVEN
        expect(systemUnderTestMock.methodWithThrowsClause()).andThrow(new ExpectedException(UNEXPECTED_MESSAGE));
        replay(systemUnderTestMock);

        // WHEN
        Throwable thrown = catchThrowable(() -> givenSut(systemUnderTestMock)
                .whenSutRunsOutsideOperatingConditions(SystemUnderTest::methodWithThrowsClause)
                .thenItFails()
                .becauseOf(ExpectedException.class)
                .withMessage(EXPECTED_MESSAGE));

        // THEN
        thrown.printStackTrace();
        assertThat(thrown).isInstanceOf(AssertionError.class);
    }

    @Test
    public void should_fail_to_execute_given_a_non_void_method() throws Throwable {
        // GIVEN
        expect(systemUnderTestMock.nonVoidMethodWithThrowsClause()).andThrow(new UnexpectedException());
        givenWhenThenDefinitionMock = strictMock(GivenWhenThenDefinition.class);
        replay(systemUnderTestMock, givenWhenThenDefinitionMock);

        // WHEN
        Throwable thrown = catchThrowable(() -> givenSut(systemUnderTestMock)
                .when(SystemUnderTest::nonVoidMethodWithThrowsClause)
                .then(() -> givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult()));

        // THEN
        thrown.printStackTrace();
        assertThat(thrown)
                .isInstanceOf(ExecutionError.class)
                .hasMessage(EXPECTED_ERROR_MESSAGE)
                .hasCauseInstanceOf(UnexpectedException.class);
        verify(givenWhenThenDefinitionMock);
    }

    @Test
    public void should_fail_given_a_void_method() throws Throwable {
        // GIVEN
        systemUnderTestMock.voidMethodWithThrowsClause();
        expectLastCall().andThrow(new UnexpectedException());
        givenWhenThenDefinitionMock = strictMock(GivenWhenThenDefinition.class);
        replay(systemUnderTestMock, givenWhenThenDefinitionMock);

        // WHEN
        Throwable thrown = catchThrowable(() -> givenSut(systemUnderTestMock)
                .when(SystemUnderTest::voidMethodWithThrowsClause)
                .then(() -> givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult()));

        // THEN
        assertThat(thrown)
                .isInstanceOf(ExecutionError.class)
                .hasMessage(EXPECTED_ERROR_MESSAGE)
                .hasCauseInstanceOf(UnexpectedException.class);
        verify(givenWhenThenDefinitionMock);
    }
}
