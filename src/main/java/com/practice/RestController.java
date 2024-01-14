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


    @ResponseBody
    @GetMapping("/index")
    public ArrayList<Map> select(@RequestParam(required = false, name = "language") String language){
//        if(language.equals("all") || language.equals("한국어")){
//            language = "";
//        }
       return restService.select(language);
    }
//
//    @ResponseBody
//    @PostMapping("/insert")
//    public void insert(@RequestBody List<RestDTO> restDTOS){
//       restService.insert(restDTOS);
//    }
}
