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
	/**
	 * 在修改每页显示个数的时候  也需要改变总页数
	 * @param pageSize
	 */
	public void setPageSize(Integer pageSize) {
		if(pageSize == null){
			return;
		}else if(pageSize % 10 == 0){
			this.pageSize = pageSize;
			//根据最新设置的总行数  计算总页数
			this.countPage = (this.countRow / pageSize) + 1;
			//计算起始行
			this.startRow = (this.currentPage-1) * pageSize;
			//计算结束行
			this.endRow = startRow + pageSize - 1;
		}
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null){
			this.currentPage = 1;
		}else if(currentPage < 1){//如果请求的页码小于1   就直接返回第一页
			this.currentPage = 1;
		}else if(currentPage > countPage){//如果请求的页码大于总页数   就直接返回尾页
			this.currentPage = countPage;
		}else{
			this.currentPage = currentPage;
		}
		//计算起始行
		this.startRow = (this.currentPage-1) * pageSize;
		//计算结束行
		this.endRow = startRow + pageSize - 1;
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
