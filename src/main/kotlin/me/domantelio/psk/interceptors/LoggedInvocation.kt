package me.domantelio.psk.interceptors

import jakarta.interceptor.InterceptorBinding
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.Interceptor
import jakarta.interceptor.InvocationContext
import org.slf4j.LoggerFactory
import java.io.Serializable

@InterceptorBinding
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    // AnnotationTarget.PROPERTY_GETTER,
    // AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.CLASS,
)
annotation class LoggedInvocation

@Interceptor
@LoggedInvocation
class LoggedInvocationImpl() : Serializable {
    private var logger = LoggerFactory.getLogger(LoggedInvocationImpl::class.java)

    @AroundInvoke
    @Throws(Exception::class)
    fun logMethodInvocation(context: InvocationContext): Any {
        val paramsStr = context.method.parameters
            .map { "${it.name} : ${it.type}" }
            .fold("") { s1: String, s2: String -> "$s1 | $s2" }

        val msg ="Method [${context.method.name}] params: [${paramsStr}]"
        logger.debug(msg)

        return context.proceed()
    }
}


