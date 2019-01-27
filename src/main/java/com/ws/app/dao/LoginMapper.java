package com.ws.app.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface LoginMapper {

    /**
     * 登录
     *
     * @param userNo
     * @return
     */
    @Select({"SELECT 姓名 AS userName , 操作员编码 AS userNo FROM T操作人员 WHERE 操作员编码 = #{userNo}"})
    public Map<String, Object> login(String userNo);


    /**
     * 获取表册信息
     *
     * @param userNo
     * @return
     */
    @Select({"SELECT  A.客户编码 AS consNo,A.抄表线路 AS bcId,B.线路名称 bcName,A.水表位置 sbAddr,B.CBY userName,C.操作员编码 userNo FROM T客户水表 A INNER JOIN T抄表线路 B " +
            " ON A.抄表线路 = B.线路编码 AND A.部门编码 = B.营业所编码 " +
            " INNER JOIN T操作人员 C ON B.CBY = C.姓名 " +
            " WHERE A.供水情况 = '0' " +
            " AND C.操作员编码 = #{userNo} " +
            " GROUP BY A.客户编码,B.线路名称,A.水表位置,B.CBY,C.操作员编码,A.抄表线路 " +
            " ORDER BY B.线路名称,A.水表位置"})
    public List<Map<String, Object>> getBCInfo(String userNo);


    /**
     * 查询水表近一年用水信息
     *
     * @param consNo
     * @return
     */
    @Select({"SELECT A.客户编码 consNo,A.上月表数 startCode,A.本月表数 endCode,A.实用水量 num,B.年月 dateYM FROM t用水记录 A " +
            " INNER JOIN  T用水年月 B ON A.用水年月 = B.年月编码 " +
            " WHERE A.客户编码 = #{consNo} " +
            " AND A.用水年月 BETWEEN '169' AND '179' " +
            " GROUP BY A.客户编码,A.上月表数,A.本月表数,A.实用水量,B.年月 " +
            " ORDER BY B.年月"})
    public List<Map<String, Object>> getWaterInfo(String consNo);

    /**
     * 上传止码
     *
     * @param map
     * @return void
     */
    @Select("exec DBO.SJCB #{consNo},#{endCode},#{userNo},#{result,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType = StatementType.CALLABLE)
    public void uploadCode(Map<String, Object> map);

    /**
     * 查询欠费
     *
     * @param map
     * @return void
     */
    @Select("exec gogpay_user_qf #{consNo}, #{result,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType = StatementType.CALLABLE)
    public void queryFee(Map<String, Object> map);

    /**
     * 销账方法
     *
     * @param map
     * @return void
     */
    @Select("exec P_WNT_GOGPAY #{consNo},#{sjje},#{yjje},#{wyj},#{result,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType = StatementType.CALLABLE)
    public void writeOff(Map<String, Object> map);
}
