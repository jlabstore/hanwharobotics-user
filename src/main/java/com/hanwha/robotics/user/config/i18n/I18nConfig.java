package com.hanwha.robotics.user.config.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class I18nConfig implements WebMvcConfigurer {

   /**
    * 스프링이 클라이언트의 언어, 국가 정보를 인식하게 하는 클래스
    * @return
    */
    @Bean
    public LocaleResolver localeResolver() {
      // CookieLocaleResolver 쿠키의 값을 저장하여 사용
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.getDefault());
//        resolver.setDefaultLocale(Locale.KOREAN);
        resolver.setCookieName("lang");
        return resolver;
    }


    /**
     * locale값이 변경되면 인터셉터 동작
     * url의 query parameter에 지정한 값이 들어올 때 동작
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    /**
     * 스프링이 작성한 언어 리소스들을 사용할 수 있게 등록, 설정하는 클래스
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(10 * 60);
        return messageSource;
    }

    /**
     * LocaleChangeInterceptor를 스프링 컨테이너에 등록
     * WebMvcConfigurer를 상속받고, addInterceptors를 오버라이딩
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}

