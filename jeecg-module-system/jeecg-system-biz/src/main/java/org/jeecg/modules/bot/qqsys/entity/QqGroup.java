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
 * @Description: QQ群表
 * @Author: miko
 * @Date:   2022-09-19
 * @Version: V1.0
 */
@Data
@TableName("qq_group")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qq_group对象", description="QQ群表")
public class QqGroup implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**QQ群号*/
	@Excel(name = "QQ群号", width = 15)
    @ApiModelProperty(value = "QQ群号")
    private java.lang.Long groupId;
	/**群名*/
	@Excel(name = "群名", width = 15)
    @ApiModelProperty(value = "群名")
    private java.lang.String name;
	/**群公告*/
	@Excel(name = "群公告", width = 15)
    @ApiModelProperty(value = "群公告")
    private java.lang.String announcement;
	/**是否开启坦白说*/
	@Excel(name = "是否开启坦白说", width = 15, dicCode = "isEnable")
	@Dict(dicCode = "isEnable")
    @ApiModelProperty(value = "是否开启坦白说")
    private java.lang.Integer confessTalk;
	/**是否允许群员邀请*/
	@Excel(name = "是否允许群员邀请", width = 15, dicCode = "isEnable")
	@Dict(dicCode = "isEnable")
    @ApiModelProperty(value = "是否允许群员邀请")
    private java.lang.Integer allowMemberInvite;
	/**是否开启自动审批入群*/
	@Excel(name = "是否开启自动审批入群", width = 15, dicCode = "isEnable")
	@Dict(dicCode = "isEnable")
    @ApiModelProperty(value = "是否开启自动审批入群")
    private java.lang.Integer autoApprove;
	/**是否允许匿名聊天*/
	@Excel(name = "是否允许匿名聊天", width = 15, dicCode = "isEnable")
	@Dict(dicCode = "isEnable")
    @ApiModelProperty(value = "是否允许匿名聊天")
    private java.lang.Integer anonymousChat;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
    @ApiModelProperty(value = "是否启用")
    private java.lang.Integer isEnable;
	/**到期时间*/
	@Excel(name = "到期时间", width = 15)
    @ApiModelProperty(value = "到期时间")
    private java.lang.String expirationTime;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
    private java.lang.String balance;
	/**权限*/
	@Excel(name = "权限", width = 15)
    @ApiModelProperty(value = "权限")
    private java.lang.String permission;
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
