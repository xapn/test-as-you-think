package givenwhenthen;

import static givenwhenthen.GivenWhenThen.givenSut;
import static givenwhenthen.GivenWhenThen.givenSutClass;
import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.strictMock;
import static org.easymock.EasyMock.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("unused")
public class GivenWhenThenTest {

    private GivenWhenThenDefinition givenWhenThenDefinitionMock;

    @Before
    public void prepareFixtures() {
        // GIVEN
        ordered_steps: {
            givenWhenThenDefinitionMock = strictMock(GivenWhenThenDefinition.class);
            givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
            givenWhenThenDefinitionMock.whenAnEventHappensInRelationToAnActionOfTheConsumer();
            givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult();
        }
        replay(givenWhenThenDefinitionMock);
    }

    @After
    public void verifyMocks() {
        // THEN
        verify(givenWhenThenDefinitionMock);
    }

    @Test
    public void should_follow_the_given_when_then_full_sequence_given_a_non_void_method() {
        // WHEN
        givenSut(new SystemUnderTest(givenWhenThenDefinitionMock)) //
                .given(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                }).when(sut -> {
                    return sut.nonVoidMethod();
                }).then(result -> {
                    givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult();
                    assertThat(result).isEqualTo("expected result");
                });
    }

    @Test
    public void should_follow_the_given_when_then_full_sequence_given_a_void_method() {
        // WHEN
        givenSut(new SystemUnderTest(givenWhenThenDefinitionMock)) //
                .given(() -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                }).when(sut -> {
                    sut.voidMethod();
                }).then(() -> {
                    givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult();
                });
    }

    @Test
    public void should_follow_the_given_when_then_full_sequence_given_a_sut_class_to_be_instantiated() {
        // WHEN
        givenSutClass(SystemUnderTest.class) //
                .given(sut -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    sut.setGivenWhenThenDefinition(givenWhenThenDefinitionMock);
                }).when(sut -> {
                    return sut.nonVoidMethod();
                }).then(result -> {
                    givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult();
                    assertThat(result).isEqualTo("expected result");
                });
    }

    @Test
    public void should_check_expectations_on_the_system_under_test_given_a_non_void_method() {
        // WHEN
        givenSutClass(SystemUnderTest.class) //
                .given(sut -> {
                    givenWhenThenDefinitionMock.givenAContextThatDefinesTheInitialStateOfTheSystem();
                    sut.setGivenWhenThenDefinition(givenWhenThenDefinitionMock);
                }).when(sut -> {
                    return sut.nonVoidMethod();
                }).then((result, sut) -> {
                    givenWhenThenDefinitionMock.thenTheActualResultIsInKeepingWithTheExpectedResult();
                    assertThat(result).isEqualTo("expected result");
                    assertThat(sut.getState()).isNotNull();
                });
    }
}
