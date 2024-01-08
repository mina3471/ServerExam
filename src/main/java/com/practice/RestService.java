package com.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestService {
    @Autowired
    RestMapper restMapper;

    String url = "https://api.odcloud.kr/api/15109950/v1/uddi:1f78fe49-78b4-4784-a5f0-e2c8a60515b4?";
    public String select(int pageNum, int pagePerNum){
        url += "page=" + pageNum;
        url += "&perPage=" + pagePerNum;
        url += "&serviceKey=I6ltxDe96dICx4lWZWgDSuzT71APcQrWFWCeW3/wwQFWXAtr9DTmKGXJ9V7VTJ5RcuOqLGbnOAytmhrGM6x6yQ==";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


    public void insert(List<RestDTO> restDTOS){
        for(RestDTO restDTO : restDTOS) {
            restMapper.insert_store(restDTO);
        }
    }
}
