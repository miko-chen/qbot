package org.jeecg.modules.bot.qqsys.service.impl;

import org.jeecg.modules.bot.qqsys.entity.QqUser;
import org.jeecg.modules.bot.qqsys.mapper.QqUserMapper;
import org.jeecg.modules.bot.qqsys.service.IQqUserService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: QQ用户表
 * @Author: miko
 * @Date:   2022-09-19
 * @Version: V1.0
 */
@Service
public class QqUserServiceImpl extends ServiceImpl<QqUserMapper, QqUser> implements IQqUserService {

}
