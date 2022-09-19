package org.jeecg.modules.bot.qqsys.service.impl;

import org.jeecg.modules.bot.qqsys.entity.QqCommand;
import org.jeecg.modules.bot.qqsys.mapper.QqCommandMapper;
import org.jeecg.modules.bot.qqsys.service.IQqCommandService;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import javax.annotation.Resource;

/**
 * @Description: QQ指令表
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Service
public class QqCommandServiceImpl extends ServiceImpl<QqCommandMapper, QqCommand> implements IQqCommandService {
	
	@Resource
	private QqCommandMapper qqCommandMapper;
	
	@Override
	public List<QqCommand> selectByMainId(String mainId) {
		return qqCommandMapper.selectByMainId(mainId);
	}
}
