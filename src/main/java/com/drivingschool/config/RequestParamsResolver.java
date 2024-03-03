package com.drivingschool.config;

import com.drivingschool.annotations.RequestParams;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

public class RequestParamsResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestParams.class);
    }



    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        Class<?> paramType = parameter.getParameterType();

        if (paramType != null && parameter.hasParameterAnnotation(RequestParams.class)) {
            Object obj = paramType.getDeclaredConstructor().newInstance();
            Map<String, String[]> requestParams = webRequest.getParameterMap();
            RequestParams annotation = parameter.getParameterAnnotation(RequestParams.class);
            String prefix = annotation != null ? annotation.value() : "";

            MutablePropertyValues mpvs = new MutablePropertyValues();
            for (Map.Entry<String, String[]> entry : requestParams.entrySet()) {
                String propertyName = entry.getKey();
                String[] propertyValues = entry.getValue();
                if (propertyName.startsWith(prefix)) {
                    propertyName = propertyName.substring(prefix.length());
                }
                if (propertyValues != null && propertyValues.length > 0) {
                    mpvs.add(propertyName, propertyValues[0]); // Assuming single value for simplicity
                }
            }

            WebDataBinder binder = binderFactory.createBinder(webRequest, obj, null);
            binder.bind(mpvs);

            if (binder.getBindingResult().hasErrors()) {
                // Handle validation errors
                throw new BindException(binder.getBindingResult());
            }

            return obj;
        }
        return null;
    }






}