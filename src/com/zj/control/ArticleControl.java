package com.zj.control;

import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.dao.impl.ArticleImgDaoImpl;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.realize.ArticleDao;
import com.zj.dao.realize.ArticleImgDao;
import com.zj.dao.realize.CommentDao;
import com.zj.entity.Article;
import com.zj.entity.ArticleImg;
import com.zj.entity.Comment;

public class ArticleControl {
	private ArticleDaoImpl articleDaoImpl = new ArticleDao();
	private ArticleImgDaoImpl articleImgDaoImpl = new ArticleImgDao();
	private CommentDaoImpl commentDaoImpl = new CommentDao();
	/**
	 * 添加文章
	 * @throws SQLException 
	 */
	public int addArticle(Integer user_id, String article_name,
			String article_content) throws SQLException {
		return articleDaoImpl.addArticle(user_id, article_name, article_content);
	}

	/**
	 * 通过id删除文章
	 * @throws SQLException 
	 */
	public int deleteArticleById(Integer article_id) throws SQLException {
		return articleDaoImpl.deleteArticleById(article_id);
	}

	/**
	 * 修改文章
	 * @throws SQLException 
	 */
	public int updateArticle(Integer article_id, String article_name,
			String article_content) throws SQLException {
		return articleDaoImpl.updateArticle(article_id, article_name, article_content);
	}

	/**
	 * 通过id查询文章
	 * @throws SQLException 
	 */
	public Article queryArticleById(Integer article_id) throws SQLException {
		return articleDaoImpl.queryArticleById(article_id);
	}

	/**
	 * 分页查询所有文章
	 * @throws SQLException 
	 */
	public List<Article> queryPageArticle(int startRow,int pageSize) throws SQLException {
		return articleDaoImpl.queryPageArticle(startRow, pageSize);
	}

	/**
	 * 查询所有文章数量
	 * @throws SQLException 
	 */
	public Long queryCountArticle() throws SQLException {
		return articleDaoImpl.queryCountArticle();
	}

	/**
	 * 更新赞数量
	 * @throws SQLException 
	 */
	public int updateArticle_praiset(Integer article_praise,Integer article_id) throws SQLException {
		return articleDaoImpl.updateArticle_praiset(article_praise, article_id);
	}

	/**
	 * 更新收藏数量
	 * @throws SQLException 
	 */
	public int updateArticle_collect(Integer article_id,Integer article_collect) throws SQLException {
		return articleDaoImpl.updateArticle_collect(article_id, article_collect);
	}
	/**
	 * 给文章添加图片
	 * @throws SQLException 
	 */
	public int addArticleImg(Integer article_id, String image_url) throws SQLException {
		return articleImgDaoImpl.addArticleImg(article_id, image_url);
	}

	/**
	 * 通过id删除文章图片
	 * @throws SQLException 
	 */
	public int deleteArticleImg(Integer image_id) throws SQLException {
		return articleImgDaoImpl.deleteArticleImg(image_id);
	}

	/**
	 * 通过id查询文章图片
	 * @throws SQLException 
	 */
	public ArticleImg queryArticleImgById(Integer image_id) throws SQLException {
		return articleImgDaoImpl.queryArticleImgById(image_id);
	}

	/**
	 * 通过文章id查询一篇文章的图片
	 * @throws SQLException 
	 */
	public List<ArticleImg> queryArticleImgByArticleId(Integer article_id) throws SQLException {
		return articleImgDaoImpl.queryArticleImgByArticleId(article_id);
	}

	/**
	 * 查询所有文章图片
	 * @throws SQLException 
	 */
	public List<ArticleImg> queryArticleImg() throws SQLException {
		return articleImgDaoImpl.queryArticleImg();
	}
	/**
	 * 给文章添加评论
	 * @throws SQLException 
	 */
	public int addComment(Integer article_id, Integer user_id,
			String comment_content, String replier_id) throws SQLException {
		return commentDaoImpl.addComment(article_id, user_id, comment_content, replier_id);
	}

	/**
	 * 通过id删除文章评论
	 * @throws SQLException 
	 */
	public int deleteComment(Integer comment_id) throws SQLException {
		return commentDaoImpl.deleteComment(comment_id);
	}

	/**
	 * 通过id查询评论
	 * @throws SQLException 
	 */
	public Comment queryCommentById(Integer comment_id) throws SQLException {
		return commentDaoImpl.queryCommentById(comment_id);
	}

	/**
	 * 分页查询一篇文章评论
	 * @throws SQLException 
	 */
	public List<Comment> queryPageComment(Integer article_id, int startRow,
			int pageSize) throws SQLException {
		return commentDaoImpl.queryPageComment(article_id, startRow, pageSize);
	}
	/**
	 * 不分页查询一篇文章所有评论
	 * @throws SQLException 
	 */
	public List<Comment> queryAllComment(Integer article_id) throws SQLException {
		return commentDaoImpl.queryAllComment(article_id);
	}
	/**
	 * 通过id查询一篇文章评论数量
	 */
	public Long queryCommCount(Integer article_id) throws SQLException {
		return commentDaoImpl.queryCommCount(article_id);
	}
	/**
	 * 查看一条评论的回复数量
	 * @throws SQLException 
	 */
	public Long queryReplierCount(Integer comment_id) throws SQLException {
		return commentDaoImpl.queryReplierCount(comment_id);
	}
	/**
	 * 查询一条评论的赞数量
	 * @throws SQLException 
	 */
	public Long queryPraiseCount(Integer comment_id) throws SQLException {
		return commentDaoImpl.queryPraiseCount(comment_id);
	}
}
