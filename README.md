# Vibrant
 A Minecraft Hacked Client written in Kotlin and AspectJ

## Compilation
1. Clone `https://github.com/Cydhra/Vibrant.git`.
2. Choose which Minecraft versions you want to compile for. Make sure you have a clean installation of all those Minecraft versions. 
(Currently only 1.8.8 is supported)
3. For every version you want to compile, run the `setupWorkspace` gradle task (for the specific submodule, e.g. for vibrant-1-8-8)
5. Run gradle task `shadowJar` to build with dependencies (either for the root project, or for each sub-module that is set up: In case 
you run it for the root project, every submodule must be set up with `setupWorkspace`)
6. Obtain the finished client artifacts from the sub-module build/lib folders.

Warning: The `setupWorkspace` won't work on Mac OS systems. However, it utilizes MCP so you should be able to rewrite the task for it. 
You can find the groovy class in the buildSrc folder. Just add calls for system-dependent commands. 