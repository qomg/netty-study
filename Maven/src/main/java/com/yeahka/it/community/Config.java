//package com.yeahka.it.community;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.hateoas.IanaLinkRelations;
//import org.springframework.hateoas.LinkRelation;
//import org.springframework.hateoas.UriTemplate;
//import org.springframework.hateoas.config.EnableHypermediaSupport;
//import org.springframework.hateoas.mediatype.hal.CurieProvider;
//import org.springframework.hateoas.mediatype.hal.DefaultCurieProvider;
//import org.springframework.hateoas.mediatype.hal.HalConfiguration;
//import org.springframework.hateoas.mediatype.hal.HalConfiguration.RenderSingleLinks;
//import org.springframework.hateoas.mediatype.hal.forms.HalFormsConfiguration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//@Configuration
//@EnableWebMvc
//@EnableHypermediaSupport(type= {EnableHypermediaSupport.HypermediaType.HAL})
//public class Config {
//
//	@Bean
//	HalFormsConfiguration halFormsConfiguration() {
//		HalFormsConfiguration configuration = new HalFormsConfiguration();
//		// TODO configuration.withPattern(CreditCardNumber.class, "[0-9]{16}");
//		return configuration;
//	}
//
//	@Bean
//	public CurieProvider curieProvider() {
//		return new DefaultCurieProvider("ex", UriTemplate.of("https://www.example.com/rels/{rel}"));
//	}
//
//	@Bean @Primary
//	public HalConfiguration globalPolicy() {
//		return new HalConfiguration() //
//				.withRenderSingleLinks(RenderSingleLinks.AS_ARRAY);
//	}
//
//	@Bean
//	public HalConfiguration linkRelationBasedPolicy() {
//		return new HalConfiguration() //
//				.withRenderSingleLinksFor( //
//						IanaLinkRelations.ITEM, RenderSingleLinks.AS_ARRAY)
//				.withRenderSingleLinksFor( //
//						LinkRelation.of("prev"), RenderSingleLinks.AS_SINGLE);
//	}
//
//	@Bean
//	public HalConfiguration patternBasedPolicy() {
//		return new HalConfiguration() //
//				.withRenderSingleLinksFor( //
//						"http*", RenderSingleLinks.AS_ARRAY);
//	}
//}
