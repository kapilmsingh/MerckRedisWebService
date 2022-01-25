package com.merck.demo.repo;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.merck.demo.model.Item;

@Repository
public class ItemRepository {
	public static final String KEY = "ITEM";
	private RedisTemplate<String, Item> redisTemplate;
	private HashOperations hasOperations;
	
	public ItemRepository(RedisTemplate<String, Item> redisTemplate){
		this.redisTemplate = redisTemplate;		
	}
	
	//Add Item Object into Redis database
	
	public void addItem(Item item){
		redisTemplate.opsForHash().put(KEY, item.getId(), item);
	}
	
	//Add method to get Item object from Redis database
	public Map<Integer, Item> getAllItems() {
		return hasOperations.entries(KEY);
	}
}
