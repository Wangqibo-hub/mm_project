package com.itheima.mm.service;

import com.itheima.mm.pojo.Company;

import java.util.List;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-02 20:38
 */
public interface CompanyService {
    List<Company> findAll(Integer state) throws Exception;
}
