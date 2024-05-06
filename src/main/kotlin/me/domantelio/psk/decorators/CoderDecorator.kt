//package me.domantelio.psk.decorators
//
//import jakarta.decorator.Decorator
//import jakarta.decorator.Delegate
//import jakarta.enterprise.inject.Any
//import jakarta.inject.Inject
//
//@Decorator
//abstract class CoderDecorator : Coder {
//    @Inject
//    @Delegate
//    @Any
//    var coder: Coder? = null
//
//    fun codeString(s: String, tval: Int): String {
//        val len = s.length
//
//        return ("\"" + s + "\" becomes " + "\"" + coder.codeString(s, tval)
//                ).toString() + "\", " + len + " characters in length"
//    }
//}
