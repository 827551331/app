package com.ws.app.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ws.app.dao.LoginMapper;
import com.ws.app.service.SynchroMeterReadService;
import com.ws.app.utils.HttpClientUtil;
import com.ws.app.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SynchroMeterReadServiceImpl implements SynchroMeterReadService {

    private static final String SUCCESS_CODE = "1";

    @Autowired
    private LoginMapper loginMapper;

    @Value("${ycsys-url}")
    private String ycsys_url;

    /**
     * 同步远传系统读数到营收系统
     */
    @Override
    public void synchroMeterRead() {

        JSONObject param = new JSONObject();
        JSONObject packet = new JSONObject();
        packet.put("customer", "CESHI");
        packet.put("systemCode", "cx.100.001");
        packet.put("queryDate1", "");
        packet.put("queryDate2", "");
        packet.put("queryType", "0");
        packet.put("meterType", "wlwsb");
        packet.put("list", Collections.emptyList());

        param.put("packet", packet);
        param.put("checkSum", Tools.encoder(packet.toJSONString()));

        ResponseEntity<Object> responseEntity = HttpClientUtil.doPostRequest(ycsys_url, param.toJSONString());
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JSONObject result = JSONObject.parseObject(responseEntity.getBody().toString());
            JSONObject result_packet = result.getJSONObject("packet");
            if (result_packet.getString("resultCode").equals(SUCCESS_CODE)) {
                JSONArray list = result_packet.getJSONArray("list");
                int length = list.size();
                for (int i = 0; i < length; i++) {
                    JSONObject item = list.getJSONObject(i);
                    Map<String, Object> map = new HashMap<>(1 << 2);
                    map.put("consNo", item.getString("userNo"));
                    map.put("endCode", (int) Math.ceil(Double.valueOf(item.getDoubleValue("meterReading"))));
                    map.put("userNo", "110191");
                    map.put("result", "");
                    loginMapper.uploadYCCode(map);
                }
            }
        }

    }
}
