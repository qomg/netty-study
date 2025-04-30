package com.yeahka.it.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;
import org.springframework.web.filter.ForwardedHeaderFilter;

@SpringBootApplication
public class CommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

//	@Bean
//	void setupOpenApi() {
//		ClassLoader loader = getClass().getClassLoader();
//		try(InputStream input = loader.getResourceAsStream("openapi/documentation.yaml")) {
//			if (input != null) {
//				StringWriter writer = new StringWriter();
//				new BufferedReader(new InputStreamReader(input)).transferTo(writer);
//				String apiDocument = writer.toString();
//				Logger.getGlobal().info("Document -> " + apiDocument);
//			}
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//	}

	/**
	 * Format embedded collections by pluralizing the resource's type.
	 *
	 * @return
	 */
	@Bean
	EvoInflectorLinkRelationProvider relProvider() {
		return new EvoInflectorLinkRelationProvider();
	}

	@Bean
	ForwardedHeaderFilter forwardedHeaderFilter() {
		return new ForwardedHeaderFilter();
	}
}
