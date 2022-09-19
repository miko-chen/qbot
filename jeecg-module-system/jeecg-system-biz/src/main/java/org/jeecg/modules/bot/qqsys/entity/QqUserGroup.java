package org.jeecg.modules.bot.qqsys.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: QQ用户与群关联表
 * @Author: miko
 * @Date:   2022-09-19
 * @Version: V1.0
 */
@Data
@TableName("qq_user_group")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qq_user_group对象", description="QQ用户与群关联表")
public class QqUserGroup implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**QQ*/
	@Excel(name = "QQ", width = 15)
    @ApiModelProperty(value = "QQ")
    private java.lang.Long qq;
	/**群号*/
	@Excel(name = "群号", width = 15)
    @ApiModelProperty(value = "群号")
    private java.lang.Long groupId;
	/**入群时间*/
	@Excel(name = "入群时间", width = 15)
    @ApiModelProperty(value = "入群时间")
    private java.lang.Long joinTimestamp;
	/**上次发言时间*/
	@Excel(name = "上次发言时间", width = 15)
    @ApiModelProperty(value = "上次发言时间")
    private java.lang.Long lastSpeakTimestamp;
	/**马甲*/
	@Excel(name = "马甲", width = 15)
    @ApiModelProperty(value = "马甲")
    private java.lang.String memberName;
	/**权限*/
	@Excel(name = "权限", width = 15)
    @ApiModelProperty(value = "权限")
    private java.lang.String permission;
	/**剩余禁言时间*/
	@Excel(name = "剩余禁言时间", width = 15)
    @ApiModelProperty(value = "剩余禁言时间")
    private java.lang.Long mutetimeremaining;
	/**特殊称号*/
	@Excel(name = "特殊称号", width = 15)
    @ApiModelProperty(value = "特殊称号")
    private java.lang.String specialTitle;
	/**上次签到时间*/
	@Excel(name = "上次签到时间", width = 15)
    @ApiModelProperty(value = "上次签到时间")
    private java.lang.Long lastSignTime;
	/**积分*/
	@Excel(name = "积分", width = 15)
    @ApiModelProperty(value = "积分")
    private java.lang.Integer integral;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
    @ApiModelProperty(value = "是否启用")
    private java.lang.Integer isEnable;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
}
