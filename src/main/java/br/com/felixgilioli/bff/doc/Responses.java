package br.com.felixgilioli.bff.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to provide one or more response examples for an endpoint.
 * <p>
 * This annotation can be applied to methods and allows you to define one or more HTTP response examples
 * using the &#64;ResponseExample annotation. Each example can have a status code, JSON template,
 * predefined data, and a description for the response.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * &#64;Responses({
 *     &#64;ResponseExample(status = 200, template = "orderCancelled.json", data = OrderCancelledDoc.class, description = "Order Cancelled"),
 *     &#64;ResponseExample(status = 200, template = "orderCreated.json", data = OrderCreatedDoc.class)
 * })
 * </pre>
 *
 * @see ResponseExample
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Responses {

    /**
     * Array of response examples.
     *
     * @return the response examples defined for the endpoint
     */
    ResponseExample[] value();

}
