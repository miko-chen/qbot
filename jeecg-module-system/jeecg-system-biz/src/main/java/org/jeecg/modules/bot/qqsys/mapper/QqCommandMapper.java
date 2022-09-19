package org.jeecg.modules.bot.qqsys.mapper;

import java.util.List;
import org.jeecg.modules.bot.qqsys.entity.QqCommand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description: QQ指令表
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
public interface QqCommandMapper extends BaseMapper<QqCommand> {

	/**
	 * 通过主表id删除子表数据
	 *
	 * @param mainId 主表id
	 * @return boolean
	 */
	public boolean deleteByMainId(@Param("mainId") String mainId);

   /**
    * 通过主表id查询子表数据
    *
    * @param mainId 主表id
    * @return List<QqCommand>
    */
	public List<QqCommand> selectByMainId(@Param("mainId") String mainId);

}
