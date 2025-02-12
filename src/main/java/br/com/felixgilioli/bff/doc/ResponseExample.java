package br.com.felixgilioli.bff.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseExample {

    String description() default "";

    String template();

    Class<? extends DocumentationData> data();

    int status();

}
