package org.eclipse.jakarta.hello

import java.util.Objects

public class Hello(val name: String) {

    public fun getHello(): String {
        return name
    }

}