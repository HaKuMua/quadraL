package cn.com.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询工具类
 * @author liu
 *
 */
public class PageUtil<T> implements Serializable{
	//总行数
	private Integer countRow=0;
	//总页数
	private Integer countPage =1;
	//一页显示个数
	private  Integer pageSize=10;
	//当前显示的页
	private Integer currentPage = 1;
	//起始行
	private Integer startRow = 0;
	//结束行
	private Integer endRow= 0;
	
	private List<T> list = new ArrayList<T>();
	private Map<String,Object> map = new HashMap<String,Object>();
	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
	public Integer getCountRow() {
		return countRow;
	}
	/**
	 * 设置总行数
	 * @param countRow
	 */
	public void setCountRow(Integer countRow) {
		if(countRow > 0) {
		this.countRow = countRow;
		this.countPage = countRow % pageSize == 0 ? (int) (countRow/pageSize) :(int) (countRow/pageSize)+1;
		this.startRow = (currentPage-1)*pageSize;
		this.endRow = startRow+pageSize-1;
		}
	}
	public Integer getCountPage() {
		return countPage;
	}
	public void setCountPage(Integer countPage) {
		this.countPage = countPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		if(pageSize %10 ==0) {
			this.pageSize = pageSize;
			this.countPage = countRow % pageSize == 0 ? (int) (countRow/pageSize) :(int) (countRow/pageSize)+1;
			this.startRow = (currentPage-1)*pageSize;
			this.endRow = startRow+pageSize-1;
		}
		
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage < 1) {
			this.currentPage = 1;
		}else if(currentPage > countPage) {
			this.currentPage = countPage;
		}else {
			this.currentPage = currentPage;
		}
		this.startRow = (this.currentPage-1)*pageSize+1;
		this.endRow = startRow+pageSize-1;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	public Integer getEndRow() {
		return endRow;
	}
	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}

	
	
}
