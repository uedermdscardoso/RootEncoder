// Top-level build file where you can add configuration options common to all sub-projects/modules.
group = "com.one.plugin"
version = "2.5.5"  

plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.android.library) apply false
  alias(libs.plugins.jetbrains.kotlin) apply false
  alias(libs.plugins.jetbrains.dokka) apply true
  kotlin("jvm") version "2.1.0" // A versão do Kotlin pode variar
  id("maven-publish") // Necessário para o bloco 'publishing'
}

publishing {
  repositories {
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/uedermdscardoso/RootEncoder")
      credentials {
        username = System.getenv("GITHUB_ACTOR")
        password = System.getenv("GITHUB_TOKEN")
        //username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
        //password = project.findProperty("gpr.token") as String? ?: System.getenv("GITHUB_TOKEN")
      }
    }
  }
  publications {
    create<MavenPublication>("gpr") {
      groupId = "com.one.plugin"
      artifactId = "root-encoder"
      version = "2.5.5" 
     
      from(components["kotlin"])
      //from(components["java"])
    }
  }
}

/*tasks.register("clean") {
  delete(layout.buildDirectory)
}*/

tasks.dokkaHtmlMultiModule.configure {
  outputDirectory.set(File("docs"))
}