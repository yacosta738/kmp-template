package io.kmptemplate.common.tests

import io.kotest.common.ExperimentalKotest
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.config.LogLevel
import io.kotest.core.spec.IsolationMode
import io.kotest.core.test.AssertionMode

@OptIn(ExperimentalKotest::class)
object KotestProjectConfig : AbstractProjectConfig() {
    private const val NUM_THREADS = 3

    override val assertionMode = AssertionMode.Warn
    override val coroutineDebugProbes = true
    override val globalAssertSoftly = true
    override val includeTestScopePrefixes = true
    override val isolationMode = IsolationMode.SingleInstance
    override val logLevel = LogLevel.Warn
    override val parallelism = NUM_THREADS
    override val testNameAppendTags = true
}
