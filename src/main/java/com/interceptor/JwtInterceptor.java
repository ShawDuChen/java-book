package com.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor extends AbstractInterceptor {
    private static final String JWT_SECRET = "OQIWUEnaksdjasgd87646546QWEYWQIUAJKSGDJASGDWQVWQEHQWEIWQYEIQWOYASHDKJASHDKJASHdbjkAGUWQTEYQWUqpoi";
    private static final String AUTH_HEADER = "Authorization";

    @Override
    public void destroy() {}

    @Override
    public void init() {}

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        ActionContext ctx = actionInvocation.getInvocationContext();
        HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ctx.get(StrutsStatics.HTTP_RESPONSE);
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.sendRedirect(request.getContextPath() + "/unAuthorized.action");
            return "unauthorized";
        }
        try {
            String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
            ctx.put("jwtClaims", claims);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.sendRedirect(request.getContextPath() + "/unAuthorized.action");
            return "unauthorized";
        }
        return actionInvocation.invoke();
    }
}
