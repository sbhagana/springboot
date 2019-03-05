package com.ss.easie.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
    private String usersQuery;
	
	 @Value("${spring.queries.roles-query}")
	    private String rolesQuery;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		
		auth.jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(rolesQuery)
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);

	}
	
		@Override
	    protected void configure(HttpSecurity http) throws Exception {
	       
			
			http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/resources/**","/resources/static/assets/*").permitAll().anyRequest().permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/registration").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.defaultSuccessUrl("/admin/home")
				.usernameParameter("username")
				.passwordParameter("password")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/access-denied");
			
//			http
//	            .authorizeRequests()
//	            .antMatchers("/resources/**","/resources/static/assets/*").permitAll().anyRequest().permitAll()
//	                .antMatchers("/", "/home").permitAll()
//	                //.anyRequest().authenticated()
//		            .and()
//	            .formLogin()
//	                .loginPage("/login")
//	                .defaultSuccessUrl("/home")
//	                .permitAll()
//	                .and()
//	            .logout()
//	                .permitAll();
	    }

	    @Bean
	     @Override
	    public UserDetailsService userDetailsService() {
	        UserDetails user =
	             User.withDefaultPasswordEncoder()
	                .username("user")
	                .password("12345")
	                .roles("USER")
	                .build();

	        return new InMemoryUserDetailsManager(user);
	    }
	
}

