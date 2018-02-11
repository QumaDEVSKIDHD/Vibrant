package net.cydhra.vibrant.generator

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asClassName
import java.io.File
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 *
 */
class ResourceInjectorProcessor : AbstractProcessor() {

    companion object {
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(InjectResource::class.java.name, InjectResources::class.java.name)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(InjectResources::class.java)
                .filterIsInstance<TypeElement>()
                .forEach { annotatedElement ->
                    annotatedElement.getAnnotationsByType(InjectResource::class.java).forEach { annotation ->
                        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "ResourceInjector: Now processing ${annotatedElement.simpleName}")
                        generateResources(
                                annotatedElement,
                                annotation.filename,
                                annotation.fieldName)
                    }
                }

        return true
    }

    private fun generateResources(element: TypeElement, filename: String, fieldName: String) {
        val content = this.javaClass.getResourceAsStream(filename).reader().readText()
        val genFilename = "injected\$$fieldName"

        FileSpec.builder(element.asClassName().packageName(), genFilename)
                .addProperty(
                        PropertySpec
                                .builder(fieldName, String::class, KModifier.INTERNAL, KModifier.CONST)
                                .initializer("\"\"\"%L\"\"\"", content)
                                .build()
                )
                .build()
                .writeTo(File(processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME]))
    }

}