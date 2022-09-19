package org.jeecg.modules.bot.qqsys.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.jeecgframework.poi.excel.annotation.Excel;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: QQ指令分组
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Data
@TableName("qq_command_packet")
@ApiModel(value="qq_command_packet对象", description="QQ指令分组")
public class QqCommandPacket implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**分组名*/
    @Excel(name = "分组名", width = 15)
    @ApiModelProperty(value = "分组名")
    private java.lang.String name;
    /**类型*/
    @Excel(name = "类型", width = 15,dicCode = "commandType")
    @Dict(dicCode = "commandType")
    @ApiModelProperty(value = "类型")
    private java.lang.String type;
	/**是否启用*/
    @Excel(name = "是否启用", width = 15, dicCode = "isEnable")
    @Dict(dicCode = "isEnable")
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
