package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import com.sky.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.simpleframework.xml.Order;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 插入一条订单数据
     * @param orders
     */
    void insert(Orders orders);

    /**
     * 历史订单查询
     * @param ordersPageQueryDTO
     * @return
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);


    /**
     * id查询订单
     * @param orders
     * @return
     */
    @Select("select * from orders where user_id = #{userId}")
    List<Orders> selectByUserId(Orders orders);
}
