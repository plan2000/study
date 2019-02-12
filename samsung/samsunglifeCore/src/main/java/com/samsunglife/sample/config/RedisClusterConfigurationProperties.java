package com.samsunglife.sample.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfigurationProperties {
	
	
	Logger logger = LoggerFactory.getLogger(RedisClusterConfigurationProperties.class);
	   /*
     * spring.redis.cluster.nodes[0] = 127.0.0.1:7379
     * spring.redis.cluster.nodes[1] = 127.0.0.1:7380
     * ...
     */
    List<String> nodes;

    /**
     * Get initial collection of known cluster nodes in format {@code host:port}.
     *
     * @return
     */
    public List<String> getNodes() {
    	
        return nodes;
    }

    public void setNodes(List<String> nodes) {
    	for (int i = 0; i < nodes.size(); i++) {
    		logger.info(nodes.get(i));	
		}
    	
    	this.nodes = nodes;
    }
}
