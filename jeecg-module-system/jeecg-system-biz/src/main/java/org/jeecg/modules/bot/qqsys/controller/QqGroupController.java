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
import org.jeecg.modules.bot.qqsys.entity.QqGroup;
import org.jeecg.modules.bot.qqsys.service.IQqGroupService;

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
 * @Description: QQ群表
 * @Author: miko
 * @Date:   2022-09-19
 * @Version: V1.0
 */
@Api(tags="QQ群表")
@RestController
@RequestMapping("/qqsys/qqGroup")
@Slf4j
public class QqGroupController extends JeecgController<QqGroup, IQqGroupService> {
	@Resource
	private IQqGroupService qqGroupService;
	
	/**
	 * 分页列表查询
	 *
	 * @param qqGroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "QQ群表-分页列表查询")
	@ApiOperation(value="QQ群表-分页列表查询", notes="QQ群表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QqGroup>> queryPageList(QqGroup qqGroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<QqGroup> queryWrapper = QueryGenerator.initQueryWrapper(qqGroup, req.getParameterMap());
		Page<QqGroup> page = new Page<QqGroup>(pageNo, pageSize);
		IPage<QqGroup> pageList = qqGroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param qqGroup
	 * @return
	 */
	@AutoLog(value = "QQ群表-添加")
	@ApiOperation(value="QQ群表-添加", notes="QQ群表-添加")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_group:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody QqGroup qqGroup) {
		qqGroupService.save(qqGroup);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param qqGroup
	 * @return
	 */
	@AutoLog(value = "QQ群表-编辑")
	@ApiOperation(value="QQ群表-编辑", notes="QQ群表-编辑")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_group:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody QqGroup qqGroup) {
		qqGroupService.updateById(qqGroup);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "QQ群表-通过id删除")
	@ApiOperation(value="QQ群表-通过id删除", notes="QQ群表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_group:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		qqGroupService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "QQ群表-批量删除")
	@ApiOperation(value="QQ群表-批量删除", notes="QQ群表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.bot:qq_group:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qqGroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "QQ群表-通过id查询")
	@ApiOperation(value="QQ群表-通过id查询", notes="QQ群表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<QqGroup> queryById(@RequestParam(name="id",required=true) String id) {
		QqGroup qqGroup = qqGroupService.getById(id);
		if(qqGroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(qqGroup);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param qqGroup
    */
    //@RequiresPermissions("org.jeecg.modules.bot:qq_group:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, QqGroup qqGroup) {
        return super.exportXls(request, qqGroup, QqGroup.class, "QQ群表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("qq_group:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, QqGroup.class);
    }

}
