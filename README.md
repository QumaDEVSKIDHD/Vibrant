# Vibrant
 A Minecraft Hacked Client written in Kotlin and AspectJ
## Download
Latest Nigthly

[![Build Status](https://jenkins.flaflo.xyz/buildStatus/icon?job=Vibrant)](https://jenkins.flaflo.xyz/job/Vibrant)

## Compilation
1. Clone `https://github.com/Cydhra/Vibrant.git`.
2. Choose which Minecraft versions you want to compile for. Make sure you have a clean installation of all those Minecraft versions.
3. For every version you want to compile, run the `setupWorkspace` gradle task (for the specific submodule)
5. Run gradle task `shadowJar` to build with dependecies (either for the root project, or for each sub-module that is set up)
6. Obtain the finished client artifacts from the sub-module build/lib folders.

Warning: The `setupWorkspace` task is untested for unix operating systems.
