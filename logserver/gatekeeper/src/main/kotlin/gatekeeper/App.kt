/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package gatekeeper

import io.micronaut.runtime.Micronaut

object App {


    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("gatekeeper")
                .mainClass(App.javaClass)
                .start()
    }
}