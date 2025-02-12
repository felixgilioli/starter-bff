package br.com.felixgilioli.bff.template;

/**
 * Interface responsible for processing JSON templates.
 * <p>
 * This interface defines a contract for a template engine that can be injected as a Spring dependency.
 * It is responsible for processing the JSON file using the provided parameters and generating the final content.
 * </p>
 *
 * <p>
 * The default implementation of this interface is the {@link DefaultFreemarkerTemplateEngine} class,
 * which uses Freemarker for template processing.
 * </p>
 *
 * <p>
 * Example usage:
 * </p>
 *
 * <pre>
 * &#64;RestController
 * public class OrderController implements OrderApi {
 *
 *     private final TemplateEngine templateEngine;
 *
 *     public OrderController(TemplateEngine templateEngine) {
 *         this.templateEngine = templateEngine;
 *     }
 *
 *     &#64;Override
 *     public ResponseEntity&lt;Object&gt; getOrderScreen(Long orderId) {
 *         // hidden code
 *         String json = templateEngine.getTemplate(TEMPLATE_NAME, order);
 *         return ResponseEntity.ok(json);
 *     }
 * }
 * </pre>
 */
public interface TemplateEngine {

    /**
     * Processes the JSON template with the provided data.
     *
     * @param name the name of the JSON template to be processed
     * @param data the data to be injected into the template
     * @return the generated JSON with the injected data
     */
    String getTemplate(String name, Object data);
}
