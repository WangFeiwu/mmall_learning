package com.wfw.mmall.common;
/**
* @author F7689334
* @version 创建时间:2018年8月14日 下午4:20:24
* 类说明   将<k,v>放到本地cache中
*/

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class TokenCache {

	private static final Logger logger = LoggerFactory.getLogger(TokenCache.class);

	public static final String TOKEN_PREFIX = "token_";

	// 声明一个静态内存块
	private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000)// 设置缓存的初始化容量为1000
			.maximumSize(10000)// 当缓存的最大容量超过10000时,会使用最小使用算法(LRU)移除缓存项
			.expireAfterAccess(12, TimeUnit.HOURS)// 缓存的有效期为12小时
			.build(new CacheLoader<String, String>() {
				// 默认的数据加载实现,当调用get数值的时候,如果key没有对应的值,就调用这个方法进行加载.
				@Override
				public String load(String s) throws Exception {
					return "null";
				}
			});

	public static void setKey(String key, String value) {
		localCache.put(key, value);
	}

	public static String getKey(String key) {
		String value = null;
		try {
			value = localCache.get(key);
			if ("null".equals(value)) {
				return null;
			}
		} catch (ExecutionException e) {
			logger.error("localCache get error:", e);
		}
		return value;
	}
}
