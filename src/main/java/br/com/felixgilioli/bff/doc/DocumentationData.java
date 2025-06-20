package br.com.felixgilioli.bff.doc;

import java.util.List;

/**
 * Represents a contract for providing predefined data to be used
 * as parameters in JSON generation through a template engine.
 * <p>
 * Implementing classes should return an object containing
 * preset values that will be injected into the template.
 * </p>
 *
 * <p>
 * Example implementation:
 * </p>
 *
 * <pre>
 * public class OrderCancelledDoc implements DocumentationData&lt;Order&gt; {
 *
 *     &#64;Override
 *     public Order getData() {
 *         return new Order(
 *                 1L,
 *                 BigDecimal.TEN,
 *                 OrderStatus.Cancelled
 *         );
 *     }
 * }
 * </pre>
 */
public interface DocumentationData<T> {

    /**
     * Returns a predefined object to be used as a parameter
     * for JSON generation via a template engine.
     *
     * @return an object with preset values for template processing
     */
    T getData();


    /**
     * Returns a list of example images that can be used in documentation.
     * Each image is represented by a {@link DocumentationExampleImage} object.
     *
     * @return a list of example images, or an empty list if none are available
     */
    default List<DocumentationExampleImage> getExampleImages() {
        return List.of();
    }

}
