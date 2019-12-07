package com.zj.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import cn.com.util.BaseServlet;
import cn.com.util.FileLoadServletUtil;
import cn.com.util.PageUtil;

import com.alibaba.fastjson.JSON;
import com.zj.service.ArticleService;
import com.zj.service.impl.ArticleServiceImpl;
import com.zj.service.impl.CommentServiceImpl;
/**
 * 文章
 * 
 * @author ml
 * 
 */
public class ArticleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ArticleServiceImpl articleService = new ArticleService();
	private Logger log = Logger.getLogger(HouseServlet.class);
	public String callback;
	public String articleMap;
	private Integer articlePresentPage;
	private Integer article_id;
	private Integer commPresentPage;
	private String article_content;

	// 返回所有的文章信息
	public String map;

	// 当前页数
	private Integer page;
	// 总页数
	private Integer countPage;
	// 每页条数
	private Integer limit;
	//所要删除的条目ID
	private Integer deleteId;
	
	
//	/**
//	 * 根据文章Id删除文章
//	 * @param request
//	 * @param response
//	 * @throws SQLException 
//	 * @throws IOException 
//	 */
//	public void deleteArticle(HttpServletRequest request,
//			HttpServletResponse response) throws SQLException, IOException{
//		Integer article_id = deleteId;
//		int isDelete = articleService.deleteArticleById(article_id);
//		JSONObject obj = new JSONObject(isDelete);
//		System.out.println("obj:" + obj);
//		response.getWriter().print(callback + "(" + obj + ")");
//	}
	/**
	 *  返回所有的文章信息
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 */
	public void getAllArticle(HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {
		Map<String, Object> map = articleService.getAllArticle(limit, page);
		JSONObject obj = new JSONObject(map);
		System.out.println("obj:" + obj);
		response.getWriter().print(callback + "(" + obj + ")");
	}

	/**
	 * 分页显示文章所需信息
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	
	public void getPageArticleInfo(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		PageUtil<Map<String, Object>> pageList = articleService
				.getPageArticleInfo(articlePresentPage);
		JSONObject json = new JSONObject(pageList);
		response.getWriter().print(json);
	}	
	
		

	/**
	 * 一篇文章所有信息 需要article_id
	 * @throws SQLException
	 * @throws IOException
	 */
	public void getOneArticleInfo(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		Map<String, Object> articleInfo = articleService
				.getOneArticleInfo(article_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oneArticleInfo", articleInfo);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(callback + "(" + json + ")");
	}

	/**
	 * 分页显示评论
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public void getPageCommInfo(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		Integer article_id = Integer.valueOf(JSON.parse("article_id")
				.toString());
		Integer commPresentPage = Integer.valueOf(JSON.parse("commPresentPage")
				.toString());
		List<Map<String, Object>> list = articleService.getPageCommInfo(
				commPresentPage, article_id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageCommentInfo", list);
		JSONObject json = new JSONObject(map);
		response.getWriter().print(json);
	}



	/**
	 * 
	 * 添加文章
	 * 
	 * @param request
	 * @param response
	 * @throws SQLException
	 * @throws IOException
	 */
	public void addArticleInfo(HttpServletRequest request,
			HttpServletResponse response) throws SQLException, IOException {
		System.out.println(articleMap);
		@SuppressWarnings("unchecked")
		Map<String, Object> articleInfo = (Map<String, Object>) JSON
				.parse(articleMap);
		articleInfo.put("article_content", article_content);
		log.info(articleInfo);
		Map<String, Object> hint = new HashMap<String, Object>();
		Integer articleNum = articleService.addArticleInfo(articleInfo);
		if (articleNum > 0) {
			hint.put("msg", "发布成功！");
			hint.put("code", 1);
		} else {
			hint.put("msg", "发布失败！");
			hint.put("code", -1);
		}
		JSONObject json = new JSONObject(hint);
		response.getWriter().print(callback + "(" + json + ")");
	}

	/**
	 * 上传文章图片到本地并返回路径
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws FileUploadException
	 */
	public void uploadImg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			FileUploadException {
		// 图片上传并且返回保存的路径
		String url = FileLoadServletUtil.upload(request, response,
				"D:/MyEclipse2015work/quadraL/WebRoot/image/articleImg/");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", "0");
		HashMap<String, Object> data = new HashMap<String, Object>();
		String dataBaseUrl = url.substring(37);
		data.put("src", dataBaseUrl);
		map.put("data", data);
		JSONObject obj = new JSONObject(map);
		// 如果上传成功返回1
		response.getWriter().print(obj);
	}

	/**
	 * 删除一篇文章内容
	 */
	public void deleteArticleInfo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> articleInfo = (Map<String, Object>) JSON
				.parse(articleMap);
		Integer article_id = Integer.valueOf(articleInfo.get("article_id")
				.toString());
		Integer num = articleService.deleteArticleInfo(article_id);
		Map<String, Object> hint = new HashMap<String, Object>();
		if (num > 0) {
			hint.put("msg", "文章删除成功");
			hint.put("code", 1);
		} else {
			hint.put("msg", "文章删除失败");
			hint.put("code", -1);
			
		}
		JSONObject json = new JSONObject(hint);
		response.getWriter().print(callback + "(" + json + ")");
	}
	

	/**
	 * 返回一个用户所有的文章信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getUserAllArticle(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> articleInfo = (Map<String, Object>) JSON
				.parse(articleMap);
		Integer user_id = Integer
				.valueOf(articleInfo.get("user_id").toString().trim());
		Integer page = Integer
				.valueOf(articleInfo.get("page").toString());
		PageUtil pageUtil = articleService
				.getAllArticleByUser(user_id,page);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageUtil);
		System.out.println(pageUtil);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print( obj);
	}

	/**
	 * 点赞
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void givePraiset(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		@SuppressWarnings("unchecked")
		Map<String, Object> articleInfo = (Map<String, Object>) JSON
				.parse(articleMap);
		Integer article_id = Integer.valueOf(articleInfo.get("article_id")
				.toString());
		Integer article_praise = Integer.valueOf(articleInfo.get(
				"article_praise").toString());
		Integer count = articleService
				.updatePraiset(article_praise, article_id);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("article_praise", count);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(obj);
	}
	/**
	 * 删除文章
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void deleteArticle(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer count = articleService.deleteArticleInfo(article_id);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("hint", count);
		JSONObject obj = new JSONObject(map);
		response.getWriter().print(obj);
	}

}
