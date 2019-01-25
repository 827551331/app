package com.ws.app.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 通讯报文
 *
 * @author wang_yw
 * @date 2019/01/25
 */
@Data
public class RequestPacket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 服务编号
     */
    private String servCode;

    /**
     * 抄表员编号
     */
    private String userNo;

    /**
     * 用水户户号
     */
    private String consNo;

    /**
     * 本期止码
     */
    private String endCode;

    /**
     * 返回数据
     */
    private List<Map<String, Object>> rtnData;
}
