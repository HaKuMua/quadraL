package cn.com.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 页码从1开始
 * 使用这个帮助类，
 * 1. 创建PageUtil对象
 * 2. 调用setCountRow方法设置总行数
 * 3. 设置当前要查询的页码
 * 4. 把查询到的数据通过setList放到PageUtil对象中
 * 5. PageUtil对象发送给页面
 */
public class PageUtil<T> implements Serializable{
	//总行数
	private Integer countRow = 0;
	//一页显示多少个
	private Integer pageSize = 2;
	//当页数
	private Integer currentPage = 1;
	//总页数
	private Integer countPage = 1;
	//当前起始行
	private Integer startRow = 0;
	//当前页结束行
	private Integer endRow = 0;
	
	/**
	 * 查询到的结果
	 */
	private Map<String,Object> map = new HashMap<String, Object>();
	
	private List<T> list = new ArrayList<T>();
	
	
	public Map<String,Object> getMap() {
		return map;
	}
	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getCountRow() {
		return countRow;
	}
	/**
	 * 当总行数发生改变的时候  也需要改变总页数
	 * @param countRow
	 */
	public void setCountRow(Integer countRow) {
		if(countRow > 0){
			this.countRow = countRow;
			//根据最新设置的总行数  计算总页数
			this.countPage = (this.countRow / pageSize) + 1;
			//计算起始行
			this.startRow = (this.currentPage-1) * pageSize;
			//计算结束行
			this.endRow = startRow + pageSize - 1;
		}
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
	public Integer getCountPage() {
		return countPage;
	}
	public void setCountPage(Integer countPage) {
		this.countPage = countPage;
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
	@Override
	public String toString() {
		return "PageUtil [countRow=" + countRow + ", pageSize=" + pageSize
				+ ", currentPage=" + currentPage + ", countPage=" + countPage
				+ ", startRow=" + startRow + ", endRow=" + endRow + ", list="
				+ map + "]";
	}
	
	
}
