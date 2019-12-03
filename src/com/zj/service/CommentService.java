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
 * @author lijia
 *评论服务层
 */
public class CommentService implements CommentServiceImpl{

	private CommentDaoImpl commenDaoImpl = new CommentDao();
	private ArticleDaoImpl articleDaoImpl = new ArticleDao();
	private UserDaoImpl  userDaoImpl = new UserDao(); 
	private Logger log = Logger.getLogger(HouseService.class);
	/**
	 * 将所有的评论打包成list返回
	 */
	public List<Map<String, Object>> getAllComment(){
		List<Map<String, Object>> list = null;
		try {
			List<Comment> commentList = commenDaoImpl.getAllComment();
			if(commentList != null){
				list = new ArrayList<Map<String,Object>>();
				for(Comment comment : commentList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("comment_id", comment.getComment_id());
					map.put("article_id", comment.getArticle_id());
					//获取文章名
					Integer article_id = comment.getArticle_id();
					Article article = articleDaoImpl.queryArticleById(article_id);
					map.put("article_name", article.getArticle_name());
					map.put("user_id", comment.getUser_id());
					//获取用户名
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
	/**
	 * 添加评论
	 */
	public Integer addComm(Map<String, Object> addCommInfo) {
		Comment comment = new Comment();
		if(addCommInfo.get("user_id") != null)
			comment.setUser_id(Integer.valueOf(addCommInfo.get("user_id").toString()));
		if(addCommInfo.get("article_id") != null)
			comment.setArticle_id(Integer.valueOf(addCommInfo.get("article_id").toString()));
		if(addCommInfo.get("replier_id") != null)
			comment.setReplier_id(Integer.valueOf(addCommInfo.get("replier_id").toString()));
		if(addCommInfo.get("comment_content") != null)
			comment.setComment_content(addCommInfo.get("comment_content").toString());
		try {
			System.out.println(comment);
			if(commenDaoImpl.addComment(comment) > 0) {
				log.info("评论内容插入成功！");
			} else {
				log.error("评论插入失败！");
				return -1;
			}
		} catch (SQLException e) {
			log.error("评论插入异常！");
			return -1;
		}
		return 1;
	}
}
