plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.0.20"
//    id("org.jetbrains.intellij") version "1.17.4"
    id("org.jetbrains.intellij.platform") version "2.0.1"
//    id("PsiViewer") version "222-SNAPSHOT"
}

group = "com.onj"
version = "1.3"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html

dependencies {
    intellijPlatform {
        intellijIdeaCommunity("2024.2.1")
        instrumentationTools()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_18
}


tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "18"
        targetCompatibility = "18"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "18"
//        exclude("**/*Reference.kt")
//        exclude("**/*ReferenceContributor.kt")
    }

    patchPluginXml {
        sinceBuild.set("220.*")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
sourceSets["main"].java.srcDirs("src/main/gen")
sourceSets["main"].java.srcDirs("src/main/kotlin")