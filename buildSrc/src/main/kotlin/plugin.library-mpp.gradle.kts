import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinNativeCompile
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.target.HostManager
import util.buildHost

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("plugin.common")
  id("io.kotest.multiplatform")
  id("dev.petuska.klip")
}

kotlin {
  explicitApi()
  js {
    useCommonJs()
    browser()
  }
  sourceSets {
    commonTest {
      dependencies {
        implementation(project(":test"))
      }
    }
  }
}

tasks {
  project.properties["org.gradletargetCompatibility"]?.toString()?.let {
    withType<KotlinCompile> { kotlinOptions { jvmTarget = it } }
    withType<AbstractCompile> { targetCompatibility = it }
  }
  withType<CInteropProcess> { onlyIf { konanTarget.buildHost == HostManager.host.family } }
  withType<AbstractKotlinNativeCompile<*, *>> {
    onlyIf { compilation.konanTarget.buildHost == HostManager.host.family }
  }
}
