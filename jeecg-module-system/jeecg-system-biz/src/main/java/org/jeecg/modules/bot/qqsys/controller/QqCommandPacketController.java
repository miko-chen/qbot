package org.jeecg.modules.bot.qqsys.controller;

import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.api.vo.Result;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.bot.qqsys.entity.QqCommand;
import org.jeecg.modules.bot.qqsys.entity.QqCommandPacket;
import org.jeecg.modules.bot.qqsys.service.IQqCommandPacketService;
import org.jeecg.modules.bot.qqsys.service.IQqCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

 /**
 * @Description: QQ指令分组
 * @Author: miko
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Api(tags="QQ指令分组")
@RestController
@RequestMapping("/qqsys/qqCommandPacket")
@Slf4j
public class QqCommandPacketController extends JeecgController<QqCommandPacket, IQqCommandPacketService> {

	@Resource
	private IQqCommandPacketService qqCommandPacketService;

	@Resource
	private IQqCommandService qqCommandService;


	/*---------------------------------主表处理-begin-------------------------------------*/

	/**
	 * 分页列表查询
	 * @param qqCommandPacket
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "QQ指令分组-分页列表查询")
	@ApiOperation(value="QQ指令分组-分页列表查询", notes="QQ指令分组-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<QqCommandPacket>> queryPageList(QqCommandPacket qqCommandPacket,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<QqCommandPacket> queryWrapper = QueryGenerator.initQueryWrapper(qqCommandPacket, req.getParameterMap());
		Page<QqCommandPacket> page = new Page<QqCommandPacket>(pageNo, pageSize);
		IPage<QqCommandPacket> pageList = qqCommandPacketService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
     *   添加
     * @param qqCommandPacket
     * @return
     */
    @AutoLog(value = "QQ指令分组-添加")
    @ApiOperation(value="QQ指令分组-添加", notes="QQ指令分组-添加")
    @PostMapping(value = "/add")
    public Result<String> add(@RequestBody QqCommandPacket qqCommandPacket) {
        qqCommandPacketService.save(qqCommandPacket);
        return Result.OK("添加成功！");
    }

    /**
     *  编辑
     * @param qqCommandPacket
     * @return
     */
    @AutoLog(value = "QQ指令分组-编辑")
    @ApiOperation(value="QQ指令分组-编辑", notes="QQ指令分组-编辑")
    @RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
    public Result<String> edit(@RequestBody QqCommandPacket qqCommandPacket) {
        qqCommandPacketService.updateById(qqCommandPacket);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @AutoLog(value = "QQ指令分组-通过id删除")
    @ApiOperation(value="QQ指令分组-通过id删除", notes="QQ指令分组-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<String> delete(@RequestParam(name="id",required=true) String id) {
        qqCommandPacketService.delMain(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @AutoLog(value = "QQ指令分组-批量删除")
    @ApiOperation(value="QQ指令分组-批量删除", notes="QQ指令分组-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.qqCommandPacketService.delBatchMain(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, QqCommandPacket qqCommandPacket) {
        return super.exportXls(request, qqCommandPacket, QqCommandPacket.class, "QQ指令分组");
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, QqCommandPacket.class);
    }
	/*---------------------------------主表处理-end-------------------------------------*/
	

    /*--------------------------------子表处理-QQ指令表-begin----------------------------------------------*/
	/**
	 * 通过主表ID查询
	 * @return
	 */
	//@AutoLog(value = "QQ指令表-通过主表ID查询")
	@ApiOperation(value="QQ指令表-通过主表ID查询", notes="QQ指令表-通过主表ID查询")
	@GetMapping(value = "/listQqCommandByMainId")
    public Result<IPage<QqCommand>> listQqCommandByMainId(QqCommand qqCommand,
                                                    @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                    @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                    HttpServletRequest req) {
        QueryWrapper<QqCommand> queryWrapper = QueryGenerator.initQueryWrapper(qqCommand, req.getParameterMap());
        Page<QqCommand> page = new Page<QqCommand>(pageNo, pageSize);
        IPage<QqCommand> pageList = qqCommandService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

	/**
	 * 添加
	 * @param qqCommand
	 * @return
	 */
	@AutoLog(value = "QQ指令表-添加")
	@ApiOperation(value="QQ指令表-添加", notes="QQ指令表-添加")
	@PostMapping(value = "/addQqCommand")
	public Result<String> addQqCommand(@RequestBody QqCommand qqCommand) {
		qqCommandService.save(qqCommand);
		return Result.OK("添加成功！");
	}

    /**
	 * 编辑
	 * @param qqCommand
	 * @return
	 */
	@AutoLog(value = "QQ指令表-编辑")
	@ApiOperation(value="QQ指令表-编辑", notes="QQ指令表-编辑")
	@RequestMapping(value = "/editQqCommand", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> editQqCommand(@RequestBody QqCommand qqCommand) {
		qqCommandService.updateById(qqCommand);
		return Result.OK("编辑成功!");
	}

	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@AutoLog(value = "QQ指令表-通过id删除")
	@ApiOperation(value="QQ指令表-通过id删除", notes="QQ指令表-通过id删除")
	@DeleteMapping(value = "/deleteQqCommand")
	public Result<String> deleteQqCommand(@RequestParam(name="id",required=true) String id) {
		qqCommandService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "QQ指令表-批量删除")
	@ApiOperation(value="QQ指令表-批量删除", notes="QQ指令表-批量删除")
	@DeleteMapping(value = "/deleteBatchQqCommand")
	public Result<String> deleteBatchQqCommand(@RequestParam(name="ids",required=true) String ids) {
	    this.qqCommandService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/exportQqCommand")
    public ModelAndView exportQqCommand(HttpServletRequest request, QqCommand qqCommand) {
		 // Step.1 组装查询条件
		 QueryWrapper<QqCommand> queryWrapper = QueryGenerator.initQueryWrapper(qqCommand, request.getParameterMap());
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		 // Step.2 获取导出数据
		 List<QqCommand> pageList = qqCommandService.list(queryWrapper);
		 List<QqCommand> exportList = null;

		 // 过滤选中数据
		 String selections = request.getParameter("selections");
		 if (oConvertUtils.isNotEmpty(selections)) {
			 List<String> selectionList = Arrays.asList(selections.split(","));
			 exportList = pageList.stream().filter(item -> selectionList.contains(item.getId())).collect(Collectors.toList());
		 } else {
			 exportList = pageList;
		 }

		 // Step.3 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 //此处设置的filename无效,前端会重更新设置一下
		 mv.addObject(NormalExcelConstants.FILE_NAME, "QQ指令表");
		 mv.addObject(NormalExcelConstants.CLASS, QqCommand.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("QQ指令表报表", "导出人:" + sysUser.getRealname(), "QQ指令表"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
		 return mv;
    }

    /**
     * 导入
     * @return
     */
    @RequestMapping(value = "/importQqCommand/{mainId}")
    public Result<?> importQqCommand(HttpServletRequest request, HttpServletResponse response, @PathVariable("mainId") String mainId) {
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		 for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
       // 获取上传文件对象
			 MultipartFile file = entity.getValue();
			 ImportParams params = new ImportParams();
			 params.setTitleRows(2);
			 params.setHeadRows(1);
			 params.setNeedSave(true);
			 try {
				 List<QqCommand> list = ExcelImportUtil.importExcel(file.getInputStream(), QqCommand.class, params);
				 for (QqCommand temp : list) {
                    temp.setPacket(mainId);
				 }
				 long start = System.currentTimeMillis();
				 qqCommandService.saveBatch(list);
				 log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
				 return Result.OK("文件导入成功！数据行数：" + list.size());
			 } catch (Exception e) {
				 log.error(e.getMessage(), e);
				 return Result.error("文件导入失败:" + e.getMessage());
			 } finally {
				 try {
					 file.getInputStream().close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
			 }
		 }
		 return Result.error("文件导入失败！");
    }

    /*--------------------------------子表处理-QQ指令表-end----------------------------------------------*/




}
