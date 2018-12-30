package com.ws.app.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoginMapper {

    @Select({"SELECT 操作员编码 AS userid,姓名 AS username FROM T操作人员 where 姓名='吴玲'"})
    public List<Map<String, Object>> login();

    @Select("exec gogpay_user_qf #{consNo}, #{result,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType = StatementType.CALLABLE)
    public void queryFee(Map<String, Object> map);

    @Select("exec P_WNT_GOGPAY #{consNo},#{sjje},#{yjje},#{wyj},#{result,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType = StatementType.CALLABLE)
    public void writeOff(Map<String, Object> map);
}
