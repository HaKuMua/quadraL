package cn.com.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 可以用于在某一个servlet上处理多个请求
 * @author junliu
 *
 */
public class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//遍历request里面的所有paramter
		Map<String,String[]> map = request.getParameterMap();
		//遍历所有的属性
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field f : fields){
			if(map == null || map.get(f.getName()) == null){
				continue;
			}
			try {
				f.setAccessible(true);
				if(f.getType() == Integer.class){//integer类型
					f.set(this, new Integer(map.get(f.getName())[0]));
				}else if(f.getType() == String.class){//string类型
					f.set(this, map.get(f.getName())[0] );
				}else if(f.getType() == Long.class){//long类型
					f.set(this, new Long(map.get(f.getName())[0]) );
				}else if(f.getType() == Boolean.class){//boolean类型
					f.set(this, new Boolean(map.get(f.getName())[0]) );
				}else if(f.getType() == Double.class){//double类型
					f.set(this, new Double(map.get(f.getName())[0]) );
				}else if(f.getType() == new String[]{}.getClass()){//string数组
					f.set(this, map.get(f.getName()) );
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/500.jsp").forward(request, response);
			} 
		}
		
		
		String methodName = request.getParameter("method");
		System.out.println("------------------method:"+methodName+"---------------------");
		//如果调用一个servlet的时候，  a?method=xxx
		//传递过来的method参数不为空就通过这个参数名去调用对应的方法
		if(methodName!=null && !methodName.isEmpty()){
			try {
				//先通过反射获取到这个方法名对应的方法
				Method m = this.getClass().getMethod(methodName, 
						HttpServletRequest.class,HttpServletResponse.class);
				//调用反射获取的方法
				m.invoke(this, request,response);
			} catch (Exception e) {
				e.printStackTrace();
				request.getRequestDispatcher("/500.jsp").forward(request, response);
			} 
		}
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
