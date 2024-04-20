package me.domantelio.psk.interceptors

import jakarta.interceptor.InterceptorBinding

@InterceptorBinding
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CLASS
)
annotation class LoggedInvocation
