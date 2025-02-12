package br.com.felixgilioli.bff.config;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enables the BFF library features in the project.
 * <p>
 * This annotation should be used in a Spring configuration class
 * to automatically activate the necessary components of the library.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * &#64;Configuration
 * &#64;EnableBffEngine
 * public class BffConfig {
 *     // Additional configurations, if necessary
 * }
 * </pre>
 *
 * @see org.springframework.context.annotation.Configuration
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan("br.com.felixgilioli.bff")
public @interface EnableBffEngine {
}
