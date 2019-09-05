package com.ws.app.controller;


import com.alibaba.fastjson.JSONObject;
import com.ws.app.entity.ContractNO;
import com.ws.app.service.LoginService;
import com.ws.app.service.impl.APPServiceImpl;
import com.ws.app.utils.EWSpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class AppController {

    @Autowired
    private LoginService loginServiceImpl;

    @RequestMapping(value = "/login")
    public List<Map<String, Object>> hello(@RequestParam String userNo) {
        return loginServiceImpl.login(userNo);
    }

    @PostMapping(value = "/queryFee")
    public JSONObject queryFee(@RequestBody Map<String, Object> map) {
        return loginServiceImpl.queryFee(map);
    }

    @PostMapping(value = "/writeOff")
    public JSONObject writeOff(@RequestBody Map<String, Object> map) {
        return loginServiceImpl.writeOff(map);
    }

    @PostMapping(value = "/info")
    public JSONObject info(@RequestBody JSONObject param) {

        APPServiceImpl asi = (APPServiceImpl) EWSpringContextUtil.getBean("aPPServiceImpl");

        switch (param.getString("servCode")) {
            case ContractNO.LOGIN_CODE:
                if (asi.login(param.getString("userNo")) == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", asi.login(param.getString("userNo")));
                }
                break;
            case ContractNO.QUERY_CODE:
                List<Map<String, Object>> result = asi.query(param.getString("consNo"));
                if (result == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", result);
                }
                break;
            case ContractNO.DOWNLOAD_CODE:
                System.out.println("------------下载表册-----------");
                List<Map<String, Object>> download_result = asi.downLoad(param.getString("userNo"));
                System.out.println("表册数量：" + download_result.size());
                if (download_result == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", download_result);
                }
                break;
            case ContractNO.UPLOAD_CODE:
                Map<String, Object> param_map = new HashMap<>();
                param_map.put("userNo", param.getString("userNo"));
                param_map.put("consNo", param.getString("consNo"));
                param_map.put("endCode", param.getString("endCode"));
                System.out.println("------------上传指数参数-----------");
                System.out.println(param_map.toString());
                JSONObject upload_result = asi.upLoad(param_map);
                if (upload_result == null) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", upload_result);
                }
                break;
            case ContractNO.UPDATE_PHONE:
                Map<String, Object> param_phone = new HashMap<>();
                param_phone.put("consNo", param.getString("consNo"));
                param_phone.put("phone", param.getString("phone"));
                System.out.println("------------修改电话号码-----------");
                System.out.println(param_phone.toString());
                int updatePhone_result = asi.updatePhone(param_phone);
                JSONObject json = new JSONObject();
                json.put("updateResult", updatePhone_result);
                if (updatePhone_result == 0) {
                    param.put("rtnCode", "1002");
                    param.put("rtnData", "更新失败");
                } else {
                    param.put("rtnCode", "9999");
                    param.put("rtnData", json);
                }
                break;
            default:
                return param;
        }
        return param;
    }
}

