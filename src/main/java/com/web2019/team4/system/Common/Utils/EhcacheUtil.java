package com.web2019.team4.system.Common.Utils;


import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EhcacheUtil {
    @Resource
    private CacheManager cacheManager;

    public boolean get(String userName) {
        Cache userCache = cacheManager.getCache("userCache");
        String user = userCache.get(userName, String.class);
        if (user == null) {
            return false;
        }
        return true;
    }

    public boolean put(String userName) {
        Cache countCache = cacheManager.getCache("countCache");
        Integer count = countCache.get(userName, Integer.class);
        if (count != null) {
            if (count == 3) {
                Cache userCache = cacheManager.getCache("userCache");
                userCache.put(userName, "lock");
                countCache.evict(userName);//重计数缓存移除当前用户计数
                return true;
            } else {
                countCache.put(userName, ++count);
            }
        } else {
            countCache.put(userName, 1);
        }
        return false;
    }
}
