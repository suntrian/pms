package org.sunt.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class I18n {

    private final MessageSource messageSource;

    @Autowired
    public I18n(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String message(String key, Object... args) {
        return this.messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }

    public String messageOrDefault(String key, String defaultMsg, Object... args) {
        return this.messageSource.getMessage(key, args, defaultMsg, LocaleContextHolder.getLocale());
    }

}
