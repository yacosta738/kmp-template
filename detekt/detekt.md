# detekt

## Metrics

* 28 number of properties

* 22 number of functions

* 12 number of classes

* 4 number of packages

* 12 number of kt files

## Complexity Report

* 329 lines of code (loc)

* 227 source lines of code (sloc)

* 148 logical lines of code (lloc)

* 25 comment lines of code (cloc)

* 38 cyclomatic complexity (mcc)

* 20 cognitive complexity

* 7 number of total code smells

* 11% comment source ratio

* 256 mcc per 1,000 lloc

* 47 code smells per 1,000 lloc

## Findings (7)

### naming, FunctionNaming (2)

Function names should follow the naming convention set in the configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionnaming)

* /home/runner/work/kmp-template/kmp-template/shared/list/src/testIntegration/kotlin/io/kmptemplate/InternalDummyClassTest.kt:10:9
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
7  class InternalDummyClassTest {
8  
9      @Test
10     fun testInternalDummyClass_AllProperties() {
!!         ^ error
11         val dummy = InternalDummyClass(name = "name", nick = "nick")
12 
13         assertThat(dummy.name).isEqualTo("name")

```

* /home/runner/work/kmp-template/kmp-template/shared/list/src/testIntegration/kotlin/io/kmptemplate/InternalDummyClassTest.kt:18:9
```
Function names should match the pattern: [a-z][a-zA-Z0-9]*
```
```kotlin
15     }
16 
17     @Test
18     fun testInternalDummyClass_DefaultProperties() {
!!         ^ error
19         val dummy = InternalDummyClass(name = "name")
20 
21         assertThat(dummy.name).isEqualTo("name")

```

### style, FunctionOnlyReturningConstant (1)

A function that only returns a constant is misleading. Consider declaring a constant instead.

[Documentation](https://detekt.dev/docs/rules/style#functiononlyreturningconstant)

* /home/runner/work/kmp-template/kmp-template/apps/app/src/main/kotlin/io/kmptemplate/app/MessageUtils.kt:8:13
```
getMessage is returning a constant. Prefer declaring a constant instead.
```
```kotlin
5  
6  class MessageUtils {
7      companion object {
8          fun getMessage(): String = "Hello      World!"
!              ^ error
9      }
10 }
11 

```

### style, UtilityClassWithPublicConstructor (4)

The class declaration is unnecessary because it only contains utility functions. An object declaration should be used instead.

[Documentation](https://detekt.dev/docs/rules/style#utilityclasswithpublicconstructor)

* /home/runner/work/kmp-template/kmp-template/apps/app/src/main/kotlin/io/kmptemplate/app/MessageUtils.kt:6:1
```
The class MessageUtils only contains utility functions. Consider defining it as an object.
```
```kotlin
3   */
4  package io.kmptemplate.app
5  
6  class MessageUtils {
!  ^ error
7      companion object {
8          fun getMessage(): String = "Hello      World!"
9      }

```

* /home/runner/work/kmp-template/kmp-template/shared/utilities/src/main/kotlin/io/kmptemplate/utilities/JoinUtils.kt:8:1
```
The class JoinUtils only contains utility functions. Consider defining it as an object.
```
```kotlin
5  
6  import io.kmptemplate.list.LinkedList
7  
8  class JoinUtils {
!  ^ error
9      companion object {
10         fun join(source: LinkedList): String {
11             val result = StringBuilder()

```

* /home/runner/work/kmp-template/kmp-template/shared/utilities/src/main/kotlin/io/kmptemplate/utilities/SplitUtils.kt:8:1
```
The class SplitUtils only contains utility functions. Consider defining it as an object.
```
```kotlin
5  
6  import io.kmptemplate.list.LinkedList
7  
8  class SplitUtils {
!  ^ error
9      companion object {
10         fun split(source: String): LinkedList {
11             var lastFind = 0

```

* /home/runner/work/kmp-template/kmp-template/shared/utilities/src/main/kotlin/io/kmptemplate/utilities/StringUtils.kt:8:1
```
The class StringUtils only contains utility functions. Consider defining it as an object.
```
```kotlin
5  
6  import io.kmptemplate.list.LinkedList
7  
8  class StringUtils {
!  ^ error
9      companion object {
10         fun join(source: LinkedList): String {
11             return JoinUtils.join(source)

```

generated with [detekt version 1.23.1](https://detekt.dev/) on 2023-09-25 16:27:35 UTC
