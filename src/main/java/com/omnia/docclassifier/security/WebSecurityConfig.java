package com.omnia.docclassifier.security;

import com.omnia.docclassifier.config.AppConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@Getter
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AppConfig config;
    private BCryptPasswordEncoder passwordEncoder;
    private Environment environment;

    @Autowired
    public WebSecurityConfig(AppConfig config, BCryptPasswordEncoder encoder, Environment env) {
        super();
        this.config = config;
        this.passwordEncoder = encoder;
        this.environment = env;
    }


    private boolean isUnitTest() {
        for(String env : this.environment.getActiveProfiles()) {
            if (env.equalsIgnoreCase("unittest")) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (this.isUnitTest()) {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/**")
                    .permitAll();
        }
        else {

            String apiBase = this.getConfig().getApiRestBase();

            http
                    .csrf().disable()
                    .authorizeRequests()

                    // version, login and media are available directly
                    .mvcMatchers("/version/**",
                            "/css/**",
                            "/images/**",
                            "/js/**",
                            "/favicon.ico")
                    .permitAll();

//                    .and()
//                    .formLogin()
//                    .loginPage(this.getConfig().getLoginPage()).permitAll()
//                    .loginProcessingUrl(this.getConfig().getLoginProcessingUrl())
//                    .defaultSuccessUrl(this.getConfig().getSuccessfulLoginUrl())
//                    .failureUrl(this.getConfig().getLoginFailureUrl())
//
//                    .and()
//                    .logout().permitAll()
//                    .logoutRequestMatcher(new AntPathRequestMatcher((this.getConfig().getLogOutUrl())));


        }
    }
}