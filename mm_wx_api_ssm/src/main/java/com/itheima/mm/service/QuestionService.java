package com.itheima.mm.service;

import java.io.IOException;
import java.util.Map;

/**
 * @author Wangqibo
 * @version 1.0
 * @date 2020-07-04 09:24
 */
public interface QuestionService {
    Map<String, Object> getQuestionList(Map requestMap) throws IOException;

    void questionCommit(Map requestMap) throws IOException;
}
