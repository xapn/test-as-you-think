package givenwhenthen;

import givenwhenthen.fixture.GivenWhenThenDefinition;
import givenwhenthen.fixture.SystemUnderTest;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Consumer;

import static givenwhenthen.GivenWhenThen.givenSut;
import static org.assertj.core.api.Assertions.fail;
import static org.easymock.EasyMock.*;

public class GivenArgumentsThenFailuresTest {

    private IMocksControl mocksControl;
    private SystemUnderTest systemUnderTestMock;
    private GivenWhenThenDefinition givenWhenThenDefinitionMock;

    @Before
    public void prepareFixtures() {
        // GIVEN
        mocksControl = createStrictControl();
        systemUnderTestMock = mocksControl.createMock(SystemUnderTest.class);
        givenWhenThenDefinitionMock = mocksControl.createMock(GivenWhenThenDefinition.class);
    }

    @After
    public void verifyMocks() {
        // THEN
        mocksControl.verify();
    }

    @Test(expected = AssertionError.class)
    public void should_fail_given_a_void_method_with_one_parameter() {
        // GIVEN
        givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
        try {
            systemUnderTestMock.failWithParameter("given argument");
            expectLastCall().andThrow(new Exception());
        } catch (Throwable throwable) {
            fail("Unexpected failure!");
        }
        mocksControl.replay();

        // WHEN
        givenSut(systemUnderTestMock)
                .givenArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return "given argument";
                })
                .when(SystemUnderTest::failWithParameter)
                .then(result -> {});
    }

    @Test(expected = AssertionError.class)
    public void should_fail_given_a_non_void_method_with_one_parameter() {
        // GIVEN
        givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
        try {
            systemUnderTestMock.nonVoidFailWithParameter("given argument");
            expectLastCall().andThrow(new Exception());
        } catch (Throwable throwable) {
            fail("Unexpected failure!");
        }
        mocksControl.replay();

        // WHEN
        givenSut(systemUnderTestMock)
                .givenArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return "given argument";
                })
                .when(SystemUnderTest::nonVoidFailWithParameter)
                .then(result -> {});
    }

    @Test(expected = AssertionError.class)
    public void should_fail_given_a_void_method_with_two_parameters() {
        // GIVEN
        givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
        expectLastCall().times(2);
        try {
            systemUnderTestMock.failWithTwoParameters("given argument", 201705);
            expectLastCall().andThrow(new Exception());
        } catch (Throwable throwable) {
            fail("Unexpected failure!");
        }
        mocksControl.replay();

        // WHEN
        givenSut(systemUnderTestMock)
                .givenArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return "given argument";
                })
                .andArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return 201705;
                })
                .when(SystemUnderTest::failWithTwoParameters)
                .then(result -> {});
    }

    @Test(expected = AssertionError.class)
    public void should_fail_given_a_non_void_method_with_two_parameters() {
        // GIVEN
        givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
        expectLastCall().times(2);
        try {
            systemUnderTestMock.nonVoidFailWithTwoParameters("given argument", 201705);
            expectLastCall().andThrow(new Exception());
        } catch (Throwable throwable) {
            fail("Unexpected failure!");
        }
        mocksControl.replay();

        // WHEN
        givenSut(systemUnderTestMock)
                .givenArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return "given argument";
                })
                .andArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return 201705;
                })
                .when(SystemUnderTest::nonVoidFailWithTwoParameters)
                .then(result -> {});
    }

    @Test(expected = AssertionError.class)
    public void should_fail_given_a_void_method_with_three_parameters() {
        // GIVEN
        givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
        expectLastCall().times(3);
        try {
            systemUnderTestMock.failWithThreeParameters("given argument", 201705, false);
            expectLastCall().andThrow(new Exception());
        } catch (Throwable throwable) {
            fail("Unexpected failure!");
        }
        mocksControl.replay();

        // WHEN
        givenSut(systemUnderTestMock)
                .givenArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return "given argument";
                })
                .andArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return 201705;
                })
                .andArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return false;
                })
                .when(SystemUnderTest::failWithThreeParameters)
                .then(result -> {});
    }

    @Test(expected = AssertionError.class)
    public void should_fail_given_a_non_void_method_with_three_parameters() {
        // GIVEN
        givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
        expectLastCall().times(3);
        try {
            expect(systemUnderTestMock.nonVoidFailWithThreeParameters("given argument", 201705, false)).andThrow(
                    new Exception());
        } catch (Throwable throwable) {
            fail("Unexpected failure!");
        }
        mocksControl.replay();

        // WHEN
        givenSut(systemUnderTestMock)
                .givenArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return "given argument";
                })
                .andArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return 201705;
                })
                .andArgument(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    return false;
                })
                .when(SystemUnderTest::nonVoidFailWithThreeParameters)
                .then((Consumer<String>) result -> {
                    throw new RuntimeException("An expected exception must have been risen before!");
                });
    }
}
