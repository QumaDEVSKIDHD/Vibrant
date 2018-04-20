package net.cydhra.vibrant.modulesystem

import org.reflections.Reflections
import org.reflections.scanners.SubTypesScanner
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder

class ReflectionModuleLoader : ModuleLoader {

    override fun loadModules(): List<Module> {
        val reflections = Reflections(ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("net.cydhra.vibrant.modules"))
                .setScanners(SubTypesScanner(true))
        )

        return reflections.getSubTypesOf(Module::class.java).map { it.newInstance() }
    }
}