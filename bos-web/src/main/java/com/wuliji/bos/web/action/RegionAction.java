package com.wuliji.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wuliji.bos.entity.Region;
import com.wuliji.bos.service.RegionService;
import com.wuliji.bos.utils.PageBean;
import com.wuliji.bos.utils.PinYin4jUtils;
import com.wuliji.bos.web.action.base.BaseAction;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 区域管理
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region>{
	
	@Autowired
	private RegionService regionService;
	
	//属性驱动，接收上传的文件
	private File regionFile;

	public File getRegionFile() {
		return regionFile;
	}

	public void setRegionFile(File regionFile) {
		this.regionFile = regionFile;
	}
	
	/**
	 * 区域Xls文件数据导入
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException {
		//使用POI解析Excel文件
		HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(regionFile));
		//得到指定表对象
		HSSFSheet sheet = workBook.getSheet("Sheet1");
		//指定数据装入
		List<Region> regionList = new ArrayList<>();
		//遍历表中的所有行
		for (Row row : sheet) {
			//跳过主题行
			if(row.getRowNum() == 0) {
				continue;
			}
			//遍历行中的所有单元格
			String id = row.getCell(0).getStringCellValue();
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			//包装区域
			Region region = new Region(id, province, city, district, postcode, null, null, null);
			//区域编码
			province = province.substring(0, province.length() - 1);
			city = city.substring(0, city.length() - 1);
			district = district.substring(0, district.length() - 1);
			String info = province + city + district;
			String[] headByString = PinYin4jUtils.getHeadByString(info);
			String shortcode = StringUtils.join(headByString);
			//城市编码
			String citycode = PinYin4jUtils.hanziToPinyin(city, "");
			region.setShortcode(shortcode);
			region.setCitycode(citycode);
			regionList.add(region);
		}
		//批量保存
		regionService.saveBatch(regionList);
		return NONE;
	}
	

	/**
	 * 分页查询
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException {
		regionService.pageQuery(pageBean);
		this.javaToJson(pageBean, new String[] {"currentPage", "detachedCriteria", "pageSize"});
		return NONE;
	}
}
