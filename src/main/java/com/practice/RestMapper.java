package com.practice;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RestMapper {
    @Insert("INSERT INTO `data` VALUES (#{storeName}, #{address}, #{language},1)")
    void insert_store(RestDTO restDTO);

    @Select("select store_name from `data` where store_name = #{storeName} limit 1")
    String select_store(String storeName);

    @Update("UPDATE `data` SET `count` = `count` + 1 WHERE `store_name` = #{storeName} and `language` = #{language}")
    void update_count(String storeName,String language);
}
