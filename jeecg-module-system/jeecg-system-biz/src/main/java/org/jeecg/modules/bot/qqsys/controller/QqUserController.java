package org.jeecg.modules.bot.qqsys.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.bot.qqsys.entity.QqUser;
import org.jeecg.modules.bot.qqsys.service.IQqUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: QQ用户表
 * @Author: miko
 * @Date:   2022-09-19
 * @Version: V1.0
 */
@Api(tags="QQ用户表")
@RestController
@RequestMapping("/qqsys/qqUser")
@Slf4j
public class QqUserController extends JeecgController<QqUser, IQqUserService> {
	@Resource
	private IQqUserService qqUserService;
	
	/**
	 * 分页列表查询
	 *
	 * @param qqUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "QQ用户表-分页列表查询")
	@ApiOperation(value="QQ用户表-分页列表查询", notes="QQ用户表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QqUser>> queryPageList(QqUser qqUser,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<QqUser> queryWrapper = QueryGenerator.initQueryWrapper(qqUser, req.getParameterMap());
		Page<QqUser> page = new Page<QqUser>(pageNo, pageSize);
		IPage<QqUser> pageList = qqUserService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param qqUser
	 * @return
	 */
	@AutoLog(value = "QQ用户表-添加")
	@ApiOperation(value="QQ用户表-添加", notes="QQ用户表-添加")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_user:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody QqUser qqUser) {
		qqUserService.save(qqUser);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param qqUser
	 * @return
	 */
	@AutoLog(value = "QQ用户表-编辑")
	@ApiOperation(value="QQ用户表-编辑", notes="QQ用户表-编辑")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_user:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody QqUser qqUser) {
		qqUserService.updateById(qqUser);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "QQ用户表-通过id删除")
	@ApiOperation(value="QQ用户表-通过id删除", notes="QQ用户表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_user:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		qqUserService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "QQ用户表-批量删除")
	@ApiOperation(value="QQ用户表-批量删除", notes="QQ用户表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_user:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qqUserService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "QQ用户表-通过id查询")
	@ApiOperation(value="QQ用户表-通过id查询", notes="QQ用户表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<QqUser> queryById(@RequestParam(name="id",required=true) String id) {
		QqUser qqUser = qqUserService.getById(id);
		if(qqUser==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(qqUser);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param qqUser
    */
    //@RequiresPermissions("org.jeecg.modules.bot:qq_user:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, QqUser qqUser) {
        return super.exportXls(request, qqUser, QqUser.class, "QQ用户表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qq_user:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, QqUser.class);
    }

}
