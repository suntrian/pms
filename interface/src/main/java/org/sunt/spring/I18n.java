package org.sunt.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Optional;

@Component
public class I18n {

    private final MessageSource messageSource;

    @Autowired(required = false)
    private HttpServletRequest request;

    @Autowired
    public I18n(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String message(String key, Object... args) {
        return this.messageSource.getMessage(key, args, resolveLocale());
    }

    public String messageOrDefault(String key, String defaultMsg, Object... args) {
        return this.messageSource.getMessage(key, args, defaultMsg, resolveLocale());
    }

    private Locale resolveLocale() {
        return Optional.ofNullable(request).map(RequestContextUtils::getLocale).orElseGet(LocaleContextHolder::getLocale);
    }

    @Configuration
    public static class MessageSourceConfig {

        @Bean
        public MessageSource messageSource() {
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            messageSource.setBasename("classpath:i18n/messages");
            messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
            messageSource.setUseCodeAsDefaultMessage(true);
            return messageSource;
        }
    }

}
