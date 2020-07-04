package com.itheima.mm.service;

import com.itheima.mm.pojo.Industry;

import java.io.IOException;
import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-02 20:39
 */
public interface IndustryService {
    List<Industry> findAll() throws IOException;
}
