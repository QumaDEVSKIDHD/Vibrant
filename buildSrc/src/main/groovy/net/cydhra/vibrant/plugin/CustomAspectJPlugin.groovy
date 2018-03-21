package net.cydhra.vibrant.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class CustomAspectJPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.configurations.create("ajc")
        project.configurations.create("aspect")

        project.task("compile-aspects") {
            doLast {
                ant.taskdef(resource: "org/aspectj/tools/ant/taskdefs/aspectjTaskdefs.properties",
                        classpath: project.configurations.ajc.asPath)

                ant.iajc(
                        fork: "false",
                        showWeaveInfo: "true",
                        verbose: "true",
                        source: project.sourceCompatibility,
                        target: project.targetCompatibility,
                        failonerror: "true",
                        destDir: project.sourceSets.main.output.classesDir.absolutePath,
                        sourceRoots: project.sourceSets.main.allSource.sourceDirectories.asPath,
                        classpath: project.sourceSets.main.runtimeClasspath.asPath,
                )
            }
        }

        project.tasks["compileJava"].dependsOn(project.tasks['compile-aspects'])
    }
}
