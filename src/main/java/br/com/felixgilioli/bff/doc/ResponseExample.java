package br.com.felixgilioli.bff.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to provide response examples for endpoints.
 * <p>
 * This annotation can be applied to methods and allows you to define HTTP response examples
 * with details such as the status code, the JSON template file, predefined data to generate
 * the JSON, and a description for the example.
 * </p>
 *
 * <p>
 * The annotation can be used inside the &#64;Responses annotation to provide multiple response examples.
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
 * @see DocumentationData
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseExample {

    /**
     * Description of the response example.
     *
     * @return a brief description of the example
     */
    String description() default "";

    /**
     * The name of the JSON file used as the template for the response.
     *
     * @return the name of the JSON template file
     */
    String template();

    /**
     * Class that implements the {@link DocumentationData} interface containing the predefined data
     * for generating the JSON.
     *
     * @return the class of data used for generating the JSON
     */
    Class<? extends DocumentationData> data() default DefaultDocData.class;

    /**
     * HTTP status code for the response example.
     *
     * @return the HTTP status code, such as 200 for success
     */
    int status();

}
