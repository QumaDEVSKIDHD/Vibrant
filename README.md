# Vibrant
 A Minecraft Hacked Client written in Kotlin and AspectJ

## License
This project is subject to the [GNU General Public License v3.0](LICENSE). This does only apply for source code located directly in this clean repository. Since the compilation requires the utilization of [MCP](http://www.modcoderpack.com/) in order to properly compile a working Minecraft client application, it will generate additionally source code. This additionally code is not covered by the license nor do we hold any rights to that code.

For those, who are not familiar with the license, I will summarize it in a view headwords. This is by no means legal advise nor legally binding.

You are allowed to
- use
- share
- modify

this project entirely or partially for free and even commercially. But:

- **You must disclose the source code of your modified work or the source code you used of this project. This means espacially, you are not allowed to use code of this project (even partially) in a closed-source (or even obfuscated) application.**
- **Your modified application must also be licensed to the GPL** 

So don't be like that and share your source code with everyone, just like we do.

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

## Contributing
We always do appreciate contribution to our project. But since we do use some very new and some very rare technology, this might be hard. Here are a few points to get started:

- While AspectJ is only a few years younger than Java, it has not been generally adopted. This means your choice of IDE is rather restricted (if you do not want to do everything manually): 
  - Eclipse is actually a good choice for that, because AspectJ is a project of the Eclipse Foundation, so it should have (though I did not test it) the best support for the language.
  - IntelliJ does have an AspectJ support plugin, but it does not support every feature of the language and it is only available in the ultimate version of the IDE. So you either need to be a student or you'll have to buy it.
  - Netbeans is just horrible handling AspectJ. Don't do that.
- Kotlin however is rather new. Since the language is developed by Jetbrains, the company behind IntelliJ, you really should use it. But there is also an [Eclipse plugin](https://github.com/JetBrains/kotlin-eclipse) for Kotlin (and since it is also developed by Jetbrains, it might be rather good). Same thing for [Netbeans](https://github.com/JetBrains/kotlin-netbeans).
- Please be careful with IDE settings however: If you do not use IntelliJ (like we do) we would really appreciate it, if you update the .gitignore with all configuration files, that do not belong in the repository and only push the required ones.
If you happen to use IntelliJ, then please do only alter the IDE settings that are pushed to this repository if absolutely required. IntelliJ seems to alter them pretty aggressively on import.
- We might reject Pull-Requests not conforming to our standards of project structure. This hacked client implementation uses rather complex utilities in order to prevent bugs or interferences of different modules. If you do not know how to use those utilities, feel free to ask.
