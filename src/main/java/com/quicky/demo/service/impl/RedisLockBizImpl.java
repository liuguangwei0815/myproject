package com.quicky.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quicky.demo.mapper.UserMapper;
import com.quicky.demo.model.User;
import com.quicky.demo.redis.RedisLockHelper;
import com.quicky.demo.service.RedisLockBiz;

@Service
public class RedisLockBizImpl implements RedisLockBiz {
	/**
	 * 超时时间 5s
	 */
	private static final int TIMEOUT = 5 * 1000;
	@Autowired
	RedisLockHelper redisLockHelper;
	
	//private static int surplusCount = 3;
	
    @Autowired
    private UserMapper userMapper;

	@Override
	public String seckilling(String targetId) {
		System.out.println("进入执行线程:"+targetId);
		 //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if(!redisLockHelper.lock("SECKILING_11",String.valueOf(time))){
        	System.out.println("排队人数太多，请稍后再试.用户:"+targetId);
            return "排队人数太多，请稍后再试.";
        }
        User user = userMapper.selectByName("Jaycekon");
        int surplusCount = Integer.parseInt(user.getPhone());
        System.out.println("奖品数量："+surplusCount);
        // 查询该商品库存，为0则活动结束 e.g. getStockByTargetId
        if(surplusCount==0){
        	System.out.println("活动结束.。用户："+targetId);
            return "活动结束.";
        }else {
            // 下单 e.g. buyStockByTargetId

            //减库存 不做处理的话，高并发下会出现超卖的情况，下单数，大于减库存的情况。虽然这里减了，但由于并发，减的库存还没存到map中去。新的并发拿到的是原来的库存
            surplusCount =surplusCount-1;
            // 减库存操作数据库 e.g. updateStockByTargetId
            User userup = new User();
            userup.setId(2);
            userup.setPhone(String.valueOf(surplusCount));
            userMapper.upById(userup);
            // buyStockByTargetId 和 updateStockByTargetId 可以同步完成(或者事物)，保证原子性。
        }

        //解锁
        redisLockHelper.unlock(targetId,String.valueOf(time));
    	System.out.println("恭喜您，秒杀成功。用户："+targetId);
        return "恭喜您，秒杀成功。用户："+targetId;
	}

}
