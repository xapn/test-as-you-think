What you think is what you test... Not yet another testing API or framework!

Matter | Badges
------ | ------
Software factory    | [![Maven Central](https://img.shields.io/maven-central/v/org.apache.maven/apache-maven.svg)](https://search.maven.org/#artifactdetails%7Ccom.github.xapn%7Ctest-as-you-think-core%7C0.4%7C) [![Build Status for master](https://travis-ci.org/xapn/test-as-you-think.svg?branch=master)](https://travis-ci.org/xapn/test-as-you-think) [![Build Status for develop](https://travis-ci.org/xapn/test-as-you-think.svg?branch=develop)](https://travis-ci.org/xapn/test-as-you-think) [![Javadocs](http://javadoc.io/badge/com.github.xapn/test-as-you-think-core.svg?color=orange)](http://javadoc.io/doc/com.github.xapn/test-as-you-think-core) [![License: GNU LGPL v3](https://img.shields.io/badge/License-LGPL%20v3-blue.svg)](http://www.gnu.org/licenses/lgpl-3.0)
Source code         | [![LoC](https://tokei.rs/b1/github/xapn/test-as-you-think?category=code)](https://github.com/xapn/test-as-you-think) [![Files](https://tokei.rs/b1/github/xapn/test-as-you-think?category=files)](https://github.com/xapn/test-as-you-think) [![Total lines](https://tokei.rs/b1/github/xapn/test-as-you-think?category=lines)](https://github.com/xapn/test-as-you-think) [![Comments](https://tokei.rs/b1/github/xapn/test-as-you-think?category=comments)](https://github.com/xapn/test-as-you-think) [![Blank lines](https://tokei.rs/b1/github/xapn/test-as-you-think?category=blanks)](https://github.com/xapn/test-as-you-think)
Social coding       | [![Twitter URL](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/search?q=%23TestAsYouThink) [![Twitter Follow](https://img.shields.io/twitter/follow/espadrine.svg?style=social&label=Follow)](https://twitter.com/XEngineer) [![GitHub stars](https://img.shields.io/github/stars/badges/shields.svg?style=social&label=Star)](https://github.com/xapn/test-as-you-think/stargazers) [![GitHub watchers](https://img.shields.io/github/watchers/badges/shields.svg?style=social&label=Watch)](https://github.com/xapn/test-as-you-think/watchers) [![GitHub forks](https://img.shields.io/github/forks/badges/shields.svg?style=social&label=Fork)](https://github.com/xapn/test-as-you-think)

Please use this [permalink](https://goo.gl/XqS4Zf) to share this web page and to get [analytics data](https://goo.gl/#analytics/goo.gl/XqS4Zf/all_time). You can also use this [QR code](https://chart.googleapis.com/chart?cht=qr&chs=150x150&choe=UTF-8&chld=H&chl=https://goo.gl/XqS4Zf).
![QR code](https://chart.googleapis.com/chart?cht=qr&chs=150x150&choe=UTF-8&chld=H&chl=https://goo.gl/XqS4Zf)

<!-- toc -->

- [Fluent testing and added value](#fluent-testing-and-added-value)
- [Getting Started](#getting-started)
  * [Installation](#installation)
  * [Basics](#basics)
  * [Test Fixtures](#test-fixtures)
    + [Separation of concerns with multiple Given steps](#separation-of-concerns-with-multiple-given-steps)
    + [Specifying fixtures](#specifying-fixtures)
  * [Event](#event)
    + [Starting with the event](#starting-with-the-event)
    + [Avoid ambiguous method calls](#avoid-ambiguous-method-calls)
  * [Expectations](#expectations)
    + [Separation of concerns with multiple Then steps](#separation-of-concerns-with-multiple-then-steps)
    + [Expectations as predicates](#expectations-as-predicates)
    + [Specifying expectations](#specifying-expectations)
    + [Failures](#failures)
      - [Expected failures](#expected-failures)
      - [Unexpected failures](#unexpected-failures)
    + [Time limit](#time-limit)
- [Release Notes](#release-notes)
  * [Version 0.4: Time limit as an expectation](#version-04-time-limit-as-an-expectation)
  * [Version 0.3: TestAsYouThink as a Maven distributed OSS library](#version-03-testasyouthink-as-a-maven-distributed-oss-library)
  * [Version 0.2: Test fixtures as method arguments](#version-02-test-fixtures-as-method-arguments)
  * [Version 0.1: Given-When-Then as a canvas](#version-01-given-when-then-as-a-canvas)
- [License](#license)

<!-- tocstop -->

# Fluent testing and added value

*TestAsYouThink* is an open source software library in Java for testing purposes. It is designed as a **fluent API** that will change the way development teams write their unit and integration tests. It aims to take control over the coding practices as **executable guidelines**, from beginners to experts, to get **high-quality tests**. Why should you adopt *TestAsYouThink*?
- It promotes good coding practices for testing on writing tests rather than before it with training or after it with code reviews.
- It enables to give a better structure based on compilable code rather than textual comments to the test code.
- It improves test code readability and may bring more conciseness.
- It is designed to be easy to use thanks to code completion.
- It builds new original features to test execution.

Why to name this API *TestAsYouThink*? The goal of *TestAsYouThink* is to map out the road from a new software functionality idea to its contractualized achievement as an executable test, while preserving product developers against known pitfalls. According to this perspective, any pitfall is likely to extend the developer's journey and to put him off his target. By anticipating such pitfalls, *TestAsYouThink* will be the best way to reduce the distance to proper, durable testing.

Moreover *TestAsYouThink* uses the [Given-When-Then](https://www.agilealliance.org/glossary/gwt/) canvas as a formal guide to compose tests. This canvas originally comes from [Gherkin](https://sites.google.com/site/unclebobconsultingllc/the-truth-about-bdd) that is a grammatical protocol used in the [Behavior-Driven Development](https://en.wikipedia.org/wiki/Behavior-driven_development) method to write test scenarii in a business human-readable way by specifying a software behavior basing on concrete examples. [Given-When-Then](https://www.agilealliance.org/glossary/gwt/) serves to divide any test into the three eponym steps. This canvas is implemented by the *TestAsYouThink* project to deliver a [DSL](https://en.wikipedia.org/wiki/Domain-specific_language) style fluent API.

# Getting Started

## Installation

Add *TestAsYouThink* as a dependency to your project with Maven, or download it from [Maven Central](https://search.maven.org/#search%7Cga%7C1%7Ctest%20as%20you%20think).
```xml
<dependency>
    <groupId>com.github.xapn</groupId>
    <artifactId>test-as-you-think-core</artifactId>
    <version>0.4</version>
</dependency>
```

## Basics

Here is the minimal syntax to implement your test methods for a `SystemUnderTest` class.
```java
givenSutClass(SystemUnderTest.class)
.when(sut -> {})
.then(() -> {});
```

Let us complete the previous scenario with a very simple example of what you can do, while testing a non-void method of your system under test (abbreviated as SUT later).
```java
import static testasyouthink.TestAsYouThink.givenSutClass;
...

givenSutClass(SystemUnderTest.class)
.given(sut -> {
    // Where you prepare the context that defines the initial state of the SUT.
    DataSet dataSet = new DataSet(...);
    sut.setAnyAttribute(...);
}).when(sut -> {
    // Where you make an event happen in relation to an action of a customer.
    return sut.nonVoidMethod(...);
}).then(result -> {
    // Where you verify the expectations are reached, by using your favorite assertion API.
    assertThat(result).isEqualTo(...);
});
```

Notice that:
- the `TestAsYouThink` class is the only one end point of the API;
- any *Given-When-Then* step can be implemented by a lambda or a method reference;
- you manipule the same SUT type from the beginning to the end, because the `sut` type is determined during the *Given* step, until the end;
- there is no need to instantiate the `sut` object, even if it is allowed by the `givenSut(sutInstance)` alternate end point method, as below;
- the call to any `given()` method is optional;
- you manipule the same `result` type until the end, because the `result` type is determined during the *When* step;
- you cannot inadvertently make a fake test that would verify nothing, because any `then()` method is always a sequence termination.

Of course, it is also possible to test any void method, instead of a non-void one, like this. 
```java
import static testasyouthink.TestAsYouThink.givenSut;
...

givenSut(systemUnderTest)
.given(() -> {
    // Preparation of fixtures
}).when(sut -> {
    // Event or action
    sut.voidMethod(...);
}).then(() -> {
    // Verification of expectations
});
```

## Test Fixtures

### Separation of concerns with multiple Given steps

If your fixtures preparation may be divided into several blocks, you can make them materialize.
```java
givenSutClass(SystemUnderTest.class)
.given(() -> {
    // the first Given step
}).and(() -> { 
    // another Given step
}) // to be repeated as many times as you need
.when(sut -> {})
.then(result -> {});
```

For example, you can separate the preparation between the SUT and the other fixtures.
```java
givenSutClass(SystemUnderTest.class)
.given(sut -> {
    // SUT preparation in a Given step
}).and(() -> {
    // Other fixtures preparation in another Given step
}).when(sut -> {})
.then(result -> {});
```

If some fixtures are the arguments of the method to be tested, you may prefer the following alternate syntaxes.
```java
givenSutClass(SystemUnderTest.class)
.givenArgument("simple argument", anyValue)
.andArgument("argument to be built", () -> {
    // Where this argument is built.
}).andArgument("argument already ready to be used", DataProvider::choosenDataSet)
.when(SystemUnderTest::nonVoidMethodWithArguments)
.then(result -> {});
```
The arguments will be injected as argument values when the method to be tested is called. As you can guess, `Data::choosenDataSet` is a method reference.

### Specifying fixtures

You are encouraged to explain your intentions to share and remember them by specifying your test fixtures. What makes both the known state of the SUT and your data set specific to the current test case?
```java
givenSutClass(SystemUnderTest.class)
.given("a SUT in a known state", sut -> {
    // Put the SUT in a known state.
}).and("a specific data set", () -> {
    // Prepare other fixtures.
}).when(sut -> {})
.then(result -> {});
```

## Event

You can use different syntaxes to pass the event to the `when()` method:
- a method reference (`SystemUnderTest::targetMethod`),
- a statement lambda (`sut -> { return sut.targetMethod(); }`),
- an expression lambda (`sut -> sut.targetMethod()`).

All of them are useful: the more proper one depends on the use case.

You can favor the simplest `when()` method, or choose a more explicit, alternate method: `whenSutReturns()` if a result is expected; otherwise `whenSutRuns()`.

### Starting with the event

To write very simple tests, you might want to directly attack the system under test. In such a use case, the API syntax becomes very minimalist.
```java
import static testasyouthink.TestAsYouThink.when;
...

when(() -> systemUnderTest.targetMethod(oneOrMoreArguments)).then(...); // or...
when(systemUnderTest::targetMethod).then(...); // without arguments to be passed to the target method
```

### Avoid ambiguous method calls

To define the event, you may want to pass an expression lambda to the `when()` method like this.
```java
givenSutClass(SystemUnderTest.class)
.when(sut -> sut.testedMethod()) // compilation error
.then(...);
```
In such a case, the compiler meets an error because of an ambiguous method call: it does not know which `when()` method must be called. One receives a lambda that returns a value, while another one receives a lambda that returns nothing. Instead of casting the expression lambda to a function or a consumer, you can avoid this compilation problem by using the following alternate methods.

Without return:
```java
givenSutClass(SystemUnderTest.class)
.whenSutRuns(sut -> sut.voidMethod(...))
.then(...);
```
With a return:
```java
givenSutClass(SystemUnderTest.class)
.whenSutReturns(sut -> sut.nonVoidMethod(...))
.then(...);
```

## Expectations

### Separation of concerns with multiple Then steps

You can separate expectation concerns if needed. The following example separates expectations between the result and the SUT.
```java
givenSutClass(SystemUnderTest.class)
.when(sut -> { return sut.nonVoidMethod(); })
.then(result -> {
    // Where the result meets expectations.
}, sut -> {
    // Where the SUT meets expectations.
});
```

You can also separate the result expectations in detached blocks.
```java
givenSutClass(SystemUnderTest.class)
.when(sut -> { return sut.nonVoidMethod(); })
.then(result -> {
    // an expectation
}).and(result -> {
    // another expectation
});
```

### Expectations as predicates

You can write your expectations by providing one or more predicates instead of assertions.
```java
givenSutClass(SystemUnderTest.class)
.when(sut -> { return sut.nonVoidMethod(); })
.then(result -> { // a predicate related to the result
    return booleanExpressionAboutResult();
}, sut -> { // a predicate related to the SUT
    return booleanExpressionAboutSut();
});
```

### Specifying expectations

You are encouraged to explain the system under test behavior by specifying your expectations. What is the expected behavior in the current situtation?
```java
givenSutClass(SystemUnderTest.class)
.when(sut -> { ... })
.then("first specified expectation", result -> {
    // Expectation as specified
}).and("second specified expectation", result -> {
    // Another expectation as specified
});
```

### Failures

If a method signature contains a `throws` clause with a checked, compile-time exception, it is not necessary to modify the testing method signature anymore by adding the same clause to it. This clause and its spreading are considered as a technical constaint without value in a executable specification approach. As a consequence, it becomes imperceptible for the test code, and above all for the software developer who can stay focused on his tests. Tests will continue to fail if any unexpected exception is raised.

#### Expected failures

Because the failure testing is an important part of your use cases, you can verify the behavior of the system under test when it is used ouside operating conditions.
```java
givenSutClass(SystemUnderTest.class)
.whenSutRunsOutsideOperatingConditions(sut -> {
    // where an event causes a failure
}).thenItFails().becauseOf(ExpectedFailure.class).withMessage("expected message");
```

#### Unexpected failures

When an unexpected failure occurs - because of a regression for example -, the test fails by raising an `AssertionError`, because the defaut behavior consists of asserting no failure should happen, unless the software developer wants.

### Time limit

Sometimes you need to limit the allowed execution time of the tested event.
```java
givenSutClass(SystemUnderTest.class)
.when(SystemUnderTest::spendSomeTime)
.thenSutRepliesWithin(100);
```
By default, the time limit is given in milliseconds. If you want to use another time unit:
```java
import java.time.Duration;
...

givenSutClass(SystemUnderTest.class)
.when(SystemUnderTest::spendSomeTime)
.thenSutRepliesWithin(Duration.ofMinutes(3);
```

The advantage of TestAsYouThink is that the time limit is only applied to the tested event, while [JUnit](https://github.com/junit-team/junit4/wiki/timeout-for-tests) applies its `timeout` to the whole test method with its `@Test` annotation. [JUnit 5](http://junit.org/junit5/docs/snapshot/user-guide/) will propose an `assertTimeout(duration, lambda)` method that returns the lamba result, but such a syntax amalgamates irremediably the expectations and the event.

# Releases

## Versioning

To understand how version numbers change, please read the [Semantic Versioning](http://semver.org/).

## Release Notes

### 0.4.2 version: Cobertura as a code coverage analyzer

- Generate the documentation TOC updating with [markdown-toc](https://github.com/jonschlinkert/markdown-toc) and commit the documentation change while building with Maven.
- Check code coverage with [Cobertura](http://cobertura.github.io/cobertura/) and publish reports to [Codecov](https://codecov.io/) while building with [Travis CI](https://travis-ci.org).

### 0.4.1 version: Travis CI as a continuous integration platform

- Build the project with [Travis CI](https://travis-ci.org).

### 0.4 version: Time limit as an expectation

- Expect that the system under test replies within a time limit.
- Resolve ambiguous method calls in relation to using expression lambdas.
- Start to write a test with the when step.

### 0.3 version: TestAsYouThink as a Maven distributed OSS library

- Rename the API to *TestAsYouThink*.
- Choose an open source license.
- Publish artifacts to Maven Central.
- Check version updates.

### 0.2 version: Method arguments as test fixtures

- Include a data as a method argument during the preparation phase.
- Include two data as method arguments during the preparation phase.
- Include three data as method arguments during the preparation phase.
- Receive method arguments directly as values.
- Specify method arguments.
- Verify failures while invoking methods with arguments.
- Verify the expected exception and the expected message separately.

### 0.1 version: Given-When-Then as a canvas

- Write an unit or integration test by using the Given-When-Then canvas and full sequence.
- Delegate the system under test instantiation to the API.
- Reduce the syntactic sequence to a When-Then partial sequence (except the determining of the system under test).
- Specify fixtures in the Given step.
- Specify expectations in the Then step.
- Verify the expectations on the system under test, in addition to the result.
- Provide expectations as predicates.
- Verify failures.
- Separate preparations.
- Separate expectations.

# License

*TestAsYouThink* is distributed under the GNU LGPLv3 license. The LGPLv3 license is included in the LICENSE.txt file. More information about this license is available at http://www.gnu.org.
