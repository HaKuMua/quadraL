package com.zj.entity;

import java.io.Serializable;

public class ArticleImg implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 文章图片ID
	 */
	private Integer image_id;
	/*
	 * 文章ID
	 */
	private Integer article_id;
	/*
	 * 文章图片url
	 */
	private String image_url;
	
	public ArticleImg(){
		
	}
	
	public Integer getImage_id() {
		return image_id;
	}
	public void setImage_id(Integer image_id) {
		this.image_id = image_id;
	}
	public Integer getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "ArticleImg [image_id=" + image_id + ", article_id="
				+ article_id + ", image_url=" + image_url + "]";
	}
	
}
