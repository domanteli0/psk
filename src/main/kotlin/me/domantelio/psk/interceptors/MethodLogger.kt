package me.domantelio.psk.interceptors

import jakarta.inject.Inject
import jakarta.interceptor.AroundInvoke
import jakarta.interceptor.InvocationContext
import jakarta.interceptor.Interceptor
import java.io.Serializable

@Interceptor
@LoggedInvocation
class MethodLogger : Serializable {
    @AroundInvoke
    @Throws(Exception::class)
    fun logMethodInvocation(context: InvocationContext): Any {
        println("Called method: " + context.method.name)
        return context.proceed()
    }
}
