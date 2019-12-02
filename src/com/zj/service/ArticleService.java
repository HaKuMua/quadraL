package com.zj.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import cn.com.util.PageUtil;

import com.zj.dao.ArticleDao;
import com.zj.dao.ArticleImgDao;
import com.zj.dao.CommentDao;
import com.zj.dao.HouseDao;
import com.zj.dao.HouseImgDao;
import com.zj.dao.UserDao;
import com.zj.dao.impl.ArticleDaoImpl;
import com.zj.dao.impl.ArticleImgDaoImpl;
import com.zj.dao.impl.CommentDaoImpl;
import com.zj.dao.impl.HouseDaoImpl;
import com.zj.dao.impl.HouseImgDaoImpl;
import com.zj.dao.impl.UserDaoImpl;
import com.zj.entity.Article;
import com.zj.entity.ArticleImg;
import com.zj.entity.Comment;
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
	 * 分页显示文章所需信息
	 * @param articlePresentPage 当前页
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getPageArticleInfo(Integer presentPage) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		Integer articleCurrentPage = presentPage;
		//获得当前页
		Integer articlePresentPage = 1;
			try{
				articlePresentPage = articleCurrentPage;
			}catch(Exception e){
				articlePresentPage = 1;
			}	
		//文章分页
		Long articleCount = 1l;
		try {
			articleCount = articleDaoImpl.queryCountArticle();
		} catch (SQLException e) {
			map.put("msg", "查询错误");
			e.printStackTrace();
		}
		PageUtil<Article> pu = new PageUtil<Article>();
		pu.setCountRow(articleCount.intValue());
		pu.setCurrentPage(articlePresentPage);
		int articleStartRow = pu.getStartRow();
		int articlePageSize = pu.getPageSize();
		List<Article> pageArticle = null;
		try {
			pageArticle = articleDaoImpl.queryPageArticle(articleStartRow, articlePageSize);
		} catch (SQLException e) {
			map.put("msg", "查询错误");
			e.printStackTrace();
		}
		if(pageArticle != null) {
			for(Article article : pageArticle) {
				map.put("article_id", article.getArticle_id());
				map.put("user_id", article.getUser_id());
				map.put("article_name", article.getArticle_name());
				map.put("article_content", article.getArticle_content());
				map.put("article_collect", article.getArticle_collect());
				map.put("article_praise", article.getArticle_praise());
				//获得该文章的第一张图片
				Integer article_id = article.getArticle_id();
				List<ArticleImg> currenArticleImg = null;
				try {
					currenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(article_id);
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				if(currenArticleImg != null) {
					ArticleImg firstImg  = currenArticleImg.get(0);
					map.put("image_id", firstImg.getImage_id());
					map.put("image_url", firstImg.getImage_url());
				}
				//获取该文章评论数量
				Long commCount = 0l;
				try {
					commCount = commentDaoImpl.queryCommCount(article_id);
				} catch (SQLException e) {
					map.put("msg", "查询错误");
					e.printStackTrace();
				}
				map.put("commCount", commCount);
				//获得用户头像
				Integer user_id = article.getUser_id();
				User user = null;
				try {
					user = userDaoImpl.queryUserById(user_id);
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
		return list;
	}
	
	/**
	 * 一篇文章所有信息
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> getOneArticleInfo(Integer article_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
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
			//获得该文章所有图片
			List<ArticleImg> currenArticleImg = null;
			try {
				currenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(article_id);
			} catch (SQLException e) {
				map.put("msg", "查询错误");
				e.printStackTrace();
			}
			if(currenArticleImg != null) {
				for(ArticleImg articleImg : currenArticleImg) {
					map.put("image_id", articleImg.getImage_id());
					map.put("image_url", articleImg.getImage_url());
				}
			}
			//获得该文章评论数量
			Long commCount = 0l;
			try {
				commCount = commentDaoImpl.queryCommCount(article_id);
			} catch (SQLException e) {
				map.put("msg", "查询错误");
				e.printStackTrace();
			}
			map.put("commCount", commCount);
			//获得用户头像与昵称
			Integer user_id = oneArticle.getUser_id();
			User user = null;
			try {
				user = userDaoImpl.queryUserById(user_id);
			} catch (SQLException e) {
				map.put("msg", "查询错误");
				e.printStackTrace();
			}
			if(user != null) {
				String userHeadUrl = user.getUser_headimg_url();
				String user_name = user.getUser_name();
				map.put("user_headimg_url", userHeadUrl);
				map.put("user_name", user_name);
			}
			//获取房子图片标题房子类型(house_type)
			Integer house_id = oneArticle.getHouse_id();
			House house = null;
			try {
				house = houseDaoImpl.getHouseInfoByID(house_id);
			} catch (SQLException e) {
				map.put("msg", "查询错误");
				e.printStackTrace();
			}
			if(house != null) {
				map.put("house_name", house.getHouse_name());
				map.put("house_type", house.getHouse_type());
			}
			//第一张图片
			List<HouseImg> currenHouseImg = null;
			try {
				currenHouseImg = houseImgDaoImpl.getHouseImgByHouseID(house_id);
			} catch (SQLException e) {
				map.put("msg", "查询错误");
				e.printStackTrace();
			}
			if(currenHouseImg != null) {
				HouseImg firstImg  = currenHouseImg.get(0);
				map.put("house_image_id", firstImg.getHouse_img_id());
				map.put("house_image_url", firstImg.getHouse_img_url());
			}
			//获取相关文章
			String keyWord = oneArticle.getArticle_name().substring(0,2);
			List<Article> relatedArticles = null;
			try {
				relatedArticles = articleDaoImpl.queryFuzzyQuery(keyWord);
			} catch (SQLException e) {
				map.put("msg", "查询错误");
				e.printStackTrace();
			}
			if(relatedArticles != null) {
				for(Article relatedArticle : relatedArticles) {
					//一篇相关文章
					map.put("relatedArticleArticle_id", relatedArticle.getArticle_id());
					map.put("relatedArticleUser_id", relatedArticle.getUser_id());
					map.put("relatedArticleArticle_name", relatedArticle.getArticle_name());
					map.put("relatedArticlerticle_content", relatedArticle.getArticle_content());
					map.put("relatedArticleArticle_collect", relatedArticle.getArticle_collect());
					map.put("relatedArticleArticle_praise", relatedArticle.getArticle_praise());
					
					//获得相关文章文章的第一张图片
					Integer relatedArticleArticle_id = relatedArticle.getArticle_id();
					List<ArticleImg> relatedArticleCurrenArticleImg = null;
					try {
						relatedArticleCurrenArticleImg = articleImgDaoImpl.queryArticleImgByArticleId(relatedArticleArticle_id);
					} catch (SQLException e) {
						map.put("msg", "查询错误");
						e.printStackTrace();
					}
					if(relatedArticleCurrenArticleImg != null) {
						ArticleImg relatedArticleFirstImg  = relatedArticleCurrenArticleImg.get(0);
						map.put("relatedArticleImage_id", relatedArticleFirstImg.getImage_id());
						map.put("relatedArticleImage_url", relatedArticleFirstImg.getImage_url());
					}
					//获取相关文章文章评论数量
					Long relatedArticleCommCount = 0l;
					try {
						relatedArticleCommCount = commentDaoImpl.queryCommCount(relatedArticleArticle_id);
					} catch (SQLException e) {
						map.put("msg", "查询错误");
						e.printStackTrace();
					}
					map.put("relatedArticleCommCount", relatedArticleCommCount);
					//获得相关文章用户头像
					Integer relatedArticleUser_id = relatedArticle.getUser_id();
					User relatedArticleUser = null;
					try {
						relatedArticleUser = userDaoImpl.queryUserById(relatedArticleUser_id);
					} catch (SQLException e) {
						map.put("msg", "查询错误");
						e.printStackTrace();
					}
					if(relatedArticleUser != null) {
						String relatedArticleUserHeadUrl = relatedArticleUser.getUser_headimg_url();
						map.put("relatedArticleUser_headimg_url", relatedArticleUserHeadUrl);
					}
				}
			}
		}
		list.add(map);
		return list;
	}
	
	/**
	 * 添加文章内容
	 * @throws SQLException
	 */
	public Map<String, Object> addArticle(Map<String, Object> addArticleInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获得添加文章所需信息
		Integer user_id = null;
		String article_name = null;		
		String article_content = null;	
		Integer house_id =	 null;
		if(addArticleInfo.get("user_id") != null)
			user_id = Integer.valueOf(addArticleInfo.get("user_id").toString());
		if(addArticleInfo.get("article_name") != null)
			article_name = addArticleInfo.get("article_name").toString();
		if(addArticleInfo.get("article_content") != null)
			article_content = addArticleInfo.get("article_content").toString();
		if(addArticleInfo.get("house_id") != null)
			house_id = Integer.valueOf(addArticleInfo.get("house_id").toString());
		try {
			int count = articleDaoImpl.addArticle(user_id, article_name, article_content, house_id);
			if(count != 0) {
				//文章插入成功
				map.put("msg", "文章插入成功");
			} else {
				map.put("msg", "文章插入失败");
			}
		} catch (SQLException e) {
			map.put("msg", "查询错误");
			e.printStackTrace();
		}
		return map;
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
	public List<Article> queryPageArticle(int startRow, int pageSize)
			throws SQLException {
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
	public int updateArticle_praiset(Integer article_praise, Integer article_id)
			throws SQLException {
		return articleDaoImpl.updateArticle_praiset(article_praise, article_id);
	}

	/**
	 * 更新收藏数量
	 * @throws SQLException 
	 */
	public int updateArticle_collect(Integer article_id, Integer article_collect)
			throws SQLException {
		return articleDaoImpl.updateArticle_collect(article_id, article_collect);
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
	 * 添加文章图片
	 */
	public Map<String, Object> addArticleImgById(
			Map<String, Object> addArticleInfo) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
