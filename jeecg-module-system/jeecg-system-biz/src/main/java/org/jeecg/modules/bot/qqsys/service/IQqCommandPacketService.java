package org.jeecg.modules.bot.qqsys.service;

import org.jeecg.modules.bot.qqsys.entity.QqCommand;
import org.jeecg.modules.bot.qqsys.entity.QqCommandPacket;
import com.baomidou.mybatisplus.extension.service.IService;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: QQ指令分组
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
public interface IQqCommandPacketService extends IService<QqCommandPacket> {

	/**
	 * 删除一对多
	 *
	 * @param id
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 *
	 * @param idList
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);


}
