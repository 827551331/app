package com.ws.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ws.app.service.SynchroMeterReadService;
import com.ws.app.utils.HttpClientUtil;
import com.ws.app.utils.Tools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class SynchroMeterReadServiceImpl implements SynchroMeterReadService {


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


    }

    public static void main(String[] args) {

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

        ResponseEntity<Object> responseEntity = HttpClientUtil.doPostRequest("http://211.159.148.145:6021/WSCHweb/MeterEnterServlet", param.toJSONString());
        System.out.println(responseEntity.getBody().toString());
    }
}
