package me.blueslime.wardenplugin.information;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface WardenInformation {

    String getName() default "";

    String getVersion() default "";

    String getAuthor() default "";

    String getCollaborators() default "";

    String getDescription() default "";
}
