package com.all.util.redis.cache;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:liliguo
 * @Description:使用布隆过滤器会可以占用少的空间，但是可能造成误判
 * @Date:Created in 1:50 下午 2020/5/15
 */
public class RedisBloomFilter {
    private static int size = 1000000;
    /**
     * 布隆过滤器可以理解为一个集合，底层位数组，不能取出数据
     *
     * size：预计要插入多少数据
     * fpp：容错率
     * bloomFilter 位数组
     * list 创建的是一个Object数组
     */
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size,0.1);
    public static void main(String[] args) {
        for(int i = 1;i<=size;i++){
            bloomFilter.put(i);
        }

        List<Integer> list = new ArrayList<>(10000);
        //故意取出10000个不在过滤器里面的值，看看有多少会被认为在过滤器中的
        for(int i = size+10000;i<size+20000;i++){
            if(bloomFilter.mightContain(i)){//判断是否可能包含i
                list.add(i);
            }
        }
        System.out.println("误判数字的数量:"+list.size());
        //result：误判数字的数量:1033 ，总数10000，误判概率接近fpp的0.1
    }
}
