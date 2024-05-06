//package me.domantelio.psk.interceptors
//
//import jakarta.faces.context.FacesContext
//import jakarta.interceptor.InterceptorBinding
//import jakarta.interceptor.AroundInvoke
//import jakarta.interceptor.Interceptor
//import jakarta.interceptor.InvocationContext
//import org.slf4j.LoggerFactory
//import java.io.Serializable
//
//@InterceptorBinding
//@Retention(AnnotationRetention.RUNTIME)
//@Target(
//    AnnotationTarget.FUNCTION,
//    AnnotationTarget.CLASS,
//    // AnnotationTarget.PROPERTY_GETTER,
//    // AnnotationTarget.PROPERTY_SETTER,
//)
//annotation class LogFacesParameters
//
//@Interceptor
//@LogFacesParameters
//class LogFacesParametersImpl() : Serializable {
//    private var logger = LoggerFactory.getLogger(LogFacesParameters::class.java)
//
//    @AroundInvoke
//    @Throws(Exception::class)
//    fun log(context: InvocationContext): Any {
//        val requestParameters: Map<String, String?> =
//            FacesContext.getCurrentInstance().externalContext.requestParameterMap
//
//        val msg = requestParameters
//            .map { it.key + " = " + (it.value ?: "__NULL__") }
//            .fold("") { s1, s2 -> "$s1 | $s2" }
//
//        logger.debug("REQUEST_PARAMS: [$msg]")
//
//        return context.proceed()
//    }
//}
//
//
