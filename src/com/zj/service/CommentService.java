package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.uitl.PageUtil;

import com.zj.dao.CommentDao;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.entity.Comment;
import com.zj.service.imp.CommentServiceImpl;
/**
 * 
 * @author lijia
 *评论服务层
 */
public class CommentService implements CommentServiceImpl{

	private CommentDaoImpl commenDaoImpl = new CommentDao();
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
					map.put("user_id", comment.getUser_id());
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
}
