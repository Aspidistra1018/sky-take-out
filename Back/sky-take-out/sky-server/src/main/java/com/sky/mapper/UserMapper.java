package com.sky.mapper;

import com.sky.entity.User;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * 根据openid查询用户
     * @param openid
     */
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    /**
     * 插入数据
     * @param user
     */
    void insert(User user);

    /**
     *
     * @param userId
     * @return
     */
    @Select("select * from user where id= #{userId}")
    User getById(Long userId);

    /**
     * 根据日期查找总用户
     * @param map
     * @return
     */
    Integer totalUserSumByMap(Map map);

    /**
     * 根据日期查找新用户
     * @param map
     * @return
     */
    Integer newUserSumByMap(Map map);
}
