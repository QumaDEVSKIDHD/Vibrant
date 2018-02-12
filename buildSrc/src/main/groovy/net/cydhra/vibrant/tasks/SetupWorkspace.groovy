package net.cydhra.vibrant.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class SetupWorkspace extends DefaultTask {

    String mcpReleaseUrl
    String minecraftVersion

    @TaskAction
    def setupWorkspace() {
        println "Setting up workspace for MC Version $minecraftVersion"

        if (mcpReleaseUrl == null) {
            throw new IllegalArgumentException("MCP Release URL not set.")
        }

        if (minecraftVersion == null) {
            throw new IllegalArgumentException("Minecraft Version not set.")
        }

        // create a temporary working dir
        File tmpFolder = new File("tmp")
        tmpFolder.with {
            deleteDir()
            mkdir()
        }

        // create ant for complex tasks
        def ant = new AntBuilder()

        println "Downloading MCP..."
        ant.get(
                src: mcpReleaseUrl.toString(),
                dest: "tmp/mcp.zip",
                verbose: true
        )

        println "Unzipping MCP..."
        ant.unzip(
                src: "tmp/mcp.zip",
                dest: "tmp/",
                overwrite: true,
        )

        println "Executing MCP..."
        ant.exec(
                command: "tmp/runtime/bin/python/python_mcp.exe runtime/decompile.py %*",
                dir: "tmp",
                osfamily: "windows"
        )
        ant.exec(
                command: "/bin/bash decompile.sh",
                dir: "tmp",
                osfamily: "unix"
        )

        println "Copying sources..."
        copyDirectory(ant, "tmp/src/minecraft", "src/main/java")

        println "Rearrange..."
        ant.move(
                file: "src/main/java/Start.java",
                tofile: "src/main/java/net/minecraft/Start.java",
                overwrite: true,
                verbose: true
        )

        println "Copying Libraries..."
        copyDirectory(ant, "tmp/jars/libraries/com/mojang/authlib/", "lib/com/mojang/authlib/")
        copyDirectory(ant, "tmp/jars/libraries/com/paulscode/codecjorbis/", "lib/com/paulscode/codecjorbis/")
        copyDirectory(ant, "tmp/jars/libraries/com/paulscode/codecwav/", "lib/com/paulscode/codecwav/")
        copyDirectory(ant, "tmp/jars/libraries/com/paulscode/libraryjavasound/", "lib/com/paulscode/libraryjavasound/")
        copyDirectory(ant, "tmp/jars/libraries/com/paulscode/librarylwjglopenal/", "lib/paulscode/librarylwjglopenal/")
        copyDirectory(ant, "tmp/jars/libraries/com/paulscode/soundsystem/", "lib/com/paulscode/soundsystem/")
        copyDirectory(ant, "tmp/jars/libraries/oshi-project/oshi-core/", "lib/oshi-project/oshi-core/")
        copyDirectory(ant, "tmp/jars/libraries/tv/twitch/", "lib/tv/twitch/")

        println "Copying workspace..."
        copyDirectory(ant, "tmp/jars/", "workspace")

        println "Cleanup..."
        tmpFolder.delete()
    }

    private def copyDirectory(AntBuilder ant, String source, String dest) {
        ant.copy(
                todir: dest,
                overwrite: true,
                verbose: true
        ) {
            fileset(dir: source)
        }
    }
}