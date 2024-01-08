package com.practice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticeApplicationTests {
    @Autowired
    public RestService restService;

    @Test
    void contextLoads() {
        System.out.println(restService.select(1, 2));
    }

}
