package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.com.util.PageUtil;

import com.zj.dao.ArticleDao;
import com.zj.dao.CommentDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.Article;
import com.zj.entity.Comment;
import com.zj.entity.User;
import com.zj.service.impl.CommentServiceImpl;

/**
 * 
 * @author lijia 评论服务层
 */
public class CommentService implements CommentServiceImpl {

	private CommentDaoImpl commenDaoImpl = new CommentDao();
	private ArticleDaoImpl articleDaoImpl = new ArticleDao();
	private UserDaoImpl  userDaoImpl = new UserDao(); 
	private Logger log = Logger.getLogger(HouseService.class);
	/**
	 * 将所有的评论打包成list返回
	 */
	public List<Map<String, Object>> getAllComment() {
		List<Map<String, Object>> list = null;
		try {
			List<Comment> commentList = commenDaoImpl.getAllComment();
			if (commentList != null) {
				list = new ArrayList<Map<String, Object>>();
				for (Comment comment : commentList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("comment_id", comment.getComment_id());
					map.put("article_id", comment.getArticle_id());
					// 获取文章名
					Integer article_id = comment.getArticle_id();
					Article article = articleDaoImpl
							.queryArticleById(article_id);
					map.put("article_name", article.getArticle_name());
					map.put("user_id", comment.getUser_id());
					// 获取用户名
					Integer user_id = comment.getUser_id();
					User user = userDaoImpl.getUserInfoById(user_id);
					map.put("user_name", user.getUser_name());
					map.put("comment_content", comment.getComment_content());
					map.put("replier_id", comment.getReplier_id());
					map.put("comment_date", comment.getComment_date());
					map.put("comment_praise", comment.getComment_praise());
					list.add(map);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	// 分页显示一篇文章评论
	public List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,
			Integer article_id) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 评论分页
		Long commCount = commenDaoImpl.queryCommCount(article_id);
		PageUtil<Comment> pu = new PageUtil<Comment>();
		pu.setCountRow(commCount.intValue());
		pu.setCurrentPage(commPresentPage);
		int commStartRow = pu.getStartRow();
		int commPageSize = pu.getPageSize();
		List<Comment> pageComm = commenDaoImpl.queryPageComment(article_id,
				commStartRow, commPageSize);
		if (pageComm != null) {
			for (Comment comment : pageComm) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("comment_id", comment.getComment_id());
				map.put("article_id", comment.getArticle_id());
				map.put("user_id", comment.getUser_id());
				map.put("comment_content", comment.getComment_content());
				map.put("replier_id", comment.getReplier_id());
				// 获得该评论的回复数
				Integer comment_id = comment.getComment_id();
				Long replierCount = commenDaoImpl.queryReplierCount(comment_id);
				map.put("replierCount", replierCount);
				// 获得该评论赞数量
				map.put("praiseCount", comment.getComment_praise());
				list.add(map);
			}
		}
		return list;
	}

	/**
	 * 增加一条评论
	 */
	public Map<String, Object> addComment(Map<String, Object> info) {
		Comment comment = new Comment();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Comment> list = new ArrayList<Comment>();
		//给文章实体类set值进去
		if(info.get("user_id") != null)
			comment.setUser_id(Integer.valueOf(info.get("user_id").toString()));
		if(info.get("article_name") != null)
			comment.setArticle_id(new Integer(info.get("article_id").toString()));
		if(info.get("article_content") != null)
			comment.setComment_content(info.get("comment_content").toString());
		if(info.get("house_id") != null)
			comment.setReplier_id(Integer.valueOf(info.get("replier_id").toString()));
		int cout=-1;
		try {
			 cout = commenDaoImpl.addComment(comment);
			 if(cout > 0) {
				 list = commenDaoImpl.queryAllComment(comment.getArticle_id());
				 if(list == null) {
					 map.put("msg", "评论失败！");
				 }else {
					 map.put("commInfo", list);
					 map.put("msg", "评论成功！");
				 }
			 }else {
				 map.put("msg", "评论失败！");
				 return map;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 删除一条评论
	 */
	public Integer deleteComm(Integer comment_id) {
//		//判断该评论是否有回复，有则删除
//		try {
//			Long replierCount = commenDaoImpl.queryReplierCount(comment_id);
//			if(replierCount > 1) {
//				Integer user_id = commenDaoImpl.qu
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}
	
}
