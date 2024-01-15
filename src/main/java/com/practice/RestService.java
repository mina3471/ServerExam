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
    // 전체가 ArrayList 그 안에 Map들로 구성
    public ArrayList<Map> select(String language){
        //api 연동할때 쓰는 일종의 tool
        //getForObject 메소드를 통해 url에서 데이터를 가져옴
        //가져온 데이터는 Map 객체로 변환되어 data 변수에 저장
        RestTemplate restTemplate = new RestTemplate();
        Map data = restTemplate.getForObject(url, Map.class);
        ArrayList<Map> result = (ArrayList<Map>) data.get("data");

        //빈 배열 생성
        ArrayList<Map> dat = new ArrayList<>();

//        if(!language.isEmpty()){
        // result 배열에서 Map 하나씩 반복
        // PSB_FRN - 가능언어, GNG_CS - 주소, BZ_NM - 음식점명
            for (Map a: result) {

                //입력된 language가 "PSB_FRN" 키의 값에 포함되어 있다면,
                //해당 요소의 "BZ_NM"과 "GNG_CS" 키의 값을 가져와 새로운 RestDTO 객체를 생성
                if(String.valueOf(a.get("PSB_FRN")).contains(language)){
                    Map<String,String> map = new HashMap();
                    map.put("store_name", (String) a.get("BZ_NM"));
                    map.put("address", (String) a.get("GNG_CS"));
                    map.put("language", language);

                    RestDTO restDTO = new RestDTO();
                    restDTO.setStoreName((String) a.get("BZ_NM"));
                    restDTO.setAddress((String) a.get("GNG_CS"));
                    restDTO.setLanguage(language);


                    //그 객체를 이용해 RestMapper의 insert_store 메소드를 호출하거나,
                    //이미 데이터베이스에 같은 이름의 매장이 있다면 update_count 메소드를 호출
                    //이 때, 새로운 Map 객체를 생성하여 "store_name", "address", "language" 키에
                    // 각각 매장 이름, 주소, 언어를 저장하고, 이 Map 객체를 dat ArrayList에 추가
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
