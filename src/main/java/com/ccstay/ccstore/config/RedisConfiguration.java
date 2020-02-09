package com.ccstay.ccstore.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;


@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfiguration {
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory fac = new JedisConnectionFactory();
//        fac.setHostName(environment.getProperty("redis.hostName"));
//        fac.setPort(Integer.parseInt(environment.getProperty("redis.port")));
//        fac.setPassword(environment.getProperty("redis.password"));
//        fac.setTimeout(Integer.parseInt(environment.getProperty("redis.timeout")));
//        fac.getPoolConfig().setMaxIdle(Integer.parseInt(environment.getProperty("redis.maxIdle")));
//        fac.getPoolConfig().setMaxTotal(Integer.parseInt(environment.getProperty("redis.maxTotal")));
//        fac.getPoolConfig().setMaxWaitMillis(Integer.parseInt(environment.getProperty("redis.maxWaitMillis")));
//        fac.getPoolConfig().setMinEvictableIdleTimeMillis(
//                Integer.parseInt(environment.getProperty("redis.minEvictableIdleTimeMillis")));
//        fac.getPoolConfig()
//                .setNumTestsPerEvictionRun(Integer.parseInt(environment.getProperty("redis.numTestsPerEvictionRun")));
//        fac.getPoolConfig().setTimeBetweenEvictionRunsMillis(
//                Integer.parseInt(environment.getProperty("redis.timeBetweenEvictionRunsMillis")));
//        fac.getPoolConfig().setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("redis.testOnBorrow")));
//        fac.getPoolConfig().setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("redis.testWhileIdle")));
//        return fac;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, String> redis = new RedisTemplate<>();
//        redis.setConnectionFactory(redisConnectionFactory);
//        redis.afterPropertiesSet();
//        return redis;
//    }



    //    @Bean
//    public JedisSentinelPool jedisSentinelPool(){
//        Set<String> sentinels=new HashSet<>();
//        sentinels.add(nodes);
//        return new JedisSentinelPool("master",sentinels);
//    }


    //@Bean
    public JedisCluster jedisCluster() {
        Set<HostAndPort> setNodes = new HashSet<>();
        String[] arrayNode = nodes.split(",");
        for (String node : arrayNode) {
            String host = node.split(":")[0];
            System.err.println(host);
            int port = Integer.parseInt(node.split(":")[1]);
            System.err.println(port);
            setNodes.add(new HostAndPort(host, port));
        }
        return new JedisCluster(setNodes);
    }

    @Value("${redis.nodes}")
    private String nodes;

    @Value("${redis.hostname}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.maxTotal}")
    private Integer maxTotal;
    @Value("${redis.maxIdle}")
    private Integer maxIdle;
    @Value("${redis.maxWaitMillis}")
    private Long maxWaitMillis;
    //@Value("${redis.password}")
    //private String password;

    //@Bean
    //@Scope("prototype")//多例对象
   // public Jedis jedis(JedisCluster jedisCluster){
  //      return jedisCluster.getConnectionFromSlot();
   // }


}
