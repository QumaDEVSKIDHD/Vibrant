# Vibrant
 A Minecraft Hacked Client written in Kotlin and AspectJ

## License
This project is subject to the [GNU General Public License v3.0](LICENSE). This does only apply for source code located directly in this clean repository. Since the compilation requires the use of [MCP](http://www.modcoderpack.com/) in order to properly compile a working Minecraft client application, it will generate additional source code. This additional code is not covered by the license nor do we hold any rights to it.

For those who are not familiar with the license I will summarize the main points. This is by no means legal advise nor legally binding.

You are allowed to
- use
- share
- modify

this project entirely or partially for free and even commercially. However, please consider the following:

- **You must disclose the source code of your modified work or the source code you took from this project. This means you are not allowed to use code from this project (even partially) in a closed-source (or even obfuscated) application.**
- **Your modified application must also be licensed under the GPL** 

Do the above and share your source code with everyone; just like we do.

## Compilation
1. Clone `https://github.com/Cydhra/Vibrant.git`.
2. Choose which Minecraft versions you want to compile for. Make sure you have a clean installation of all those Minecraft versions. 
(Currently only 1.8.8 is supported)
3. For every version you want to compile, run the `setupWorkspace` gradle task (for the specific submodule, e.g. for vibrant-1-8-8)
5. Run gradle task `shadowJar` to build with dependencies (either for the root project or for each sub-module that is set up: In case 
you run it for the root project, every submodule must be set up with `setupWorkspace`)
6. Obtain the finished client artifacts from the sub-module build/lib folders.

Warning: `setupWorkspace` does not work work on MacOS. However, since it utilizes MCP, you should be able to rewrite the task for it. 
You can find the groovy class in the buildSrc folder. Just add calls for system-dependent commands. 

## Contributing
We always appreciate contribution to our project. However, since we are using some very new and rare technology, it might not be as easy as expected. Here are a few points to get you started:

- While AspectJ is only a few years younger than Java, it has not been generally adopted. This means you are limited to the use of just a few well-known IDEs (At least if you do not want to do everything completely manually): 
  - Eclipse is probably a good choice when working with AspectJ because it is one of the Eclipse Foundation's projects as well and therefore should have the best support for the language (though I did not test it).
  - IntelliJ has a plugin that adds AspectJ support. Unfortunately it does not support every feature the language offers and is only available for the ultimate version of the IDE. If you are not a student, you would have to purchase it in order to use it.
  - Netbeans is just horrible at handling AspectJ. Do not use it.
- Kotlin is a relatively young language. Since it is being developed by Jetbrains, the company behind IntelliJ, you should use their IDE when working with it. However, there is an [Eclipse plugin](https://github.com/JetBrains/kotlin-eclipse) for Kotlin (and since it is also being developed by Jetbrains and therefore might be rather good) as well. Same thing for [Netbeans](https://github.com/JetBrains/kotlin-netbeans).
- Please be careful with IDE settings however: If you do not use IntelliJ (as we do), we would really appreciate it if you updated the .gitignore with all configuration files that do not belong in the repository and only push the required ones.
If you happen to use IntelliJ, please only alter the IDE settings that are pushed to this repository if absolutely required. IntelliJ seems to modify them rather aggressively on import.
- We might reject Pull-Requests not conforming to our standards of project structure. This hacked client implementation is using advanced utilities in order to prevent bugs or interferences between different modules. If you do not know how to use those utilities, feel free to ask.
