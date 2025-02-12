package br.com.felixgilioli.bff.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

@Component
public class DefaultFreemarkerTemplateEngine implements TemplateEngine {

    private Configuration freemarkerConfiguration;

    @PostConstruct
    public void init() {
        try {
            this.freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_34);
            freemarkerConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getTemplate(String name, Object data) {
        try {
            Template template = freemarkerConfiguration.getTemplate(name + ".json");
            Writer out = new StringWriter();
            template.process(data, out);
            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
