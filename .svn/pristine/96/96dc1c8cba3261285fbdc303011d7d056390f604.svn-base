package fengkongweishi.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import fengkongweishi.entity.user.User;
import fengkongweishi.entity.user.UserRepository;
import fengkongweishi.entity.user.UserVO;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.util.Common;
import fengkongweishi.util.FailResponse;
import fengkongweishi.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 移动端认证过滤器
 *
 * @author huanghengkun
 * @date 2018/02/02
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Autowired
    UserRepository userRepository;
    private RememberMeServices rememberMeServices = new NullRememberMeServices();

    public MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login/mobile"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder in = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            in.append(line);
        }
        reader.close();

        String body = in.toString().replace("\t", "");
        JSONObject bodyJson = JSON.parseObject(body);
        String username = bodyJson.getString("username");
        String password = bodyJson.getString("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new FailResponse(ExceptionEnum.LOGIN_USERNAME_PASSWORD_EMPTY);
        }
        User user = userRepository.findByUsername(username);
        if (user == null || !user.password().equals(MD5Util.encode(password))) {
            throw new FailResponse(ExceptionEnum.LOGIN_USERNAME_PASSWORD_WRONG);
        }
        Common.UserDetailsImpl userDetailsImpl = new Common.UserDetailsImpl(user);
        return new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);

        rememberMeServices.loginSuccess(request, response, authResult);

        // Fire event
        if (this.eventPublisher != null) {
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(
                    authResult, this.getClass()));
        }

        response.setStatus(200);
        User user = ((Common.UserDetailsImpl) authResult.getPrincipal()).getUser();
        UserVO userVO = new UserVO(user);
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(userVO);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(body);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
