package cn.nicecoder.newssys.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 IP获取工具
 * @author: longt
 * @date: 2020/12/31 上午11:38
 */
public class IPUtil {
	public static String getIpAddress(HttpServletRequest request) {   
	    String ip = request.getHeader("x-forwarded-for");   
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	      ip = request.getHeader("Proxy-Client-IP");   
	    }   
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	      ip = request.getHeader("WL-Proxy-Client-IP");   
	    }   
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	      ip = request.getHeader("HTTP_CLIENT_IP");   
	    }   
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR");   
	    }   
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	      ip = request.getRemoteAddr();   
	    }   
	    return ip;   
	  }   
}
