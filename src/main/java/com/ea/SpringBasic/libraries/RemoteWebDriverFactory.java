package com.ea.SpringBasic.libraries;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;

import java.net.URL;

@Configuration
@Profile( "remote")
public class RemoteWebDriverFactory {
    @Value( "${selenium.grid.url}")
    public URL gridUrl;

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    @Scope("driverscope")
    public WebDriver getRemoteWebDriverForChrome() {
        return new RemoteWebDriver(gridUrl, new ChromeOptions());
    }

    @Bean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    @Scope("driverscope")
    public WebDriver getRemoteWebDriverForFirefox() {
        return new RemoteWebDriver(gridUrl, new FirefoxOptions());
    }
}
