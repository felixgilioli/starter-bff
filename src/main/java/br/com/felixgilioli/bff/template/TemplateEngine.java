package br.com.felixgilioli.bff.template;

public interface TemplateEngine {

    String getTemplate(String name, Object data);
}
