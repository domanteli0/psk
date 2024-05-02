package me.domantelio.psk.interceptors

import jakarta.inject.Inject
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.InvocationContext
import jakarta.interceptor.Interceptor
import java.io.Serializable
import java.util.logging.Level
import java.util.logging.Logger

@Interceptor
@LoggedInvocation
class MethodLogger : Serializable {
    private var logger: Logger = Logger.getGlobal()

    @AroundInvoke
    @Throws(Exception::class)
    fun logMethodInvocation(context: InvocationContext): Any {
        val msg ="Called method: ${context.method.name}"

        println(msg)
        logger.log(Level.INFO, msg)

        return context.proceed()
    }
}
