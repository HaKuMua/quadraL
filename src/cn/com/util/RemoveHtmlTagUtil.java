package cn.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RemoveHtmlTagUtil {
	/**
	 * 正则表达式html标签去除
	 * @param htmlContent
	 * @return
	 */
	public static String removeHtmlTag(String htmlContent){
    	if (htmlContent!=null&&htmlContent.length()>0) {
    		//去除html标签
    		htmlContent = htmlContent.replaceAll("\\s*",""); //去除空格
    		String regEx_html="<[^>]+>";                      //HTML标签正则表达式
    		Pattern pattern = Pattern.compile(regEx_html);
    		Matcher matcher = pattern.matcher(htmlContent);
    		while (matcher.find()){
    			htmlContent = htmlContent.replace(matcher.group(),"");
    		}
    		return htmlContent.replaceAll("&nbsp;","");
		}else {
			return "";
		}
    }
}
