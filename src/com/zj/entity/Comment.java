package com.zj.entity;

import java.io.Serializable;

/**
 * 
 * @author lijia
 *
 */
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	/*
	 * 评论ID
	 */
	private Integer comment_id;
	/*
	 * 文章ID
	 */
	private Integer article_id;
	/*
	 * 用户ID
	 */
	private Integer user_id;
	/*
	 * 评论内容
	 */
	private String comment_content;
	/*
	 * 回复ID
	 */
	private String replier_id;
	
	public Comment() {
	}

	public Integer getComment_id() {
		return comment_id;
	}

	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
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

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getReplier_id() {
		return replier_id;
	}

	public void setReplier_id(String replier_id) {
		this.replier_id = replier_id;
	}

	public String toString() {
		return "Comment [comment_id=" + comment_id + ", article_id="
				+ article_id + ", user_id=" + user_id + ", comment_content="
				+ comment_content + ", replier_id=" + replier_id + "]";
	}
}