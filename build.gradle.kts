@file:Suppress("UNUSED_VARIABLE")

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.2.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.8.RELEASE"
	kotlin("jvm") version "1.3.61"
	kotlin("plugin.spring") version "1.3.61"
	id("se.patrikerdes.use-latest-versions") version "0.2.13"
	id("com.github.ben-manes.versions") version "0.27.0"
	id("com.adarshr.test-logger") version "2.0.0"
	id("com.bmuschko.docker-spring-boot-application") version "6.1.1"
}

group = "it.skrape"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val isIdea = System.getProperty("idea.version") != null

testlogger {
	setTheme(if (isIdea) "plain" else "mocha")
	slowThreshold = 1000
}

repositories {
	jcenter()
}

dependencies {
	val tess4jVersion = "4.5.1"
	val htmlDslVersion = "0.6.11"

	val striktVersion = "0.23.4"

	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("net.sourceforge.tess4j:tess4j:$tess4jVersion")
	implementation("com.github.geko444:im4java:1.4.2")
	implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$htmlDslVersion")

	testImplementation("io.strikt:strikt-core:$striktVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks {
	withType<Test> {
		useJUnitPlatform()
	}

	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	withType<DependencyUpdatesTask> {

		gradleReleaseChannel = "current"

		rejectVersionIf {
			val isFlaggedAsNonStable = listOf("alpha", "beta", "RC", "rc", "M1").any { candidate.version.contains(it) }.not()
			val isSemanticVersion =  "^[0-9,.v-]+(-r)?$".toRegex().matches(candidate.version)
			(isFlaggedAsNonStable || isSemanticVersion).not()
		}
	}

	val updateDependencies by creating {
		dependsOn(useLatestVersions, test)
	}
}

docker {
	springBootApplication {
		baseImage.set("openjdk:8-alpine")
		maintainer.set(rootProject.name)
		images.set(setOf("${rootProject.name}:latest"))
		jvmArgs.set(listOf("-Dspring.profiles.active=production", "-Xmx2048m"))
	}
}
