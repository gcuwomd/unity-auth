package com.example.unityauth.Fliter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CusUsernamePasswordAuthentication extends UsernamePasswordAuthenticationFilter {
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println("Json");
        // 从 POST 请求中获取用户名和密码
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        UsernamePasswordAuthenticationToken authenticationToken;
        try {
            jsonNode = objectMapper.readTree(request.getInputStream());
            String username = jsonNode.get("username").asText();
            String password = jsonNode.get("password").asText();
            authenticationToken= UsernamePasswordAuthenticationToken.unauthenticated(username, password);
            this.setDetails(request, authenticationToken);
            logger.info("success");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 创建一个自定义的认证令牌
        // 进行身份验证
        return this.getAuthenticationManager().authenticate(authenticationToken);
    }

}
