package vs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;//file upload
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder; //swagger
import springfox.documentation.builders.RequestHandlerSelectors; //swagger
import springfox.documentation.service.ApiInfo; //swagger
import springfox.documentation.spi.DocumentationType; //swagger
import springfox.documentation.spring.web.plugins.Docket; //swagger
import springfox.documentation.swagger2.annotations.EnableSwagger2; //swagger
import vs.admin.features.candidate.model.storage.StorageProperties;// file upload
import vs.admin.features.candidate.model.storage.StorageService;// file upload

@EnableSwagger2 // swagger
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class) // file upload
public class Application {

	/* ===== File upload bean===== */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
	/* ===== File upload bean===== */
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
	}

	/* =====swagger=== */
	@Bean
	public Docket swaggerDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("vs")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Rinkimu Sistema").version("0.0.1").build();
	}
	/* =====swagger=== */

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
