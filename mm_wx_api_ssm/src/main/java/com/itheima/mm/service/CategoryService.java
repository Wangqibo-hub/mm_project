package com.itheima.mm.service;

import java.util.List;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-04 09:24
 */
public interface CategoryService {
    List<Map<String, Object>> getCategoryList(Map requestMap);
}
