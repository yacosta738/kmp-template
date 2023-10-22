package io.kmptemplate.common.core

import arrow.core.Either

inline fun Either.Companion.catchUnit(f: () -> Unit): Either<Throwable, Unit> = catch(f)
