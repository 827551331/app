package com.ws.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ws.app.dao.LoginMapper;
import com.ws.app.service.APPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service(value = "aPPServiceImpl")
public class APPServiceImpl implements APPService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Map<String, Object> login(String userNo) {
        Map<String, Object> result = loginMapper.login(userNo);
        System.out.println("登录接口返回信息：" + result);
        return result;
    }

    @Override
    public List<Map<String, Object>> query(String consNo) {
        return loginMapper.getWaterInfo(consNo,getDate(12),getDate(1));
    }

    @Override
    public List<Map<String, Object>> downLoad(String userNo) {
        return loginMapper.getBCInfo(userNo);
    }

    @Override
    public JSONObject upLoad(Map<String, Object> map) {
        loginMapper.uploadCode(map);
        System.out.println(map.get("result"));
        return JSONObject.parseObject((String) map.get("result"));
    }

    private String getDate(int num) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -num);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String dateStr = sdf.format(c.getTime());
        System.out.println(dateStr);
        return dateStr;
    }

}
