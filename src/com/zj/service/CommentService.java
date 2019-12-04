package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.util.PageUtil;

import com.zj.dao.ArticleDao;
import com.zj.dao.CommentDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.Article;
import com.zj.entity.Comment;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.User;
import com.zj.service.impl.CommentServiceImpl;
/**
 * 
 * @author lijia
 *评论服务层
 */
public class CommentService implements CommentServiceImpl{

	private CommentDaoImpl commenDaoImpl = new CommentDao();
	private ArticleDaoImpl articleDaoImpl = new ArticleDao();
	private UserDaoImpl  userDaoImpl = new UserDao(); 
	/**
	 * 将所有的评论打包成list返回
	 * @throws SQLException 
	 */
	public Map<String, Object> getAllComment(Integer commentPresentPage,Integer pageSize) throws SQLException{
					//获取文章名
					//获取用户名
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 订单分页
		Long orderCount = commenDaoImpl.queryCountComment();
		PageUtil<Comment> pu = new PageUtil<Comment>();
		pu.setCountRow(orderCount.intValue());
		pu.setCurrentPage(commentPresentPage);
		pu.setPageSize(pageSize);
		
		int commentStartRow = pu.getStartRow();
		int commentPageSize = pu.getPageSize();
		
		List<Comment> pageComment = commenDaoImpl.queryCommentPage(commentStartRow, commentPageSize);
		Article article = new Article();
		String article_name = article.getArticle_name();
		List<Article> pageArticle = articleDaoImpl.getAllArticle();
		List<User> pageUser = userDaoImpl.getAllUserInfo();
		
		
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		dataMap.put("comment", pageComment);
		dataMap.put("article", pageArticle);
		dataMap.put("user", pageUser);
		
		pu.setMap(dataMap);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageUtil", pu);
		return map;
	}
	//分页显示一篇文章评论
			public List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id) throws SQLException{
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				//评论分页
				Long commCount = commenDaoImpl.queryCommCount(article_id);
				PageUtil<Comment> pu = new PageUtil<Comment>();
				pu.setCountRow(commCount.intValue());
				pu.setCurrentPage(commPresentPage);
				int commStartRow = pu.getStartRow();
				int commPageSize = pu.getPageSize();
				List<Comment> pageComm = commenDaoImpl.queryPageComment(article_id, commStartRow, commPageSize);
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
						Long replierCount = commenDaoImpl.queryReplierCount(comment_id);
						map.put("replierCount", replierCount);
						//获得该评论赞数量
						map.put("praiseCount", comment.getComment_praise());
						list.add(map);
					}
				}
				return list;
			}
}
