package io.hfx.pluginx.platform.dsl

@DslMarker
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE)
annotation class HfxDsl()