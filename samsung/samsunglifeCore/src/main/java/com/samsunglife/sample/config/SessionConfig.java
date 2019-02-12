package com.samsunglife.sample.config;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.data.redis.connection.RedisClusterConfiguration;

import com.samsunglife.sample.basis.YamlPropertySourceFactory;

import lombok.extern.slf4j.Slf4j;

//@PropertySource(factory = YamlPropertySourceFactory.class, value = { "classpath:config/application.yml", "classpath:config/common.yml" })
@Slf4j
@Configuration
@EnableRedisHttpSession
@PropertySource(factory = YamlPropertySourceFactory.class, value = { "classpath:config/application.yml", "classpath:config/common.yml" })
public class SessionConfig extends AbstractHttpSessionApplicationInitializer {

	@Value("${spring.redis.password}")
    private  String password;
	@Value("${spring.redis.host}")
    private  String redisHost;
	@Value("${spring.redis.port}")
    private  int redisPort;
    @Value("${spring.session.timeout}")
    private Integer maxInactiveIntervalInSeconds;

    
    /**
     * Type safe representation of application.properties
     */
    @Autowired RedisClusterConfigurationProperties clusterProperties;
	//@Value("${spring.redis.namespace}")
    //private  String namespace;
	@Autowired
	private Environment env;
	//List<String> clusterNodes = Arrays.asList(namespace, "node2-redis-dev.com:6379");
	Logger logger = LoggerFactory.getLogger(SessionConfig.class);
    @Bean
    public JedisConnectionFactory connectionFactory() {
    	logger.info("JedisConnectionFactory");
    	//RedisClusterConfiguration clusterNodes = new RedisClusterConfiguration(clusterNodes);
    	//RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(clusterProperties.getNodes());
    	//JedisConnectionFactory connectionFactory =new JedisConnectionFactory(clusterConfiguration);
    	JedisConnectionFactory connectionFactory =new JedisConnectionFactory();
    	logger.info("redisHost {}",redisHost);
    	logger.info("redisPort {}",redisPort);
    	logger.info("password {}",password);
    	logger.info("redisHost {}",redisHost);
    	
    	connectionFactory.setHostName(redisHost);
    	connectionFactory.setPort(redisPort);
    	connectionFactory.setPassword(password);
    	connectionFactory.setUsePool(true);
    	
    	logger.info("JedisConnectionFactory {}", connectionFactory);
    	//logger.info("clusterConfiguration {}", clusterConfiguration);
        return connectionFactory;
    }
    @Autowired
    @Bean
    RedisCacheManager redisCacheManager(final StringRedisTemplate stringRedisTemplate) {

    	logger.info("redisCacheManager stringRedisTemplate {}", stringRedisTemplate);
    	RedisCacheManager cacheManager = new RedisCacheManager(stringRedisTemplate);
    	logger.info("cacheManager {}", cacheManager);
        return cacheManager;
    }

    @Autowired
    @Bean
    StringRedisTemplate template(final RedisConnectionFactory connectionFactory) {
    	logger.info("template connectionFactory {}", connectionFactory);
    	StringRedisTemplate redisTemplate = new StringRedisTemplate(connectionFactory);
    	logger.info("redisTemplate {}", redisTemplate);
        return redisTemplate;
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    
    //some other codes here
    @Bean
    public RedisOperationsSessionRepository sessionRepository( RedisConnectionFactory factory) {
        RedisOperationsSessionRepository sessionRepository = new RedisOperationsSessionRepository(factory);
        //System.out.println("maxInactiveIntervalInSeconds=="+maxInactiveIntervalInSeconds);
        sessionRepository.setDefaultMaxInactiveInterval(maxInactiveIntervalInSeconds);
        return sessionRepository;
    }
    
    
}
