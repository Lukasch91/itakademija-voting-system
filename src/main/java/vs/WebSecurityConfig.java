package vs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.headers().disable()
		.authorizeRequests()
			.antMatchers(/*"/", "/js/**", */"/api/**", "/", "/index.html", "/js/**").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.permitAll()
			.and()
		.logout()
			.permitAll();

	}

	@Autowired
	public void configureGlobal(DataSource dataSource, AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("pass").roles("USER").and()
				.withUser("admin").password("pass").roles("ADMIN");
			/*.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select login_name, password, true from representatives where login_name = ?")
				.authoritiesByUsernameQuery("select login_name, 'ROLE_USER' from representatives where login_name = ?");*/
	}
}