package com.gibatekpro.jackson_json_databinding.config;

import com.google.protobuf.Internal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan("com.gibatekpro.jackson_json_databinding")
@PropertySource("classpath:")
public class AppConfig {

    @Autowired
    private Environment environment;

    private Logger logger = Logger.getLogger(getClass().getName());

}
