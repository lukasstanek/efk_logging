import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */
buildscript {
    repositories {
        mavenCentral()
        maven(url="https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:2.0.4")
        classpath("io.spring.gradle:dependency-management-plugin:1.0.6.RELEASE")
        classpath("net.ltgt.gradle:gradle-apt-plugin:0.15")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.11")

    }
}


plugins {
    // Apply the application plugin to add support for building a CLI application.
    application
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.11")
    id("idea")
    id("java")
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("net.ltgt.apt-idea") version "0.20"
    kotlin("kapt") version "1.3.11"
}

configure<DependencyManagementExtension> {
    imports {
        mavenBom("io.micronaut:micronaut-bom:1.0.3")
    }
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
    maven(url="https://jcenter.bintray.com")
    //tag::spekrepository[]
    maven(url="https://dl.bintray.com/spekframework/spek-dev")
    //end::spekrepository[]
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.11")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation(group="org.slf4j", name="slf4j-api", version="1.7.+")
    implementation(group="org.apache.logging.log4j", name="log4j-core", version="2.11.1")
    implementation(group="ch.qos.logback", name="logback-classic", version="1.+")
    implementation(group="org.slf4j", name="slf4j-simple", version="1.7.+")

    implementation(group="org.mindrot", name="jbcrypt", version="0.4")



    implementation("com.floragunn:search-guard-6:6.5.4-24.0")
    implementation("org.elasticsearch.client:transport:6.5.4")
    implementation("com.floragunn:search-guard-tlstool:1.6")

    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-http-server-netty")
    kapt("io.micronaut:micronaut-inject-java")
    kaptTest("io.micronaut:micronaut-inject-java")
    runtime("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.4.1")

    kapt("io.micronaut:micronaut-inject-java")

}

application {
    // Define the main class for the application.
    mainClassName = "gatekeeper.AppKt"
}
// due to initialization bug, this plugin is initiliazed here
apply(plugin="com.github.johnrengelman.shadow")


val shadowJar: ShadowJar by tasks
shadowJar.mergeServiceFiles()

tasks.withType<KotlinCompile<KotlinJvmOptions>> {
    kotlinOptions {
        jvmTarget = "1.8"
        javaParameters = true
    }
}