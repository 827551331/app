package com.ws.app.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface APPService {

    public Map<String, Object> login(String userNo);

    public List<Map<String, Object>> query(String consNo);

    public List<Map<String, Object>> downLoad(String consNo);

    public List<Map<String, Object>> updateDownLoad(String consNo);

    public JSONObject upLoad(Map<String, Object> map);

    public int updatePhone(Map<String, Object> map);

}