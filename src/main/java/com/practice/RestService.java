package com.practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RestService {
    @Autowired
    RestMapper restMapper;

    String url = "https://www.daegufood.go.kr/kor/api/tasty.html?mode=json&addr=중구";
    public ArrayList<Map> select(String language){
        //api 연동할때 쓰는 일종의 tool
        RestTemplate restTemplate = new RestTemplate();
        Map data = restTemplate.getForObject(url, Map.class);
        ArrayList<Map> result = (ArrayList<Map>) data.get("data");

        ArrayList<Map> dat = new ArrayList<>();

//        if(!language.isEmpty()){
            for (Map a: result) {

                if(String.valueOf(a.get("PSB_FRN")).contains(language)){
                    Map<String,String> map = new HashMap();
                    map.put("store_name", (String) a.get("BZ_NM"));
                    map.put("address", (String) a.get("GNG_CS"));
                    map.put("language", language);

                    RestDTO restDTO = new RestDTO();
                    restDTO.setStoreName((String) a.get("BZ_NM"));
                    restDTO.setAddress((String) a.get("GNG_CS"));
                    restDTO.setLanguage(language);

                   String dbData= restMapper.select_store((String) a.get("BZ_NM"));
                    if(dbData == null){
                        restMapper.insert_store(restDTO);
                    }else{
                        restMapper.update_count((String) a.get("BZ_NM"), language);
                    }

                    dat.add(map);
                }
            }
//        }else{
//            for (Map a: result) {
//                Map<String,String> map = new HashMap();
//                     map.put("store_name", (String) a.get("BZ_NM"));
//                    map.put("address", (String) a.get("GNG_CS"));
//                    map.put("language", language);
//                    dat.add(map);
//
//                RestDTO restDTO = new RestDTO();
//                restDTO.setStoreName((String) a.get("BZ_NM"));
//                restDTO.setAddress((String) a.get("GNG_CS"));
//                restDTO.setLanguage(language);
//                String dbData= restMapper.select_store((String) a.get("BZ_NM"));
//                if(dbData == null){
//                    restMapper.insert_store(restDTO);
//                }else{
//                    restMapper.update_count((String) a.get("BZ_NM") , language);
//                }
//            }
//        }


        return  dat;
    }


//    public void insert(List<RestDTO> restDTOS){
//        for(RestDTO restDTO : restDTOS) {
//            restMapper.insert_store(restDTO);
//        }
//    }
}
