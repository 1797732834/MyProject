package com.example.tianqitong.dao;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/6 0006.
 */

public interface Iservice {
    void showNotification(HashMap<String, String> map);
    void cancelNotification();
}
