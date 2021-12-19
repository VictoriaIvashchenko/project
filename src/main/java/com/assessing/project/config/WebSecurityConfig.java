package com.assessing.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;


    @Autowired
    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable().authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers(HttpMethod.GET,"/student").hasAuthority("STUDENT")
                    .antMatchers(HttpMethod.GET,"/admin","/add", "/add_admin", "/add_student",
                      "/add_teacher", "/adddata", "/adddata_faculty", "/adddata_group", "/adddata_speciality",
                      "/admin_report", "/admin_report_course", "/admin_report_faculty", "/admin_report_group",
                      "/admin_report_speciality", "/Admin_teacher_page", "/Admin_teacher_page_add_new_subject",
                      "/Admin_teacher_page_subject_info", "/exams", "/exams_course", "/exams_faculty", "/exams_group",
                      "/exams_speciality").hasAuthority("ADMIN")
                    .antMatchers("/teacher","/Teacher_report", "/Teacher_report_group_option",
                            "/Teacher_report_subject_option").hasAuthority("TEACHER")
                .anyRequest()
                    .authenticated().and()
                .formLogin()
                    .loginPage("/").permitAll()
                    .loginProcessingUrl("/")
                    .successHandler(myAuthenticationSuccessHandler())
                    .failureUrl("/login?error=true")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .and()
                .logout()
                    .permitAll()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/")
                    .and().httpBasic();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());


    }


    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder(12);
    }

    @Override
    public void configure(WebSecurity web){
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySuccessHandler();
    }
}