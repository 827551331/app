package com.ws.app.dao;


import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface LoginMapper {

    @Select({"SELECT 操作员编码 AS userid,姓名 AS username FROM T操作人员 where 姓名='吴玲'"})
    public List<Map<String,Object>> login();
}
