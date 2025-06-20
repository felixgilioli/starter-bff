package br.com.felixgilioli.bff.doc;

import br.com.felixgilioli.bff.template.TemplateEngine;
import br.com.felixgilioli.bff.utils.JsonUtils;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Component
public class OpenApiTemplateEngineCustomizer implements OperationCustomizer {

    private final TemplateEngine templateEngine;
    private final ResponseExampleDescriptionFormatter responseExampleDescriptionFormatter;

    public OpenApiTemplateEngineCustomizer(TemplateEngine templateEngine,
                                           ResponseExampleDescriptionFormatter responseExampleDescriptionFormatter) {
        this.templateEngine = templateEngine;
        this.responseExampleDescriptionFormatter = responseExampleDescriptionFormatter;
    }

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        Responses responses = handlerMethod.getMethodAnnotation(Responses.class);

        if (responses == null || responses.value().length == 0) {
            return operation;
        }

        Map<Integer, List<ResponseExample>> responsesByStatus = Arrays.stream(responses.value())
                .collect(Collectors.groupingBy(ResponseExample::status));

        if (!responsesByStatus.containsKey(200)) {
            operation.getResponses().remove("200");
        }

        for (Integer status : responsesByStatus.keySet()) {
            var httpStatus = String.valueOf(status);
            ApiResponse apiResponse = operation.getResponses().get(httpStatus);

            if (apiResponse == null) {
                apiResponse = new ApiResponse();
                operation.getResponses().addApiResponse(httpStatus, apiResponse);
            }

            var mediaType = new MediaType();
            int exampleNumber = 1;

            for (ResponseExample responseExample : responsesByStatus.get(status)) {
                var documentationData = getDocumentationData(responseExample);
                var jsonString = templateEngine.getTemplate(responseExample.template(), documentationData.getData());

                mediaType.addExamples(
                        isNotBlank(responseExample.description())
                                ? responseExample.description()
                                : "Example " + exampleNumber++,
                        new Example()
                                .description(responseExampleDescriptionFormatter.format(documentationData).orElse(null))
                                .value(JsonUtils.readTree(jsonString))
                );

            }


            apiResponse.content(new Content().addMediaType("application/json", mediaType));
        }

        return operation;
    }

    private DocumentationData getDocumentationData(ResponseExample responseExample) {
        try {
            return responseExample.data().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
