package com.liftkarade.app.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import com.liftkarade.app.handler.MyAccessDeniedHandler;
import com.liftkarade.app.handler.MySuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	Environment env;
	
	@Autowired(required=true)
	DriverManagerDataSource dataSource1;
	
	@Autowired
	SessionRegistry sessionRegistry;
	
	@Autowired
	ConcurrentSessionControlAuthenticationStrategy sessionControlStrategy;
	
	@Autowired
	MySuccessHandler mySuccessHandler;
	
	@Autowired
	SimpleUrlAuthenticationFailureHandler myFailureHandler;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	UsernamePasswordAuthenticationFilter authenticationFilter;
	
	@Autowired
	ConcurrentSessionFilter concurrencyFilter;
	
	@Autowired
	LoginUrlAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	AccessDecisionManager accessDecisionManager;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.dataSource(dataSource1)
					.authoritiesByUsernameQuery("select u.username, ur.authority from user u, userroles ur where u.id=ur.id and u.username =?")
					.usersByUsernameQuery("SELECT username,password,1 as enabled from user WHERE username=?");
	}
	
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
		RoleVoter roleVoter = new RoleVoter();
		WebExpressionVoter expressionVoter = new WebExpressionVoter();
		AuthenticatedVoter aVoter = new AuthenticatedVoter();
		decisionVoters.add(aVoter);
		decisionVoters.add(roleVoter);
		decisionVoters.add(expressionVoter);
		UnanimousBased unanimousBased = new UnanimousBased(decisionVoters);
		return unanimousBased;
	}
	
	@Bean(name="dataSource1")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql//localhost:3306/test1");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	///loginfailed
	@Bean
	public MySuccessHandler mySuccessHandler() {
		return new MySuccessHandler();
	}
	
	@Bean
	public SimpleUrlAuthenticationFailureHandler myFailureHandler() {
		SimpleUrlAuthenticationFailureHandler failreHandler = new SimpleUrlAuthenticationFailureHandler();
		failreHandler.setDefaultFailureUrl("/loginfailed");
		return failreHandler;
	}
	
	@Bean
	public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
		return new LoginUrlAuthenticationEntryPoint("/login");
	}
	
	
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
	
	@Bean
	public ConcurrentSessionControlAuthenticationStrategy sessionControlStrategy() {
		ConcurrentSessionControlAuthenticationStrategy sessionControlStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
		sessionControlStrategy.setMaximumSessions(1);
		return sessionControlStrategy;
	}
	
	@Bean
	public UsernamePasswordAuthenticationFilter authenticationFilter() {
		UsernamePasswordAuthenticationFilter authFilter = new UsernamePasswordAuthenticationFilter();
		authFilter.setSessionAuthenticationStrategy(sessionControlStrategy);
		authFilter.setAuthenticationManager(authenticationManager);
		authFilter.setAuthenticationFailureHandler(myFailureHandler);
		authFilter.setAuthenticationSuccessHandler(mySuccessHandler);
		return authFilter;
	}
	
	@Bean 
	public MyAccessDeniedHandler accessDeniedHandler() {
		MyAccessDeniedHandler handler = new MyAccessDeniedHandler();
		handler.setAccessDeniedUrl("access_denied");
		return handler;
	}
	
	@Bean
	public ConcurrentSessionFilter concurrencyFilter() {
		return new ConcurrentSessionFilter(sessionRegistry);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**");
	      
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
        http.httpBasic();http.csrf();
        http.logout().logoutSuccessUrl("/logout").invalidateHttpSession(true);
        http.sessionManagement().invalidSessionUrl("/");
        http.portMapper().http(8080).mapsTo(8443);
        http.sessionManagement().sessionAuthenticationStrategy(sessionControlStrategy);
        http.addFilter(authenticationFilter);
        http.addFilter(concurrencyFilter);
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
        http.authorizeRequests().accessDecisionManager(accessDecisionManager)
            .antMatchers("/user*").access("hasRole('USER')")
            .antMatchers("/admin*").access("hasRole('ADMIN')")
            .antMatchers("/show*").access("hasRole('USER')")
            .antMatchers("/register*").access("hasRole('ANONYMOUS')")
        	.antMatchers("/resources/**").permitAll();
        http.formLogin().loginPage("/login").passwordParameter("password").usernameParameter("username");
        
            
    }
	
	@Bean(name="authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
