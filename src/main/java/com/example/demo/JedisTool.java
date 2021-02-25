package com.example.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
 
public class JedisTool {
    private Pipeline pl = null;
    private JedisPoolConfig poolConfig = null;
    private Jedis jr = null;
    private JedisPool jedisPool = null;
 
    public void initJedis() {
        this.poolConfig = new JedisPoolConfig();
        this.poolConfig.setMaxTotal(4);
        this.poolConfig.setMaxIdle(1);
        this.poolConfig.setMaxWaitMillis(6000);
        this.jedisPool = new JedisPool(poolConfig,"47.98.132.34", 6380, 60, "Ltd3411??");
        this.jr = this.jedisPool.getResource();
        this.pl = this.jr.pipelined();
        System.out.println("initJedis");
    }
 
    public void usePipeline(int count) {
        if(this.pl == null) {
            System.out.println("Warning: not initialized");
        } else {
            System.out.println("initialized");
        }
        try{
            for(int i=0;i<count;i++) {
                this.pl.set("abcdefgh" + Integer.toString(i), "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz" + Integer.toString(i));
            }
            this.pl.sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
		JedisTool jedisTool = new JedisTool();
        jedisTool.initJedis();
        jedisTool.usePipeline(1);
	}
}
