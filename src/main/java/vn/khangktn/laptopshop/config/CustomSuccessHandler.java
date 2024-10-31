package vn.khangktn.laptopshop.config;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.khangktn.laptopshop.domain.User;
import vn.khangktn.laptopshop.service.UserService;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    UserService userService;

    String determineTargetUrl(final Authentication authentication) {
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("ROLE_USER", "/");
        roleTargetUrlMap.put("ROLE_ADMIN", "/admin");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for(final GrantedAuthority grantedAuthority : authorities){
            String authorityName = grantedAuthority.getAuthority();
            if(roleTargetUrlMap.containsKey(authorityName)){
                return roleTargetUrlMap.get(authorityName);
            }
        }
        throw new IllegalStateException();
    }

    void clearAuthenticationAttributes(HttpServletRequest httpRequest, Authentication authentication) {
        HttpSession httpSession = httpRequest.getSession(false);
        if(httpSession == null){
            return;
        }
        httpSession.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        User user = userService.getUserByEmail(authentication.getName());
        if(user != null) {
            httpSession.setAttribute("fullName", user.getFullName());
            httpSession.setAttribute("avatar", user.getAvatar());
            httpSession.setAttribute("email", user.getEmail());
            httpSession.setAttribute("numberItemCart", user.getCart() == null ? 0 : user.getCart().getQuantity());
        }
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
        HttpServletResponse response, 
        Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
        clearAuthenticationAttributes(request, authentication);
    }
}
