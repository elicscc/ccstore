//package com.ccstay.ccstore;
//
//import com.ccstay.ccstore.entity.User;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.junit.jupiter.api.TestTemplate;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.Transaction;
//
//import javax.sound.midi.Soundbank;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 测试redis全部操作
// */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class TestRedis {
//
//    private Jedis jedis;
//
//    @BeforeAll
//    public void init() {
//        String host = "106.12.23.3";
//        int port = 6379;
//        jedis = new Jedis(host, port);
//    }
//
//    @Test
//    public void testHash() {
//        jedis.hset("user", "id", "1001");
//        jedis.hset("user", "name", "txc奥");
//        System.out.println(jedis.hgetAll("user"));
//    }
//
//    @Test
//    public void testList() {
//        jedis.lpush("list", "1", "2", "3");
//        jedis.rpush("list", "4", "5", "6");
//
//        System.out.println(jedis.rpop("list"));
//    }
//
//    /**
//     * redis事务控制 AOP操作
//     */
//    @Test
//    public void testTx() {
//        Transaction transaction = jedis.multi();
//        try {
//            transaction.set("cc", "cc");
//            //事务提交
//            transaction.exec();
//        } catch (Exception e) {
//            //事务回滚
//            transaction.discard();
//        }
//    }
//
//    private static final ObjectMapper mapper = new ObjectMapper();
//
//    /**
//     * 检查异常转化为运行异常
//     */
//    @Test
//    public String toJson() {
//        User user = new User();
//        user.setUid(1);
//        user.setUsername("CC");
//        user.setPassword("11");
//        String json = null;
//        try {
//            json = mapper.writeValueAsString(user);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        System.out.println(json);
//        return json;
//    }
//
//    @Test
//    public void TOlist() throws JsonProcessingException {
//        List<User> userList = new ArrayList<>();
//
//        User user = new User();
//        user.setUid(1);
//        user.setUsername("CC");
//        user.setPassword("11");
//        User user2 = new User();
//        user2.setUid(1);
//        user2.setUsername("CC");
//        user2.setPassword("11");
//        userList.add(user);
//        userList.add(user2);
//
//        String json = mapper.writeValueAsString(userList);
//        System.out.println(json);
//        //List<User> u = mapper.readValue(json, userList.getClass());
//        toObject(json, User.class);
//
//        //System.out.println(u);
//    }
//
//    @Test
//    public static <T> T toObject(String json, Class<T> c) {
//        T o = null;
//        try {
//            o = mapper.readValue(json, c);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        return o;
//
//    }
//}
