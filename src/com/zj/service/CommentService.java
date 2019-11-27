package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.realize.CommentDao;
import com.zj.entity.Comment;
import com.zj.service.imp.CommentServiceImpl;
/**
 * 评论服务层
 */
public class CommentService implements CommentServiceImpl{

	private CommentDaoImpl commenDaoImpl = new CommentDao();
	//将所有的评论打包成list返回

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
}
