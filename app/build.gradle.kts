plugins {
    id("java")
    id("checkstyle")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("application")
    id("org.sonarqube") version "6.2.0.5505"
    id("jacoco")
    id("io.freefair.lombok") version "8.13.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("hexlet.code.App")
}
checkstyle {
    toolVersion = "10.26.1"
    config = resources.text.fromFile("config/checkstyle/checkstyle.xml")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    implementation("org.apache.commons:commons-lang3:3.17.0")
    implementation("org.apache.commons:commons-collections4:4.5.0-M3")
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("info.picocli:picocli:4.7.7")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.0")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testCompileOnly("org.projectlombok:lombok:1.18.38")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.38")

}

sonar {
    properties {
        property("sonar.projectKey", "vvvilkha_java-project-71")
        property("sonar.organization", "vvvilkha")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

tasks.test {
    useJUnitPlatform()

}
jacoco {
    toolVersion = "0.8.13"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}