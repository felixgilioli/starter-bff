package br.com.felixgilioli.bff.doc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ResponseExampleDescriptionFormatter {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    public Optional<String> format(DocumentationData<?> documentationData) {
        if (documentationData.getExampleImages() == null || documentationData.getExampleImages().isEmpty()) {
            return Optional.empty();
        }

        var imagesHTML = documentationData.getExampleImages()
                .stream()
                .map(img -> """
                        <td>
                            %s<br>
                            <img src="%s" alt="%s" width="300"/>
                        </td>
                        """.formatted(
                        img.getDescription(),
                        getImageSrc(img.getSrc()),
                        img.getDescription()
                ))
                .collect(Collectors.joining());

        var descriptionHTML = """
                <table>
                    <tr>
                        %s
                </table>
                """.formatted(imagesHTML); // unable to close tr tag due to swagger bug

        return Optional.of(descriptionHTML);
    }

    private String getImageSrc(String imgSrc) {
        if (imgSrc == null || imgSrc.isBlank()) {
            return "";
        }
        if (imgSrc.startsWith("/")) {
            return (contextPath == null || contextPath.isBlank()) ? imgSrc : contextPath + imgSrc;
        }
        return imgSrc;
    }
}
