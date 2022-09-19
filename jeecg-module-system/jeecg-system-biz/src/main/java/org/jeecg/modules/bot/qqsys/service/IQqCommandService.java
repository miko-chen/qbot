package org.jeecg.modules.bot.qqsys.service;

import org.jeecg.modules.bot.qqsys.entity.QqCommand;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: QQ指令表
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
public interface IQqCommandService extends IService<QqCommand> {

  /**
   * 通过主表id查询子表数据
   *
   * @param mainId
   * @return List<QqCommand>
   */
	public List<QqCommand> selectByMainId(String mainId);
}
