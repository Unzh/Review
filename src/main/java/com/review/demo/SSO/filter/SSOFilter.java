package com.review.demo.SSO.filter;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Title : SSOFilter.java
 * Package : com.review.demo.SSO.filter
 * Description : <p>基于 shiro 实现单点登录的过滤</p>
 * Create on : 2020/4/14
 *
 */
public class SSOFilter extends AccessControlFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSOFilter.class);

    private String SSOUrl;

    private String loginUrl;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        if (servletRequest instanceof HttpServletRequest
                && servletResponse instanceof HttpServletResponse) {
            /**
             * TODO 获取Session中内容
             * 校验 server中 令牌是否存在，存在则直接返回登录地址，不存在则去SSO登录
             */
            if (true) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendRedirect(loginUrl);
                return true;
            }
        }
        return false;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回 true 表示需要继续处理；
     * 如果返回 false 表示该拦截器实例已经处理了，将直接返回即可。
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        return false;
    }
}
