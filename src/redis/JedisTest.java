package redis;

import java.util.Map;
import java.util.UUID;

import org.junit.Test;

public class JedisTest {

    @Test
    public void test1(){
        JedisClientPool jedis = new JedisClientPool();
        String vl = jedis.hget("200727090001", "threshold_1");
        System.out.println(vl);
//        jedis.hset("98D863412A40", "threshold_3", vl);
//        long v = jedis.hdel("98D863412A40", "threshold_3");
//        System.out.println(v);
//        String lockKey = "countDayElectricity11";
//        String lockValue = UUID.randomUUID().toString();
//        boolean lockResult = jedis.setDistributedLock(lockKey, lockValue, "NX", "EX", 1800);
//        System.out.println(lockResult);
//        System.out.println(jedis.releaseDistributedLock(lockKey, "b4d75b70-4e82-47bd-963d-872aeadf49dc"));
    }
}
