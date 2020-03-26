package com.example.demo.shorten.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ToString
@FieldDefaults(level= AccessLevel.PRIVATE)
@Configuration
@ConfigurationProperties(prefix = "application")
public class ServiceProperties {

    String homePath;
}
