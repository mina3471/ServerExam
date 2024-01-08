//package com.practice;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class MainController {
//    public String data1 = "김민아";
//    public String data2 = "바보";
//    public String data3 = "ㅋㅋㅋㅋ";
//
//
//
//
//    @GetMapping("/message")
//    public String get_message(){
//        // View 반환. 메인화면으로 가기 위한 것.
//        return "김민아";
//    }
//
//    // 클라이언트가 보낸 데이터 받는 방법 1
//    @ResponseBody
//    @GetMapping("/input/{number}")
//    public String get_input1(@PathVariable int number){
//        if(number == 1){
//            return data1;
//        }
//        else if(number == 2){
//            return data2;
//        }
//        return data3;
//    }
//
//    // 클라이언트가 보낸 데이터 받는 방법 2
//
//    @ResponseBody
//    @GetMapping("/input")
//    public String get_input2(@RequestParam int number){
//        if(number == 1){
//            return data1;
//        }
//        else if(number == 2){
//            return data2;
//        }
//        return data3;
//    }
//
//}
