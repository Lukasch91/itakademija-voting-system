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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().headers().disable().authorizeRequests().antMatchers("/api/**", "/", "/js/**", "/test.css").permitAll()
				/*
				 * .antMatchers("/admin/**").hasRole("ADMIN")
				 * .antMatchers("/rep/**").hasRole("USER")
				 */
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll();

	}

	@Autowired
	public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("pass").roles("ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

	}

}