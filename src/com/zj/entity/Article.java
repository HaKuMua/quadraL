package com.zj.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author lijia
 *文章实体类
 */
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 文章ID
	 */
	private Integer article_id;
	/*
	 * 用户ID
	 */
	private Integer user_id;
	/*
	 * 文章名称
	 */
	private String article_name;
	/*
	 * 文章内容
	 */
	private String article_content;
	/*
	 * 文章发布时间
	 */
	private Date article_date;
	/*
	 * 文章赞数
	 */
	private Integer article_praise;
	/*
	 * 文章收藏数
	 */
	private Integer article_collect;
	
	
	public Integer getArticle_praise() {
		return article_praise;
	}

	public void setArticle_praise(Integer article_praise) {
		this.article_praise = article_praise;
	}

	public Integer getArticle_collect() {
		return article_collect;
	}

	public void setArticle_collect(Integer article_collect) {
		this.article_collect = article_collect;
	}

	public Article() {
	}

	public Integer getArticle_id() {
		return article_id;
	}

	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getArticle_name() {
		return article_name;
	}

	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public Date getArticle_date() {
		return article_date;
	}

	public void setArticle_date(Date article_date) {
		this.article_date = article_date;
	}

	@Override
	public String toString() {
		return "Article [article_id=" + article_id + ", user_id=" + user_id
				+ ", article_name=" + article_name + ", article_content="
				+ article_content + ", article_date=" + article_date
				+ ", article_praise=" + article_praise + ", article_collect="
				+ article_collect + "]";
	}

	
}
