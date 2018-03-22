package net.cydhra.vibrant.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A custom AspectJ compiler plugin that is actually capable of including sub-modules into aspect-path.
 */
class CustomAspectJPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.configurations.create("ajc")

        project.task("compile-aspects") {
            // depend on everything necessary to fulfill a jar task. This includes escpecially the assembly of dependent sub-modules
            dependsOn project.configurations*.getTaskDependencyFromProjectDependency(true, "jar")
            dependsOn project.processResources

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
                        classpath: project.sourceSets.main.compileClasspath.asPath,
                )
            }
        }

        // decompose the java compile task
        project.tasks.compileJava.deleteAllActions()

        // and add its only new dependency on this task
        project.tasks.compileJava.dependsOn(project.tasks['compile-aspects'])
    }
}
