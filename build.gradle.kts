// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.jetbrains.kotlin) apply false
  alias(libs.plugins.jetbrains.dokka) apply true
  `maven-publish`
}

publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/uedermdscardoso/RootEncoder")
      credentials {
        username = System.getenv("GITHUB_USERNAME")
        password = System.getenv("GITHUB_TOKEN")
      }
    }
  }
  publications {
    create<MavenPublication>("gpr") {
      from(components["java"])
    }
  }
}

tasks.register("clean") {
  delete(layout.buildDirectory)
}

tasks.dokkaHtmlMultiModule.configure {
  outputDirectory.set(File("docs"))
}