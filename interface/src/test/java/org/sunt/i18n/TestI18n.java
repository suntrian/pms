package org.sunt.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.sunt.spring.I18n;

import java.nio.charset.StandardCharsets;

public class TestI18n {

    private static final Logger log = LoggerFactory.getLogger(TestI18n.class);

    private static I18n i18n;

    @BeforeAll
    public static void setUp() {
        //MessageSource messageSource = new I18n.MessageSourceConfig().messageSource();
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        i18n = new I18n(messageSource);
    }

    @Test
    public void testGetMessage() {
        String hello = i18n.message("hello");
        log.info(hello);
        Assertions.assertEquals("你好", hello);
    }

}
