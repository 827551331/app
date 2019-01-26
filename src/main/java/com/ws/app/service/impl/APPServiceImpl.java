package com.ws.app.service.impl;

import com.ws.app.service.APPService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("aPPServiceImpl")
public class APPServiceImpl implements APPService {

    @Override
    public List<Map<String, Object>> login(String userNo) {
        return null;
    }
}
