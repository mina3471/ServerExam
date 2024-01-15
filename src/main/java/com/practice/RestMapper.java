package com.practice;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RestMapper {

    //RestDTO 객체를 파라미터로 받아,
    // 해당 객체의 필드 값을 이용하여 data 테이블에 새로운 행을 추가하는 SQL 쿼리를 실행합니다.
    // 이 때, 쿼리는 storeName, address, language 필드 값을 사용하고, count 값은 1로 설정
    @Insert("INSERT INTO `data` VALUES (#{storeName}, #{address}, #{language},1)")
    void insert_store(RestDTO restDTO);

    //매장 이름을 파라미터로 받아, data 테이블에서 해당 매장 이름을 가진 행을 찾고,
    // 그 매장의 이름을 반환하는 SQL 쿼리를 실행합니다.
    // 만약 동일한 매장 이름을 가진 행이 여러 개 있다면, 첫 번째 행의 매장 이름만 반환
    @Select("select store_name from `data` where store_name = #{storeName} limit 1")
    String select_store(String storeName);

    //매장 이름과 언어를 파라미터로 받아,
    // data 테이블에서 해당 매장 이름과 언어를 가진 행의 count 값에 1을 더하는 SQL 쿼리를 실행
    @Update("UPDATE `data` SET `count` = `count` + 1 WHERE `store_name` = #{storeName} and `language` = #{language}")
    void update_count(String storeName,String language);
}
