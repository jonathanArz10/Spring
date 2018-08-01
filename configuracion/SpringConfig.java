/**
 * 
 */
package com.mpersd.spring.configuracion;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * @author jonat
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.mpersd.spring")
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {
//	mvc
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/vistas/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/content/**").addResourceLocations("/content/");
    }
    
//    Hibernate y datos
//    Conexion
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/cometadb");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        return dataSource;
    }
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", "true");
        return properties;        
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    	LocalContainerEntityManagerFactoryBean emf 
    		= new LocalContainerEntityManagerFactoryBean();
    	emf.setDataSource(dataSource());
    	emf.setPersistenceUnitName("cometaPU");
    	emf.setPackagesToScan("com.mpersd.spring.dominio");
    	HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
    	emf.setJpaVendorAdapter(vendor);
    	emf.setJpaProperties(hibernateProperties());
    	return emf;
    }
    
    @Bean @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    	JpaTransactionManager txm = new JpaTransactionManager();
    	txm.setEntityManagerFactory(emf);
    	return txm;
    }
}
