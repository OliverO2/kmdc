import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins { `kotlin-dsl` }

repositories {
  gradlePluginPortal()
  mavenCentral()
  google()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
}

tasks {
  project.properties["org.gradle.project.targetCompatibility"]?.toString()?.let {
    withType<KotlinCompile> { kotlinOptions { jvmTarget = it } }
    withType<JavaCompile> { targetCompatibility = it }
  }
}
