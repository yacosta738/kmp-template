package io.kmptemplate

import assertk.assertThat
import assertk.assertions.isEqualTo
import kotlin.test.Test

class InternalDummyClassTest {

    @Test
    fun testInternalDummyClassAllProperties() {
        val dummy = InternalDummyClass(name = "name", nick = "nick")

        assertThat(dummy.name).isEqualTo("name")
        assertThat(dummy.nick).isEqualTo("nick")
    }

    @Test
    fun testInternalDummyClassDefaultProperties() {
        val dummy = InternalDummyClass(name = "name")

        assertThat(dummy.name).isEqualTo("name")
        assertThat(dummy.nick).isEqualTo("")
    }

}
