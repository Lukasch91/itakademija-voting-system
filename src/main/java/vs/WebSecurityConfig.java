package vs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import vs.utils_.password.PasswordService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserRepositoryUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	public PasswordService bCryptPasswordEncoder() {
		return new PasswordService();
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private CustomSuccessHandler successHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.headers().disable() //enables h2-console
		.authorizeRequests()
			.antMatchers("/","/api/PUBLIC/**", "/js/public/**", "/test.css", "/login_style.css", "/images/**", "**/favicon.ico").permitAll()				
				.antMatchers("/api/ADMIN/**", "/admin", "/js/admin/**", "/swagger-ui.html/**", "/h2-console/**").hasRole("ADMIN")
				.antMatchers("/api/REPRES/**", "/rep", "/js/representative/**").hasRole("REPRESENTATIVE")	 
				.anyRequest().authenticated().and()
				.formLogin().loginPage("/login").successHandler(successHandler)
				.permitAll().and()
				.logout().permitAll();
	}

	@Autowired
	public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

	}

}