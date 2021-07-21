/**
 * 
 */
package com.mmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * @author pkumar
 *
 */
@Configuration
public class BeanConfig {

	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tiles = new TilesConfigurer();
		tiles.setDefinitions(new String[] { "WEB-INF/tiles.xml" });
		return tiles;

	}
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
	
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
	 MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
	 ObjectMapper objectMapper = new ObjectMapper();
	 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	 jsonConverter.setObjectMapper(objectMapper);
	 return jsonConverter;
	}
	 @Bean
	    public WebMvcConfigurerAdapter forwardToIndex() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addViewControllers(ViewControllerRegistry registry) {
	                // forward requests to /admin and /user to their index.html
	                registry.addViewController("/").setViewName(
	                        "redirect:/mmt/home.html");
	               
	            }
	        };
	    }
	 
	 

}
