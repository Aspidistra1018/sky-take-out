package com.sky.service.impl;

import com.sky.dto.SetmealDTO;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.DishMapper;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    SetmealDishMapper setmealDishMapper;

    /**
     * 新增套餐和对应菜品
     *
     * @param setmealDTO
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDTO setmealDTO) {
        //符合插入的逻辑规范，创建表对应类型的实例对象
        Setmeal setmeal = new Setmeal();

        //属性拷贝
        BeanUtils.copyProperties(setmealDTO, setmeal);

        //向套餐表中插入一条数据
        SetmealMapper.insert(setmeal);

        //获取insert语句生成的主键值
        Long setmealId = setmeal.getId();

        //向菜品表中插入多条数据
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        if(setmealDishes != null && setmealDishes.size() > 0){
            setmealDishes.forEach(setmealdish ->{
                setmealdish.setDishId(setmealId);
            });
            //向套餐表和菜品表的中间表添加n条数据
            setmealDishMapper.insertBatch(setmealDishes);
        }

    }
}
