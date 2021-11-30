package com.comcast.orion.shipmentdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.comcast.orion.shipmentdata.config.MDCHystrixConcurrencyStrategy;
import com.comcast.xsp.security.interceptors.PlatformAuthenticationClientInterceptor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.strategy.HystrixPlugins;

import brave.sampler.Sampler;


@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@RefreshScope
// @EnableJpaRepositories(basePackages =
// "com.comcast.orion.shipmentdata.repository")
// @EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
public class ShipmentdataApplication {

	@Autowired
	private PlatformAuthenticationClientInterceptor xspInterceptor;

	public static void main(String[] args) {
		HystrixPlugins.getInstance().registerConcurrencyStrategy(new MDCHystrixConcurrencyStrategy());
		SpringApplication.run(ShipmentdataApplication.class, args);
	}

	/**
	 */

	/**
	 * Default sampler.
	 *
	 * @return the always sampler
	 */

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public FilterRegistrationBean loggingOutFilter() {
		com.comcast.orion.logging.interceptor.RestLoggingOutFilter filter = new com.comcast.orion.logging.interceptor.RestLoggingOutFilter();
		filter.setSleuthEnabled(Boolean.valueOf(true));
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(filter);
		frb.setUrlPatterns(Arrays.asList("/shipmentdata/*"));
		return frb;
	}

	/**
	 * New intercepting template.
	 *
	 * @param restTemplateBuilder
	 *            the rest template builder
	 * @return the rest template
	 */
	@Bean(name = "downstreamRestTemplate")
	public RestTemplate newInterceptingTemplate(RestTemplateBuilder restTemplateBuilder) {
		// Buffer the stream
		RestTemplate restTemplate = restTemplateBuilder.build();

		// Don't use SimpleClientHttpConnectionFactory as it will throw
		// IOException on ClientHttpResponse.getBody.

		// PlatformAuthenticationClientInterceptor xspInterceptor = new
		// PlatformAuthenticationClientInterceptor();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new RestTemplateLoggingInterceptor());
		interceptors.add(new com.comcast.orion.logging.interceptor.RestTemplateLoggingInterceptor());
		interceptors.add(xspInterceptor);
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	/**
	 * Object mapper.
	 *
	 * @return the object mapper
	 */
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
		// return new ObjectMapper();
	}

	// @Autowired
	// private DataSource dataSource;

	/*
	 * @Bean public LocalContainerEntityManagerFactoryBean
	 * entityManagerFactory(Environment env) {
	 * LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new
	 * LocalContainerEntityManagerFactoryBean(); //
	 * entityManagerFactoryBean.setDataSource(vmsDataSource);
	 * entityManagerFactoryBean.setDataSource(dataSource);
	 * entityManagerFactoryBean.setJpaVendorAdapter(new
	 * HibernateJpaVendorAdapter());
	 * entityManagerFactoryBean.setPackagesToScan("com.comcast.orion.shipmentdata");
	 * 
	 * Properties jpaProperties = new Properties();
	 * 
	 * // Configures the used database dialect. This allows Hibernate to create //
	 * SQL // that is optimized for the used database.
	 * jpaProperties.put("hibernate.dialect",
	 * env.getRequiredProperty("orion.hibernate.dialect"));
	 * 
	 * // Specifies the action that is invoked to the database when the // Hibernate
	 * // SessionFactory is created or closed. //
	 * jpaProperties.put("hibernate.hbm2ddl.auto", //
	 * env.getRequiredProperty("vms.hibernate.hbm2ddl.auto"));
	 * 
	 * // If the value of this property is true, Hibernate writes all SQL //
	 * statements to the console. jpaProperties.put("hibernate.show_sql",
	 * env.getRequiredProperty("orion.hibernate.show_sql"));
	 * 
	 * // If the value of this property is true, Hibernate will format the SQL //
	 * that is written to the console. jpaProperties.put("hibernate.format_sql",
	 * env.getRequiredProperty("orion.hibernate.format_sql"));
	 * 
	 * jpaProperties.put("hibernate.jdbc.fetch_size",
	 * env.getRequiredProperty("orion.hibernate.jdbc.fetch_size"));
	 * jpaProperties.put("hibernate.jdbc.batch_size",
	 * env.getRequiredProperty("orion.hibernate.jdbc.batch_size"));
	 * jpaProperties.put("hibernate.cache.user_query_cache",
	 * env.getRequiredProperty("orion.hibernate.cache.user_query_cache"));
	 * jpaProperties.put("hibernate.order_inserts",
	 * env.getRequiredProperty("orion.hibernate.order_inserts"));
	 * jpaProperties.put("hibernate.order_updates",
	 * env.getRequiredProperty("orion.hibernate.order_updates"));
	 * jpaProperties.put("hibernate.enable_lazy_load_no_trans", true);
	 * entityManagerFactoryBean.setJpaProperties(jpaProperties);
	 * 
	 * return entityManagerFactoryBean; }
	 */

}
