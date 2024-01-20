package hdang09.filters;

import hdang09.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Value("${app.username}")
    private String username;

    @Autowired
    public AuthorizationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private final List<String> excludedUrls = Arrays.asList("/swagger-ui", "/login", "/v3/api-docs");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, java.io.IOException {
        // Bypass OPTIONS method
        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(request, response);
            return;
        }

        String requestPath = request.getRequestURI().substring(request.getContextPath().length());
        if (isUrlExcluded(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        String PREFIX_HEADER = "Authorization";
        String authHeader = request.getHeader(PREFIX_HEADER);
        if (authHeader == null || authHeader.isEmpty()) {
            setResponseUnAuthorized(response, "Token empty");
            return;
        }

        try {
            String PREFIX_TOKEN = "Bearer ";
            String token = authHeader.substring(PREFIX_TOKEN.length());
            if (jwtUtil.isTokenExpired(token)) {
                setResponseUnAuthorized(response, "Token expired");
                return;
            }

            if (!jwtUtil.isTokenValid(token, username)) {
                setResponseUnAuthorized(response, "Token Invalid");
                return;
            }
        } catch (Exception e) {
            setResponseUnAuthorized(response, "Token Invalid");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isUrlExcluded(String url) {
        for (String excludedUrl : excludedUrls) {
            if (url.startsWith(excludedUrl)) {
                return true;
            }
        }

        return false;
    }

    private void setResponseUnAuthorized(HttpServletResponse response, String text) throws java.io.IOException {
        response.setContentType("text/plain");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(text);
    }
}