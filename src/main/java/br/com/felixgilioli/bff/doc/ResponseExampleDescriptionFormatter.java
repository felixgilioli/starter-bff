package br.com.felixgilioli.bff.doc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ResponseExampleDescriptionFormatter {

    @Value("${server.servlet.context-path:}")
    private String contextPath;

    private static <T> List<List<T>> partition(List<T> list, int size) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }

    public Optional<String> format(DocumentationData<?> documentationData) {
        List<DocumentationExampleImage> images = documentationData.getExampleImages();
        if (images == null || images.isEmpty()) {
            return Optional.empty();
        }

        var rowsHtml = partition(images, 3).stream()
                .map(group -> group.stream()
                        .map(img -> """
                        <td>
                            %s<br>
                            <img src="%s" alt="%s" width="300"/>
                        </td>
                        """.formatted(
                                        img.getDescription(),
                                        getImageSrc(img.getSrc()),
                                        img.getDescription()
                                )
                        ).collect(Collectors.joining())
                )
                .map(tds -> "<tr>" + tds + "</tr>")
                .collect(Collectors.joining());

        var descriptionHTML = """
            <table>
                %s
            </table>
            """.formatted(rowsHtml);

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
