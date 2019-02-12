package com.samsunglife.sample.core.redis.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.samsunglife.sample.config.SessionConfig;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisHandler {

	
	
	Logger logger = LoggerFactory.getLogger(RedisHandler.class);
	public RedisHandler() {
		
	}

	@Autowired
	SessionConfig SessionConfig;	
	

    @Bean    
    public boolean putAll(String key, Object obj) {
    	
    	logger.info("SessionConfig {}",SessionConfig);
    	RedisTemplate redisTemplate = SessionConfig.redisTemplate();
    	logger.info("key {}",obj);
    	ValueOperations<String, Object> vop = redisTemplate.opsForValue();
		vop.set(key, obj);
		
		//obj.get(key)
		
		
		
		
    	return true;
    }
}
