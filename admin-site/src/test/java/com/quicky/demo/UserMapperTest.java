package com.quicky.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.quicky.demo.mapper.UserMapper;
import com.quicky.demo.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.quicky.demo.mapper")
public class UserMapperTest {
    @Autowired
    private UserMapper mapper;

    @Test
    public void testInset() {
//        User user = new User(1, "Jaycekon","1234","1234","123");
//        int i = mapper.insert(user);
//        Assert.assertNotEquals(0, i);
    }


    @Test
    public void testSelect(){
        User user = mapper.selectByName("Jaycekon");
        System.out.println("____1111_"+user.getId());
    }
}