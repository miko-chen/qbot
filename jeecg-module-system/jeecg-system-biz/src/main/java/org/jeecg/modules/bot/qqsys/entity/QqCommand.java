package org.jeecg.modules.bot.qqsys.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecg.common.aspect.annotation.Dict;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.UnsupportedEncodingException;

/**
 * @Description: QQ指令表
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Data
@TableName("qq_command")
@ApiModel(value="qq_command对象", description="QQ指令表")
public class QqCommand implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**指令名*/
	@Excel(name = "指令名", width = 15)
    @ApiModelProperty(value = "指令名")
    private java.lang.String command;
	/**指令处理地址*/
	@Excel(name = "指令处理地址", width = 15)
    @ApiModelProperty(value = "指令处理地址")
    private java.lang.String url;
	/**是否启用*/
	@Excel(name = "是否启用", width = 15)
    @Dict(dicCode = "isEnable")
    @ApiModelProperty(value = "是否启用")
    private java.lang.Integer isEnable;
	/**分组*/
    @ApiModelProperty(value = "分组")
    private java.lang.String packet;
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
