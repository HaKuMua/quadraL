package com.zj.control;

import java.sql.SQLException;
import java.util.List;

import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.realize.CommentDao;
import com.zj.entity.Comment;
import com.zj.entity.Landlord;

public class CommentControl {

	private CommentDaoImpl commenDaoImpl = new CommentDao();
	
	public List<Comment> getAllComment() throws SQLException {
		return commenDaoImpl.getAllCommen();
	}
}
