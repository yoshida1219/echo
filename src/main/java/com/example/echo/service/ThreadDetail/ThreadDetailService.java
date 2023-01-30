package com.example.echo.service.ThreadDetail;

import com.example.echo.entity.select.ThreadDetail;
import com.example.echo.entity.select.ThreadList;

public interface ThreadDetailService {
    
    Iterable<ThreadDetail> selectThreadDetail_Popular3(String thread_id);

    Iterable<ThreadDetail> selectThreadDetailAll(String thread_id);

    Iterable<ThreadList> selectThreadOneByWord(String search_word);

}