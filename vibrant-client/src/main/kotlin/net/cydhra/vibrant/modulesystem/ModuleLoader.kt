package net.cydhra.vibrant.modulesystem

interface ModuleLoader {

    fun loadModules(): List<Module>
}