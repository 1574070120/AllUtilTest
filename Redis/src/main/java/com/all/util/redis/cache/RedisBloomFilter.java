package com.all.util.redis.cache;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Author:liliguo
 * @Description:
 * @Date:Created in 1:50 下午 2020/5/15
 */
public class RedisBloomFilter {
    private static int size = 1000000;
    /**
     * size：预计要插入多少数据
     * fpp：容错率
     * bloomFilter 位数组
     * list 创建的是一个Object数组
     */
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,0.1);
    public static void main(String[] args) {

    }
}
