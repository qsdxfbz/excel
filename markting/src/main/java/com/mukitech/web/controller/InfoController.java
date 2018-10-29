package com.mukitech.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.xmlbeans.impl.xb.xmlschema.impl.SpaceAttributeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mukitech.web.domain.Info;
import com.mukitech.web.domain.ReadInfo;
import com.mukitech.web.service.IInfoService;
import com.mukitech.web.util.AjaxResult;
import com.mukitech.web.util.ExcelUtil;
import com.mukitech.web.util.ExcelUtils;
import com.mukitech.web.util.ImportExcelUtil;
import com.mukitech.web.util.Result;
import com.mukitech.web.util.StringUtils;
import com.mukitech.web.util.TableDataInfo;

import springfox.documentation.spring.web.json.Json;

/**
 * 
 * @author sky
 * @date 2018-10-25
 */
@Controller
@RequestMapping("/module/info")
public class InfoController extends BaseController {
	private String prefix = "module/info";

	@Autowired
	private IInfoService infoService;

	@GetMapping()
	public String info() {
		return prefix + "/info";
	}

	/**
	 * 查询列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Info info) {
		startPage();
		List<Info> list = infoService.selectInfoList(info);
		return getDataTable(list);
	}

	/**
	 * 新增
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存
	 */
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Info info) {
		return toAjax(infoService.insertInfo(info));
	}

	/**
	 * 修改
	 */
	@GetMapping("/edit/{companyId}")
	public String edit(@PathVariable("companyId") Long companyId, ModelMap mmap) {
		Info info = infoService.selectInfoById(companyId);
		mmap.put("info", info);
		return prefix + "/edit";
	}

	/**
	 * 修改保存
	 */
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Info info) {
		return toAjax(infoService.updateInfo(info));
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(infoService.deleteInfoByIds(ids));
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/import")
	public String impExcel() {
		return prefix + "/ImportExcel";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	private Result importExcel(@RequestParam(value = "excelFile", required = false) MultipartFile[] file,
			HttpServletRequest request) {
		try {
			MultipartRequest multipartRequest = (MultipartRequest) request;
			MultipartFile excelFile = multipartRequest.getFile("excelFile");
			if (excelFile != null) {
				List<List<String>> datas = ExcelUtils.readXlsx(excelFile.getInputStream());
				List<Info> infolist = new ArrayList<Info>();
				int num = StringUtils.random();
				
				for (int i = 0; i < datas.size(); i++) {
					Info info = new Info();
					List<String> row = datas.get(i);

					String compname = (String) row.get(0);
					String name = (String) row.get(1);
					String type = (String) row.get(2);
					String address = (String) row.get(3);
					String phone = (String) row.get(4);
					String date = (String) row.get(5);
					String datea = StringUtils.transFormat1(date);
					Date dateb = StringUtils.parseServerTime(datea,"yyyy-MM-dd");
					
					info.setCompanyName(compname);
					info.setCompanyContactName(name);
					info.setCompanyType(type);
					info.setCompanyAddress(address);
					info.setCompanyContactPhone(phone);
					info.setCreatedDate(dateb);
					info.setUpdatedDate(new Date());

					String datec = StringUtils.transFormat2(date);
					
					StringBuffer sb = new StringBuffer(datec);
					String serNum = sb.append(num).toString();
					info.setSerialNumber(serNum);
					// 我也很无语 为啥解析 的是这种格式的 23-十月-2018
					
					infoService.insertInfo(info);
				}
				
				if (datas != null && datas.size() > 0) {
					return new Result(true);
				}
			} else {
				return new Result(false);
			}
		} catch (Exception e) {
			return new Result(false, e.getMessage());
		}
		return new Result(false);
	}

}
