package com.itheima.mm.dao;

import com.itheima.mm.pojo.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-06-22 09:46
 */
public interface DictDao {

    /**
    * @Description: 根据城市名获取城市信息
    * @Param: [cityName]
    * @Return: com.itheima.mm.pojo.Dict
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    Dict getCityByName(String cityName);

    /**
    * @Description: 根据是否热门获取城市信息
    * @Param: [fs]
    * @Return: java.util.List<com.itheima.mm.pojo.Dict>
    * @Author: Wangqibo
    * @Date: 2020/6/22/0022
    */
    List<Dict> getCityByFs(@Param("fs") String fs);
}
