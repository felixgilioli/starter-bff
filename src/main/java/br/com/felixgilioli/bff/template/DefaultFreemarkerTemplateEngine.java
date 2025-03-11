package br.com.felixgilioli.bff.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.StringWriter;
import java.io.Writer;

@Component
public class DefaultFreemarkerTemplateEngine implements TemplateEngine {

    private Configuration freemarkerConfiguration;

    @Value("${bff.templates.directory:templates}")
    private String templateDirectory;

    @PostConstruct
    public void init() {
        this.freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_34);
        freemarkerConfiguration.setClassForTemplateLoading(getClass(), "/" + templateDirectory.replaceFirst("^/", ""));
        freemarkerConfiguration.setDefaultEncoding("UTF-8");
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
