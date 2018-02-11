package net.cydhra.vibrant.generator;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
@Repeatable(InjectResources.class)
@Retention(RetentionPolicy.SOURCE)
public @interface InjectResource {
    String filename();
    
    String fieldName();
}
