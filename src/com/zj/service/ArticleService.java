package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.com.uitl.PageUtil;

import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.dao.impl.ArticleImgDaoImpl;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.realize.ArticleDao;
import com.zj.dao.realize.ArticleImgDao;
import com.zj.dao.realize.CommentDao;
import com.zj.entity.Article;
import com.zj.entity.ArticleImg;
import com.zj.entity.Comment;
import com.zj.service.imp.ArticleServiceImpl;

/**
 * 文章服务层
 * @author liyifeng
 *
 */
public class ArticleService implements ArticleServiceImpl{
	private ArticleDaoImpl articleDaoImpl = new ArticleDao();
	private ArticleImgDaoImpl articleImgDaoImpl = new ArticleImgDao();
	private CommentDaoImpl commentDaoImpl = new CommentDao();
	/**
	 * 获得所有文章
	 * @return
	 */
	public List<Map<String, Object>> getAllArticle(){
		List<Map<String, Object>> list = null;
		try {
			List<Article> articleList = articleDaoImpl.getAllArticle();
			if(articleList != null){
				list = new ArrayList<Map<String,Object>>();
				for(Article article : articleList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("article_id", article.getArticle_id());
					map.put("user_id", article.getUser_id());
					map.put("article_name", article.getArticle_name());
					map.put("article_content", article.getArticle_content());
					map.put("article_date", article.getArticle_date());
					map.put("article_praise", article.getArticle_praise());
					map.put("article_collect", article.getArticle_collect());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 分页显示文章所需信息
	 * @param articlePresentPage 当前页
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getPageArticleInfo(Integer articlePresentPage) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//文章分页
		Long articleCount = articleDaoImpl.queryCountArticle();
		PageUtil<Article> pu = new PageUtil<Article>();
		pu.setCountRow(articleCount.intValue());
		pu.setCurrentPage(articlePresentPage);
		int articleStartRow = pu.getStartRow();
		int articlePageSize = pu.getPageSize();
		List<Article> pageArticle = articleDaoImpl.queryPageArticle(articleStartRow, articlePageSize);
		if(pageArticle != null) {
			for(Article article : pageArticle) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("article_id", article.getArticle_id());
				map.put("user_id", article.getUser_id());
				map.put("article_name", article.getArticle_name());
				map.put("article_collect", article.getArticle_collect());
				map.put("article_praise", article.getArticle_praise());
				//获得该文章的第一张图片
				Integer article_id = article.getArticle_id();
				List<ArticleImg> currenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(article_id);
				ArticleImg firstImg  = currenArticleImg.get(0);
				map.put("image_id", firstImg.getImage_id());
				map.put("image_url", firstImg.getImage_url());
				//获取该文章评论数量
				Long commCount = commentDaoImpl.queryCommCount(article_id);
				map.put("commCount", commCount);
				//获得用户头像与市区地址
				
				list.add(map);
			}
		} 
		return list;
	}
	
	/**
	 * 一篇文章所有信息
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> getOneArticleInfo(Integer article_id) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Article oneArticle = articleDaoImpl.queryArticleById(article_id);
		if(oneArticle != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("article_id", oneArticle.getArticle_id());
			map.put("user_id", oneArticle.getUser_id());
			map.put("article_name", oneArticle.getArticle_name());
			map.put("article_content", oneArticle.getArticle_content());
			map.put("article_date", oneArticle.getArticle_date());
			map.put("article_collect", oneArticle.getArticle_collect());
			map.put("article_praise", oneArticle.getArticle_praise());
			//获得该文章所有图片
			List<ArticleImg> currenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(article_id);
			for(ArticleImg articleImg : currenArticleImg) {
				map.put("image_id", articleImg.getImage_id());
				map.put("image_url", articleImg.getImage_url());
			}
			//获得该文章评论数量
			Long commCount = commentDaoImpl.queryCommCount(article_id);
			map.put("commCount", commCount);
			//获得该文章所有评论
			List<Comment> currenComment = commentDaoImpl.queryAllComment(article_id);
			for(Comment comment : currenComment) {
				map.put("comment_id", comment.getComment_id());
				map.put("comment_content", comment.getComment_content());
				map.put("replier_id", comment.getReplier_id());
			}
			//获得用户头像与昵称
			
			list.add(map);
			return list;
		}
		return null;
	}
	
	//分页显示评论
	public List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//评论分页
		Long commCount = commentDaoImpl.queryCommCount(article_id);
		PageUtil<Comment> pu = new PageUtil<Comment>();
		pu.setCountRow(commCount.intValue());
		pu.setCurrentPage(commPresentPage);
		int commStartRow = pu.getStartRow();
		int commPageSize = pu.getPageSize();
		List<Comment> pageComm = commentDaoImpl.queryPageComment(article_id, commStartRow, commPageSize);
		if(pageComm != null) {
			for(Comment comment : pageComm) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("comment_id", comment.getComment_id());
				map.put("article_id", comment.getArticle_id());
				map.put("user_id", comment.getUser_id());
				map.put("comment_content", comment.getComment_content());
				map.put("replier_id", comment.getReplier_id());
				//获得该评论的回复数
				Integer comment_id = comment.getComment_id();
				Long replierCount = commentDaoImpl.queryReplierCount(comment_id);
				map.put("replierCount", replierCount);
				//获得该评论赞数量
				Long praiseCount = commentDaoImpl.queryPraiseCount(comment_id);
				map.put("praiseCount", praiseCount);
				list.add(map);
			}
		}
		return list;
	}
}
