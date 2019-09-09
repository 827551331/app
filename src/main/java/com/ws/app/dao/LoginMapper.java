package com.ws.app.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
//    @Select({"SELECT  A.客户编码 AS consNo,A.抄表线路 AS bcId,B.线路名称 bcName,A.水表位置 sbAddr,B.CBY userName,C.操作员编码 userNo FROM T客户水表 A INNER JOIN T抄表线路 B " +
//            " ON A.抄表线路 = B.线路编码 AND A.部门编码 = B.营业所编码 " +
//            " INNER JOIN T操作人员 C ON B.CBY = C.姓名 " +
//            " WHERE A.供水情况 = '0' " +
//            " AND C.操作员编码 = #{userNo} " +
//            " GROUP BY A.客户编码,B.线路名称,A.水表位置,B.CBY,C.操作员编码,A.抄表线路 " +
//            " ORDER BY B.线路名称,A.水表位置"})
    @Select({"SELECT B.客户编码 consNo,MAX(C.客户名称) AS consName , MAX(B.祥细地址) AS sbAddr,A.线路名称 bcName,B.水表位置 sbSite,A.线路编码 bcId FROM T抄表线路 A INNER JOIN  t客户水表 B " +
            " ON A.线路编码 = B.抄表线路 INNER JOIN  T客户资料 C ON  B.客户编码 = C.客户编码 " +
            " AND A.营业所编码 = B.部门编码 INNER JOIN T操作人员 D ON A.CBY = D.姓名 " +
            " WHERE D.操作员编码 = #{userNo} and  B.供水情况 = '0'" +
            " GROUP BY A.线路名称,B.水表位置,B.客户编码,A.线路编码 " +
            " ORDER BY A.线路名称 "})
    public List<Map<String, Object>> getBCInfo(String userNo);


    /**
     * 查询水表近一年用水信息
     *
     * @param consNo
     * @return
     */
    @Select({"SELECT DISTINCT(A.客户编码) consNo,MAX(A.客户名称) AS consName,MAX(B.祥细地址) AS addr,MAX(A.联系电话) phone,C.上月表数 startCode,C.本月表数 endCode,D.年月 dateYM,B.抄表线路 bdId FROM T客户资料 A INNER JOIN T客户水表 B " +
            " ON A.客户编码 = B.客户编码 " +
            " INNER JOIN T用水记录 C ON A.客户编码 = C.客户编码 AND A.客户编码 = C.客户编码 INNER JOIN T用水年月 D ON C.用水年月 = D.年月编码  " +
            " WHERE A.客户编码 = #{consNo} " +
            " AND D.年月 BETWEEN #{startDate} AND #{endDate} " +
            " GROUP BY A.客户编码,D.年月,C.上月表数,C.本月表数,A.客户名称,B.抄表线路 " +
            " ORDER BY D.年月 "})
    public List<Map<String, Object>> getWaterInfo(String consNo, String startDate, String endDate);

    /**
     * 上传止码
     *
     * @param map
     * @return void
     */
    @Select("exec DBO.SJCB #{consNo},#{endCode},#{userNo},#{result,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType = StatementType.CALLABLE)
    public void uploadCode(Map<String, Object> map);


    @Update({"UPDATE T客户资料 SET 联系电话 = #{phone} WHERE 客户编码 = #{consNo}"})
    public int updatePhone(Map<String, Object> map);

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

    /**
     * 上传远传表止码
     *
     * @param map
     * @return void
     */
    @Select("exec BH_CHYC #{consNo},#{endCode},#{userNo},#{result,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType = StatementType.CALLABLE)
    public void uploadYCCode(Map<String, Object> map);
}
