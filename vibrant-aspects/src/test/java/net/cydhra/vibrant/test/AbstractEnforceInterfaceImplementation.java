package net.cydhra.vibrant.test;

import com.sun.deploy.util.StringUtils;
import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * Test case to enforce full implementation of the Vibrant API interfaces
 */
public abstract class AbstractEnforceInterfaceImplementation {

    @Test
    public final void enforceInterfaceImplementation() {
        List<ClassLoader> classLoadersList = new LinkedList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        // reflection of the API required to be injected
        Reflections apiReflection = new Reflections(
                new ConfigurationBuilder()
                        .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                        .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                        .filterInputsBy(new FilterBuilder()
                                .include(FilterBuilder.prefix("net.cydhra.vibrant.api"))));

        // reflection of the finished artifact where the API must be injected into
        Reflections minecraftReflection = new Reflections(
                new ConfigurationBuilder()
                        .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                        .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                        .filterInputsBy(new FilterBuilder()
                                .include(FilterBuilder.prefix("(net.minecraft|net.cydhra.vibrant)"))));

        Set<Class<?>> apiClasses = apiReflection.getSubTypesOf(Object.class);
        // prevent early fail
        boolean fail = false;
        List<String> reasons = new ArrayList<>();

        for (Class<?> cls : apiClasses) {
            if (!cls.isInterface())
                continue;

            if (minecraftReflection.getSubTypesOf(cls).stream().allMatch(Class::isInterface)) {
                fail = true;
                reasons.add(cls.getName() + " is not injected.");
            }
        }

        if (fail) {
            fail("API is not fully injected (" + reasons.size() + " assertions failed): \n"
                    + StringUtils.join(reasons, "\n"));
        }
    }
}
