package com.shixun;

import com.shixun.api.entity.Dept;
import com.shixun.api.service.IDeptService;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
public class MybatisPlusTest {
    @Autowired
    private IDeptService deptService;

    @Test
    public void test1(){
        List<Dept> list = deptService.list();
        System.out.println(list);
    }
}
