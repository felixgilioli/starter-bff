package br.com.felixgilioli.bff.config;

import br.com.felixgilioli.bff.template.TemplateEngine;
import br.com.felixgilioli.bff.utils.JsonUtils;
import br.com.felixgilioli.bff.web.ViewModel;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ViewModelToJsonAdvice implements ResponseBodyAdvice<Object> {

    private final TemplateEngine templateEngine;

    public ViewModelToJsonAdvice(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            return ViewModel.class.isAssignableFrom(returnType.getParameterType()) ||
                    returnType.getGenericParameterType().getTypeName().contains(ViewModel.class.getName());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<?
                                          extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof ViewModel viewModel) {
            String json = templateEngine.getTemplate(viewModel.viewName(), viewModel.params());
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return JsonUtils.readTree(json);
        }
        return body;
    }

}