package com.nongboo.flowery.util.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nongboo.flowery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {


    private final JWTService jwtService;
    private final ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean check = checkAnnotation(handler, NoAuth.class);
        if (check) return true;

        try {
            int userIdByJwt = jwtService.getUserId();
            request.setAttribute("userId", userIdByJwt);
        } catch (Exception exception) {
            // userId가 없는 token 이면 redirect
            exception.printStackTrace();
            String requestURI = request.getRequestURI();

            Map<String, String> map = new HashMap();
            map.put("redirectURI", "redirectURI=" + requestURI);

            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            //response.sendError(401, "권한이 없습니다.");
            response.getWriter().write(json);

            return false;
        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class cls){
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        if(handlerMethod.getMethodAnnotation(cls) != null){
            return true;
        }
        return false;
    }
}
