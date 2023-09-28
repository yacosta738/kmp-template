# detekt

## Metrics

* 29 number of properties

* 22 number of functions

* 8 number of classes

* 4 number of packages

* 12 number of kt files

## Complexity Report

* 323 lines of code (loc)

* 220 source lines of code (sloc)

* 145 logical lines of code (lloc)

* 25 comment lines of code (cloc)

* 38 cyclomatic complexity (mcc)

* 20 cognitive complexity

* 6 number of total code smells

* 11% comment source ratio

* 262 mcc per 1,000 lloc

* 41 code smells per 1,000 lloc

## Findings (6)

### formatting, ImportOrdering (3)

Detects imports in non default order

[Documentation](https://detekt.dev/docs/rules/formatting#importordering)

* /home/runner/work/kmp-template/kmp-template/apps/app/src/main/kotlin/io/kmptemplate/app/App.kt:3:1
```
Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end
```
```kotlin
1 package io.kmptemplate.app
2 
3 import io.kmptemplate.utilities.StringUtils
! ^ error
4 
5 import org.apache.commons.text.WordUtils
6 

```

* /home/runner/work/kmp-template/kmp-template/apps/app/src/test/kotlin/io/kmptemplate/app/MessageUtilsTest.kt:6:1
```
Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end
```
```kotlin
3   */
4  package io.kmptemplate.app
5  
6  import org.junit.jupiter.api.Test
!  ^ error
7  
8  import org.junit.jupiter.api.Assertions.assertEquals
9  

```

* /home/runner/work/kmp-template/kmp-template/shared/list/src/test/kotlin/io/kmptemplate/list/LinkedListTest.kt:6:1
```
Imports must be ordered in lexicographic order without any empty lines in-between with "java", "javax", "kotlin" and aliases in the end
```
```kotlin
3   */
4  package io.kmptemplate.list
5  
6  import assertk.assertThat
!  ^ error
7  import assertk.assertions.isEqualTo
8  import assertk.assertions.isTrue
9  import assertk.assertions.isFalse

```

### formatting, NoBlankLineBeforeRbrace (2)

Detects blank lines before rbraces

[Documentation](https://detekt.dev/docs/rules/formatting#noblanklinebeforerbrace)

* /home/runner/work/kmp-template/kmp-template/shared/list/src/test/kotlin/io/kmptemplate/AnotherInternalDummyClassTest.kt:14:6
```
Unexpected blank line(s) before "}"
```
```kotlin
11         val dummy = AnotherInternalDummyClass("name")
12 
13         assertThat(dummy.name).isEqualTo("name")
14     }
!!      ^ error
15 
16 }
17 

```

* /home/runner/work/kmp-template/kmp-template/shared/list/src/testIntegration/kotlin/io/kmptemplate/InternalDummyClassTest.kt:23:6
```
Unexpected blank line(s) before "}"
```
```kotlin
20 
21         assertThat(dummy.name).isEqualTo("name")
22         assertThat(dummy.nick).isEqualTo("")
23     }
!!      ^ error
24 
25 }
26 

```

### formatting, NoConsecutiveBlankLines (1)

Reports consecutive blank lines

[Documentation](https://detekt.dev/docs/rules/formatting#noconsecutiveblanklines)

* /home/runner/work/kmp-template/kmp-template/apps/app/src/main/kotlin/io/kmptemplate/app/MessageUtils.kt:9:2
```
Needless blank line(s)
```
```kotlin
6  object MessageUtils {
7      private const val message = "Hello      World!"
8      fun getMessage(): String = message
9  }
!   ^ error
10 
11 

```

generated with [detekt version 1.23.1](https://detekt.dev/) on 2023-09-28 15:09:02 UTC
