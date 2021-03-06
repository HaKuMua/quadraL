﻿package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;








import cn.com.util.PageUtil;
import cn.com.util.RemoveHtmlTagUtil;

import com.zj.dao.ArticleDao;
import com.zj.dao.ArticleImgDao;
import com.zj.dao.CommentDao;
import com.zj.dao.GrogshopOrderDao;
import com.zj.dao.HouseDao;
import com.zj.dao.HouseImgDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.dao.impl.ArticleImgDaoImpl;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.impl.GrogshopOrderDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.Article;
import com.zj.entity.ArticleImg;
import com.zj.entity.Comment;
import com.zj.entity.GrogshopOrder;
import com.zj.entity.House;
import com.zj.entity.HouseImg;
import com.zj.entity.User;
import com.zj.service.impl.ArticleServiceImpl;

/**
 * 文章服务层
 * @author liyifeng
 *
 */
public class ArticleService implements ArticleServiceImpl{
	private ArticleDaoImpl articleDaoImpl = new ArticleDao();
	private ArticleImgDaoImpl articleImgDaoImpl = new ArticleImgDao();
	private CommentDaoImpl commentDaoImpl = new CommentDao();
	private UserDaoImpl userDaoImpl = new UserDao();
	private HouseDaoImpl houseDaoImpl = new HouseDao();
	private HouseImgDaoImpl houseImgDaoImpl = new HouseImgDao();
	private GrogshopOrderDaoImpl orderDaoImpl = new GrogshopOrderDao();
	private Logger log = Logger.getLogger(HouseService.class);
	
	
	public List<Map<String, Object>> getAllArticle(){
		List<Map<String, Object>> list = null;
		try {
			List<Article> articleList = articleDaoImpl.getAllArticle();
			if(articleList != null){
				list = new ArrayList<Map<String,Object>>();
				for(Article article : articleList){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("article_id", article.getArticle_id());
					//获取对应的文章图片
					Integer article_id = article.getArticle_id();
					List<ArticleImg> articleImg = articleImgDaoImpl.queryArticleImgByArticleId(article_id);
					map.put("article_img", articleImg);
					map.put("user_id", article.getUser_id());
					Integer user_id = article.getUser_id();
					User user = userDaoImpl.getUserInfoById(user_id);
					map.put("user_name", user.getUser_name());
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
	 * 获得所有文章
	 * @return
	 * @throws SQLException 
	 */
	public Map<String, Object> getAllArticle(Integer limit,Integer page) throws SQLException{
		Map<String,Object> map = new HashMap<String,Object>();
		List<Article> articleList = null;
		try {
			articleList = articleDaoImpl.queryArticlePage((page-1)*10, limit);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for(Article article : articleList){
				Map<String, Object> articleMap = new HashMap<String, Object>();
				articleMap.put("article_id", article.getArticle_id());
				articleMap.put("user_name", userDaoImpl.getUserInfoById(article.getUser_id()).getUser_name());
				articleMap.put("article_name", article.getArticle_name());
				articleMap.put("article_content", article.getArticle_content());
				articleMap.put("article_date", article.getArticle_date());
				articleMap.put("article_praise", article.getArticle_praise());
				articleMap.put("article_collect", article.getArticle_collect());
				articleMap.put("house_name", houseDaoImpl.getHouseInfoByID(article.getHouse_id()).getHouse_name());
				list.add(articleMap);
			}
			map.put("data", list);
			map.put("count", Integer.valueOf(articleDaoImpl.queryCountArticle().toString()));
			map.put("msg", "");
			map.put("code", 0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 分页显示文章所需信息
	 * @param articlePresentPage 当前页
	 * @throws SQLException
	 */
	public PageUtil getPageArticleInfo(Integer presentPage) {
		System.out.println(1111);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//文章分页
		Long articleCount = 1l;
		try {
			articleCount = articleDaoImpl.queryCountArticle();
		} catch (SQLException e) {
			log.error("查询错误");
			e.printStackTrace();
		}
		PageUtil<Map<String, Object>> pu = new PageUtil<Map<String, Object>>();
		pu.setCountRow(articleCount.intValue());
		//设置当前页数
		pu.setCurrentPage(presentPage);
		//设置一页显示多少条
		pu.setPageSize(12);
		int articleStartRow = pu.getStartRow();
		int articlePageSize = pu.getPageSize();
		List<Article> pageArticle = null;
		try {
			System.out.println("articleStartRow:"+articleStartRow);
			System.out.println("articlePageSize:"+articlePageSize);
			pageArticle = articleDaoImpl.queryPageArticle(articleStartRow, articlePageSize);
		} catch (SQLException e) {
			log.error("查询错误");
			e.printStackTrace();
		}
		if(pageArticle != null) {
			for(Article article : pageArticle) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("article_id", article.getArticle_id());
				map.put("user_id", article.getUser_id());
				map.put("article_name", article.getArticle_name());
				map.put("article_content", article.getArticle_content());
				map.put("article_collect", article.getArticle_collect());
				map.put("article_praise", article.getArticle_praise());
				//获得该文章的第一张图片
				List<ArticleImg> currenArticleImg = null;
				try {
					currenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(article.getArticle_id());
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				
				if(!currenArticleImg.isEmpty()) {
					System.out.println("--"+currenArticleImg);
					ArticleImg firstImg  = currenArticleImg.get(0);
					map.put("image_id", firstImg.getImage_id());
					map.put("image_url", firstImg.getImage_url());
				}else {
					map.put("image_id", null);
					map.put("image_url", null);
				}
				//获取该文章评论数量
				Long commCount = 0l;
				try {
					commCount = commentDaoImpl.queryCommCount(article.getArticle_id());
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				map.put("commCount", commCount);
				//获得用户头像
				Integer user_id = article.getUser_id();
				User user = null;
				try {
					user = userDaoImpl.getUserInfoById(user_id);
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				if(user != null) {
					String userHeadUrl = user.getUser_headimg_url();
					map.put("user_headimg_url", userHeadUrl);
				}
				System.out.println(map);
				list.add(map);
				System.out.println("list:"+list);
			}
		} 
		pu.setList(list);
		return pu;
	}
	
	/**
	 * 一篇文章所有信息
	 * @throws SQLException 
	 */
	public Map<String, Object> getOneArticleInfo(Integer article_id) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//获得该文章
		Article oneArticle = null;
		try {
			oneArticle = articleDaoImpl.queryArticleById(article_id);
		} catch (SQLException e) {
			map.put("msg", "查询错误");
			e.printStackTrace();
		}
		if(oneArticle != null) {
			map.put("article_id", oneArticle.getArticle_id());
			map.put("user_id", oneArticle.getUser_id());
			map.put("article_name", oneArticle.getArticle_name());
			map.put("article_content", oneArticle.getArticle_content());
			map.put("article_date", oneArticle.getArticle_date());
			map.put("article_collect", oneArticle.getArticle_collect());
			map.put("article_praise", oneArticle.getArticle_praise());
			map.put("house_id",oneArticle.getHouse_id());
			List<String> imgMap = new ArrayList<String>();
			//获得该文章所有图片
			List<ArticleImg> currenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(article_id);
			for(ArticleImg articleImg : currenArticleImg) {
				imgMap.add( articleImg.getImage_url());
			}
			map.put("article_images", imgMap);
			//获得该文章评论数量
			Long commCount = commentDaoImpl.queryCommCount(article_id);
			map.put("commNum", commCount);
			//获得用户头像与昵称
			Integer user_id = oneArticle.getUser_id();
			User user = userDaoImpl.getUserInfoById(user_id);
			String userHeadUrl = user.getUser_headimg_url();
			String user_name = user.getUser_name();
			map.put("user_headimg_url", userHeadUrl);
			map.put("user_name", user_name);
			Map<String, Object> houseMap = new HashMap<String, Object>();
			//获取房子图片标题房子类型(house_type)
			Integer house_id = oneArticle.getHouse_id();
			House house= houseDaoImpl.getHouseInfoByID(house_id);
			houseMap.put("house_name", house.getHouse_name());
			houseMap.put("house_type", house.getHouse_type());
			houseMap.put("house_price", house.getHouse_price());
			//第一张图片
			List<HouseImg> currenHouseImg = houseImgDaoImpl.getHouseImgByHouseID(house_id);
			HouseImg firstImg  = currenHouseImg.get(0);
			houseMap.put("house_image_id", firstImg.getHouse_img_id());
			houseMap.put("house_image_url", firstImg.getHouse_img_url());
			map.put("house", houseMap);
			//获取相关文章
			String keyWord = oneArticle.getArticle_name().substring(0,2);
			List<Article> relatedArticles = articleDaoImpl.queryFuzzyQuery(keyWord);
			List<Map<String, Object>> articleInfo =  new ArrayList<Map<String,Object>>();
			Map<String, Object> article = new HashMap<String, Object>();
			for(Article relatedArticle : relatedArticles) {
				if(relatedArticle.getArticle_id().equals(article_id)) {
					continue;
				}
				//一篇相关文章
				article.put("article_id", relatedArticle.getArticle_id());
				article.put("article_name", relatedArticle.getArticle_name());
				article.put("article_praise", relatedArticle.getArticle_praise());
				//获得相关文章文章的第一张图片
				Integer relatedArticleArticle_id = relatedArticle.getArticle_id();
				List<ArticleImg> relatedArticleCurrenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(relatedArticleArticle_id);
				ArticleImg relatedArticleFirstImg  = relatedArticleCurrenArticleImg.get(0);
				article.put("article_img", relatedArticleFirstImg.getImage_url());
				//获取相关文章文章评论数量
				Long relatedArticleCommCount = commentDaoImpl.queryCommCount(relatedArticleArticle_id);
				article.put("article_collect", relatedArticleCommCount);
				//获得相关文章用户头像
				Integer relatedArticleUser_id = relatedArticle.getUser_id();
				User relatedArticleUser = userDaoImpl.getUserInfoById(relatedArticleUser_id);
				String relatedArticleUserHeadUrl = relatedArticleUser.getUser_headimg_url();
				article.put("user_img", relatedArticleUserHeadUrl);
			}
			articleInfo.add(article);
			map.put("articleInfo", articleInfo);
			return map;
		}
		return map;
	}
	/**
	* 分页显示评论
	 */
	public List<Map<String, Object>> getPageCommInfo(Integer commPresentPage,Integer article_id){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		//评论分页
		Long commCount = 1l;
		try {
			commCount = commentDaoImpl.queryCommCount(article_id);
		} catch (SQLException e) {
			map.put("msg", "查询错误");
			e.printStackTrace();
		}
		PageUtil<Comment> pu = new PageUtil<Comment>();
		pu.setCountRow(commCount.intValue());
		pu.setCurrentPage(commPresentPage);
		int commStartRow = pu.getStartRow();
		int commPageSize = pu.getPageSize();
		List<Comment> pageComm = null;
		try {
			pageComm = commentDaoImpl.queryPageComment(article_id, commStartRow, commPageSize);
		} catch (SQLException e) {
			map.put("msg", "查询错误");
			e.printStackTrace();
		}
		if(pageComm != null) {
			for(Comment comment : pageComm) {
				map.put("comment_id", comment.getComment_id());
				map.put("article_id", comment.getArticle_id());
				map.put("user_id", comment.getUser_id());
				map.put("comment_content", comment.getComment_content());
				map.put("replier_id", comment.getReplier_id());
				//获得该评论的回复数
				Integer comment_id = comment.getComment_id();
				Long replierCount = 0l;
				try {
					replierCount = commentDaoImpl.queryReplierCount(comment_id);
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				map.put("replierCount", replierCount);
				//获得该评论赞数量
				Long praiseCount = 0l;
				try {
					praiseCount = commentDaoImpl.queryPraiseCount(comment_id);
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				map.put("praiseCount", praiseCount);
			}
		}
		list.add(map);
		return list;
	}

	/**
	 * 添加文章
	 */
	public Integer addArticleInfo(Map<String, Object> addArticleInfo) {
		Article article = new Article();
	
		//给文章实体类set值进去
		if(addArticleInfo.get("user_id") != null)
			article.setUser_id(Integer.valueOf(addArticleInfo.get("user_id").toString()));
		if(addArticleInfo.get("article_name") != null)
			article.setArticle_name(addArticleInfo.get("article_name").toString());
		if(addArticleInfo.get("article_content") != null)
			article.setArticle_content(addArticleInfo.get("article_content").toString());
		if(addArticleInfo.get("house_id") != null)
			article.setHouse_id(Integer.valueOf(addArticleInfo.get("house_id").toString()));
		String articleImgList = null;
		if(addArticleInfo.get("allArticleImg") != null)
			articleImgList = addArticleInfo.get("allArticleImg").toString();
		//添加文章内容
		try {
			//改变订单状态
			System.out.println(addArticleInfo.get("order_id").toString());
			int count = orderDaoImpl.updateUserStatus(addArticleInfo.get("order_id").toString(), 3);
			System.out.println(count);
			if (articleDaoImpl.addArticle(article) > 0)
				log.info("文章内容插入成功！");
				//给文章添加一组图片
				Integer article_id = articleDaoImpl.queryIdByArticleName(article.getArticle_name());
				System.out.println(article);
				String[] images = articleImgList.split(",");
				for (int i = 0; i < images.length; i++) {
					ArticleImg articleImg = new ArticleImg();
					try {
						articleImg.setArticle_id(article_id);
						articleImg.setImage_url(images[i]);
						if (articleImgDaoImpl.addArticleImg(articleImg) > 0) {
							log.info("图片插入成功！");
						}
					} catch (Exception e) {
						log.error("图片插入异常！");
						return -1;
					}
				}
		} catch (SQLException e) {
			log.error("文章内容插入异常！");
		}
		return 1;		
	}

	/**
	 * 删除文章
	 */
	public Integer deleteArticleInfo(Integer article_id) {
		//删除文章内容、图片、所有评论
		try {
			//判断是否有评论
			Integer articleCommCount = 1;
			Long commCount = commentDaoImpl.queryCommCount(article_id);
			if(commCount > 0) {
				articleCommCount = commentDaoImpl.deleteCommByArticleId(article_id);
			}
			//判断是否有图片
			Integer articleImgCount = 1;
			List<ArticleImg> ImgCount = articleImgDaoImpl.queryArticleImgByArticleId(article_id);
			if(ImgCount != null) {
				articleImgCount = articleImgDaoImpl.deleteImgByArticleId(article_id);
			}
			Integer articleInfoCount = articleDaoImpl.deleteArticleById(article_id);
			if(articleInfoCount != 0 && articleImgCount != 0 &&articleCommCount != 0) {
				//文章删除成功
				log.info("文章删除成功！");
			} else {
				log.info("文章删除失败！");
				return -1;
			}
		} catch (SQLException e) {
			log.error("文章删除异常！");
			return -1;
		}
		return 1;
	}
	/**
	 * 根据文章Id删除文章service
	 */
	public int deleteArticleById(Integer article_id) throws SQLException {
		articleDaoImpl.deleteArticleById(article_id);
		return 1;
	}
	


	/**
	 * 查询一个用户所有文章
	 */
	public PageUtil getAllArticleByUser(Integer user_id,Integer page) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//文章分页
		Long articleCount = 1l;
		try {
			articleCount = articleDaoImpl.queryUserCountArticle(user_id);
		} catch (SQLException e) {
			log.error("查询错误");
			e.printStackTrace();
		}
		PageUtil<Map<String, Object>> pu = new PageUtil<Map<String, Object>>();
		pu.setCountRow(articleCount.intValue());
		//设置当前页数
		pu.setCurrentPage(page);
		//设置一页显示多少条
		pu.setPageSize(12);
		int articleStartRow = pu.getStartRow();
		int articlePageSize = pu.getPageSize();
		List<Article> pageArticle = null;
		try {
			pageArticle = articleDaoImpl.queryArticleByUserId(articleStartRow, articlePageSize,user_id);
		} catch (SQLException e) {
			log.error("查询错误");
			e.printStackTrace();
		}
		if(pageArticle != null) {
			for(Article article : pageArticle) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("article_id", article.getArticle_id());
				map.put("user_id", article.getUser_id());
				map.put("article_name", article.getArticle_name());
				map.put("article_content", article.getArticle_content());
				map.put("article_collect", article.getArticle_collect());
				map.put("article_praise", article.getArticle_praise());
				//获得该文章的第一张图片
				List<ArticleImg> currenArticleImg = null;
				try {
					currenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(article.getArticle_id());
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				
				if(!currenArticleImg.isEmpty()) {
					System.out.println("--"+currenArticleImg);
					ArticleImg firstImg  = currenArticleImg.get(0);
					map.put("image_id", firstImg.getImage_id());
					map.put("image_url", firstImg.getImage_url());
				}else {
					map.put("image_id", null);
					map.put("image_url", null);
				}
				//获取该文章评论数量
				Long commCount = 0l;
				try {
					commCount = commentDaoImpl.queryCommCount(article.getArticle_id());
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				map.put("commCount", commCount);
				User user = null;
				try {
					user = userDaoImpl.getUserInfoById(user_id);
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				if(user != null) {
					String userHeadUrl = user.getUser_headimg_url();
					map.put("user_headimg_url", userHeadUrl);
				}
				list.add(map);
			}
		} 
		pu.setList(list);
		return pu;
	}
	/**
	 * 点赞
	 * @param article_praise 赞数量
	 * @param article_id 文章id
	 * @return 赞数量
	 */
	public Integer updatePraiset(Integer article_praise, Integer article_id) {
		int praiset =article_praise ;
		try {
			int count = articleDaoImpl.updateArticle_praiset(article_praise, article_id);
			praiset = articleDaoImpl.queryArticleById(article_id).getArticle_praise();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return praiset;
	}


}
