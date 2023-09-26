package io.kmptemplate

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class AnotherInternalDummyClassTest {

    @Test
    fun `test internal dummy class`() {
        val dummy = AnotherInternalDummyClass("name")

        assertThat(dummy.name).isEqualTo("name")
    }

}
