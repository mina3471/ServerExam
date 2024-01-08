package com.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Controller
public class RestController {
    @Autowired
    private RestService restService;


    @ResponseBody
    @GetMapping("/index/{pageNum}/{pagePerNum}")
    public String select(@PathVariable int pageNum, @PathVariable int pagePerNum){
       return restService.select(pageNum, pagePerNum);
    }

    @ResponseBody
    @PostMapping("/insert")
    public void insert(@RequestBody List<RestDTO> restDTOS){
       restService.insert(restDTOS);
    }
}
