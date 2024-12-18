package cn.edu.xmu.oomall.payment.mapper.generator;

import cn.edu.xmu.oomall.payment.mapper.generator.po.PayTransPo;
import cn.edu.xmu.oomall.payment.mapper.generator.po.PayTransPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PayTransPoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @Delete({
        "delete from payment_pay_trans",
        "where `id` = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @Insert({
        "insert into payment_pay_trans (`out_no`, `trans_no`, ",
        "`amount`, `status`, `success_time`, ",
        "`adjust_id`, `adjust_name`, ",
        "`adjust_time`, `sp_openid`, ",
        "`time_expire`, `time_begin`, ",
        "`account_id`, `creator_id`, ",
        "`creator_name`, `modifier_id`, ",
        "`modifier_name`, `gmt_create`, ",
        "`gmt_modified`, `prepay_id`, ",
        "`div_amount`, `in_refund`, ",
        "`shop_id`, `description`, ",
        "`ledger_id`)",
        "values (#{outNo,jdbcType=VARCHAR}, #{transNo,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=BIGINT}, #{status,jdbcType=TINYINT}, #{successTime,jdbcType=TIMESTAMP}, ",
        "#{adjustId,jdbcType=BIGINT}, #{adjustName,jdbcType=VARCHAR}, ",
        "#{adjustTime,jdbcType=TIMESTAMP}, #{spOpenid,jdbcType=VARCHAR}, ",
        "#{timeExpire,jdbcType=TIMESTAMP}, #{timeBegin,jdbcType=TIMESTAMP}, ",
        "#{accountId,jdbcType=BIGINT}, #{creatorId,jdbcType=BIGINT}, ",
        "#{creatorName,jdbcType=VARCHAR}, #{modifierId,jdbcType=BIGINT}, ",
        "#{modifierName,jdbcType=VARCHAR}, #{gmtCreate,jdbcType=TIMESTAMP}, ",
        "#{gmtModified,jdbcType=TIMESTAMP}, #{prepayId,jdbcType=VARCHAR}, ",
        "#{divAmount,jdbcType=BIGINT}, #{inRefund,jdbcType=TINYINT}, ",
        "#{shopId,jdbcType=BIGINT}, #{description,jdbcType=VARCHAR}, ",
        "#{ledgerId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PayTransPo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @InsertProvider(type=PayTransPoSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(PayTransPo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @SelectProvider(type=PayTransPoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="out_no", property="outNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="trans_no", property="transNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="success_time", property="successTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="adjust_id", property="adjustId", jdbcType=JdbcType.BIGINT),
        @Result(column="adjust_name", property="adjustName", jdbcType=JdbcType.VARCHAR),
        @Result(column="adjust_time", property="adjustTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sp_openid", property="spOpenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="time_expire", property="timeExpire", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="time_begin", property="timeBegin", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.BIGINT),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
        @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
        @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="prepay_id", property="prepayId", jdbcType=JdbcType.VARCHAR),
        @Result(column="div_amount", property="divAmount", jdbcType=JdbcType.BIGINT),
        @Result(column="in_refund", property="inRefund", jdbcType=JdbcType.TINYINT),
        @Result(column="shop_id", property="shopId", jdbcType=JdbcType.BIGINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="ledger_id", property="ledgerId", jdbcType=JdbcType.BIGINT)
    })
    List<PayTransPo> selectByExample(PayTransPoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "`id`, `out_no`, `trans_no`, `amount`, `status`, `success_time`, `adjust_id`, ",
        "`adjust_name`, `adjust_time`, `sp_openid`, `time_expire`, `time_begin`, `account_id`, ",
        "`creator_id`, `creator_name`, `modifier_id`, `modifier_name`, `gmt_create`, ",
        "`gmt_modified`, `prepay_id`, `div_amount`, `in_refund`, `shop_id`, `description`, ",
        "`ledger_id`",
        "from payment_pay_trans",
        "where `id` = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="out_no", property="outNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="trans_no", property="transNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="amount", property="amount", jdbcType=JdbcType.BIGINT),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT),
        @Result(column="success_time", property="successTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="adjust_id", property="adjustId", jdbcType=JdbcType.BIGINT),
        @Result(column="adjust_name", property="adjustName", jdbcType=JdbcType.VARCHAR),
        @Result(column="adjust_time", property="adjustTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sp_openid", property="spOpenid", jdbcType=JdbcType.VARCHAR),
        @Result(column="time_expire", property="timeExpire", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="time_begin", property="timeBegin", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.BIGINT),
        @Result(column="creator_id", property="creatorId", jdbcType=JdbcType.BIGINT),
        @Result(column="creator_name", property="creatorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="modifier_id", property="modifierId", jdbcType=JdbcType.BIGINT),
        @Result(column="modifier_name", property="modifierName", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="prepay_id", property="prepayId", jdbcType=JdbcType.VARCHAR),
        @Result(column="div_amount", property="divAmount", jdbcType=JdbcType.BIGINT),
        @Result(column="in_refund", property="inRefund", jdbcType=JdbcType.TINYINT),
        @Result(column="shop_id", property="shopId", jdbcType=JdbcType.BIGINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="ledger_id", property="ledgerId", jdbcType=JdbcType.BIGINT)
    })
    PayTransPo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @UpdateProvider(type=PayTransPoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("row") PayTransPo row, @Param("example") PayTransPoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @UpdateProvider(type=PayTransPoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("row") PayTransPo row, @Param("example") PayTransPoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @UpdateProvider(type=PayTransPoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PayTransPo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_pay_trans
     *
     * @mbg.generated
     */
    @Update({
        "update payment_pay_trans",
        "set `out_no` = #{outNo,jdbcType=VARCHAR},",
          "`trans_no` = #{transNo,jdbcType=VARCHAR},",
          "`amount` = #{amount,jdbcType=BIGINT},",
          "`status` = #{status,jdbcType=TINYINT},",
          "`success_time` = #{successTime,jdbcType=TIMESTAMP},",
          "`adjust_id` = #{adjustId,jdbcType=BIGINT},",
          "`adjust_name` = #{adjustName,jdbcType=VARCHAR},",
          "`adjust_time` = #{adjustTime,jdbcType=TIMESTAMP},",
          "`sp_openid` = #{spOpenid,jdbcType=VARCHAR},",
          "`time_expire` = #{timeExpire,jdbcType=TIMESTAMP},",
          "`time_begin` = #{timeBegin,jdbcType=TIMESTAMP},",
          "`account_id` = #{accountId,jdbcType=BIGINT},",
          "`creator_id` = #{creatorId,jdbcType=BIGINT},",
          "`creator_name` = #{creatorName,jdbcType=VARCHAR},",
          "`modifier_id` = #{modifierId,jdbcType=BIGINT},",
          "`modifier_name` = #{modifierName,jdbcType=VARCHAR},",
          "`gmt_create` = #{gmtCreate,jdbcType=TIMESTAMP},",
          "`gmt_modified` = #{gmtModified,jdbcType=TIMESTAMP},",
          "`prepay_id` = #{prepayId,jdbcType=VARCHAR},",
          "`div_amount` = #{divAmount,jdbcType=BIGINT},",
          "`in_refund` = #{inRefund,jdbcType=TINYINT},",
          "`shop_id` = #{shopId,jdbcType=BIGINT},",
          "`description` = #{description,jdbcType=VARCHAR},",
          "`ledger_id` = #{ledgerId,jdbcType=BIGINT}",
        "where `id` = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PayTransPo row);
}