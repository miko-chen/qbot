package org.jeecg.modules.bot.qqsys.service.impl;

import org.jeecg.modules.bot.qqsys.entity.QqCommandPacket;
import org.jeecg.modules.bot.qqsys.entity.QqCommand;
import org.jeecg.modules.bot.qqsys.mapper.QqCommandMapper;
import org.jeecg.modules.bot.qqsys.mapper.QqCommandPacketMapper;
import org.jeecg.modules.bot.qqsys.service.IQqCommandPacketService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: QQ指令分组
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Service
public class QqCommandPacketServiceImpl extends ServiceImpl<QqCommandPacketMapper, QqCommandPacket> implements IQqCommandPacketService {

	@Resource
	private QqCommandPacketMapper qqCommandPacketMapper;
	@Resource
	private QqCommandMapper qqCommandMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		qqCommandMapper.deleteByMainId(id);
		qqCommandPacketMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			qqCommandMapper.deleteByMainId(id.toString());
			qqCommandPacketMapper.deleteById(id);
		}
	}
	
}
