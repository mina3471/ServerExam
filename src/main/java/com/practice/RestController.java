package com.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class RestController {
    @Autowired
    private RestService restService;


    //required = false는 파라미터가 필수아님 URL에 language 파라미터가 없더라도 select 메소드는 실행
    @ResponseBody
    @GetMapping("/index")
    public ArrayList<Map> select(@RequestParam(required = false, name = "language") String language) {
        return restService.select(language);
    }
}
