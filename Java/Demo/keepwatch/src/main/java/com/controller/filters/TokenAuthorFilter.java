package com.controller.filters;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.service.UserService;
import com.service.result.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 2)
@WebFilter(urlPatterns = { "/*" })
public class TokenAuthorFilter implements Filter {

    @Autowired
    private UserService userService;

    // AccessTokenKey
    private static final String KeepWatch_AccessTokenKey = "AccessToken";

    // 需要排除的接口地址
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList("/main/excludefilter", "/user/login", "/user/logout", "/user/register")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String contextPath = request.getContextPath();
        String path = request.getRequestURI().substring(contextPath.length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        if (allowedPath) {
            chain.doFilter(req, res);
        } else {
            Boolean isContinueValidadte = true;
            String methodStr = request.getMethod();
            if (methodStr.toUpperCase().equals("OPTIONS")) {
                isContinueValidadte = false;
            }

            String accessToken = (String) request.getHeader(KeepWatch_AccessTokenKey);
            if (isContinueValidadte && (accessToken == null || accessToken.isEmpty())) {
                isContinueValidadte = false;
            }

            if (isContinueValidadte && this.volidateToken(accessToken)) {
                // 正常调用接口
                chain.doFilter(req, res);
            } else {
                ResponseModel result = new ResponseModel();
                result.setSuccess(false);
                result.setErrcode("100001");
                result.setErrmsg("授权失败！！！");
                HttpServletResponse response = (HttpServletResponse) res;
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.addHeader("Access-Control-Allow-Origin", "*");
                response.addHeader("Access-Control-Allow-Methods", "*");
                response.addHeader("Access-Control-Allow-Headers", "*");
                response.addHeader("Access-Control-Max-Age", "7200");
                PrintWriter writer = response.getWriter();
                String jsonObjectStr = JSONObject.toJSONString(result);
                writer.print(jsonObjectStr);
            }
        }
    }

    /**
     * 验证token是否正确
     *
     * @param token
     * @return 验证是否成功
     */
    public boolean volidateToken(String token) {
        ResponseModel response = userService.getUserByAccessToken(token);
        return response.getSuccess();
    }

    @Override
    public void destroy() {
    }
}