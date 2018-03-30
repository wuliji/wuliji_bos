package com.wuliji.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wuliji.bos.entity.Region;
import com.wuliji.bos.entity.Subarea;
import com.wuliji.bos.service.SubareaService;
import com.wuliji.bos.utils.FileUtils;
import com.wuliji.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea>{
	
	@Autowired
	private SubareaService service;
	
	/**
	 * 添加分区
	 */
	public String add() {
		service.save(model);
		return LIST;
	}
	
	/**
	 * 数据页面显示
	 */
	public String pageQuery() {
		DetachedCriteria dc = pageBean.getDetachedCriteria();	
		//动态添加过滤条件
		String addresskey = model.getAddresskey();
		if(StringUtils.isNotBlank(addresskey)) {
			//添加过滤条件，根据地址关键字模糊查询
			dc.add(Restrictions.like("addresskey", "%" + addresskey + "%"));
		}
		Region region = model.getRegion();
		if(region != null) {
			String province = region.getProvince();
			String city = region.getCity();
			String district = region.getDistrict();
			//分区对象中关联中的区域对象的属性名称，还有别名
			dc.createAlias("region", "r");
			if(StringUtils.isNotBlank(province)) {
				//添加过滤条件，根据地址关键字模糊查询
				dc.add(Restrictions.like("r.province", "%" + province + "%"));
			}
			if(StringUtils.isNotBlank(city)) {
				//添加过滤条件，根据地址关键字模糊查询
				dc.add(Restrictions.like("r.city", "%" + city + "%"));
			}
			if(StringUtils.isNotBlank(district)) {
				//添加过滤条件，根据地址关键字模糊查询
				dc.add(Restrictions.like("r.district", "%" + district + "%"));
			}
			
		}
		service.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage", "detachedCriteria", "pageSize","decidedzone","subareas"});
		return NONE;
	}
	
	/**
	 * 分区数据导出功能
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		//查询所有分区数据
		List<Subarea> list = service.findAll();
		//使用POI将数据写到Excel文件中
		//在内存中创建一个excel文件
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个表格
		HSSFSheet sheet = workbook.createSheet("分区数据");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("开始编号");
		headRow.createCell(2).setCellValue("结束编号");
		headRow.createCell(3).setCellValue("位置信息");
		headRow.createCell(4).setCellValue("省市区");
		//数据导入
		for (Subarea subarea : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getStartnum());
			dataRow.createCell(2).setCellValue(subarea.getEndnum());
			dataRow.createCell(3).setCellValue(subarea.getPosition());
			dataRow.createCell(4).setCellValue(subarea.getRegion().getName());
		}
		//使用输出流进行文件下载
		String filename = "分区数据.xls";
		//获得文件后缀名类型格式
		String mimeType = ServletActionContext.getServletContext().getMimeType(filename);
		ServletOutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setContentType(mimeType);
		//获取客户端类型
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		//解决中文乱码的问题
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		workbook.write(outputStream);
		return NONE;
	}
	
	/**
	 * 查询所有未关联的定区数据
	 * @return
	 */
	public String listajax() {
		List<Subarea> list = service.findListNotRelate();
		this.javaToJson(list, new String[] {"decidedzone","region"});
		return NONE;
	}
}
