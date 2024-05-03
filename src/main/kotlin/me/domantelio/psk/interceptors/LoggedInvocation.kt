package me.domantelio.psk.interceptors

import jakarta.interceptor.InterceptorBinding
import java.lang.annotation.ElementType
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
class MethodLogger() : Serializable {
    private var logger = LoggerFactory.getLogger(MethodLogger::class.java)

    @AroundInvoke
    @Throws(Exception::class)
    fun logMethodInvocation(context: InvocationContext): Any {
        val msg ="Called method: ${context.method.name}"
        logger.info(msg)

        return context.proceed()
    }
}


