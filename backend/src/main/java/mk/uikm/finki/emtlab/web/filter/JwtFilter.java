package mk.uikm.finki.emtlab.web.filter;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import mk.uikm.finki.emtlab.constants.JwtConstants;
import mk.uikm.finki.emtlab.helpers.JwtHelper;
import mk.uikm.finki.emtlab.model.domain.User;
import mk.uikm.finki.emtlab.service.domain.UserService;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtHelper jwtHelper;
    private final UserService userService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public JwtFilter(JwtHelper jwtHelper, UserService userService, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtHelper = jwtHelper;
        this.userService = userService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String headerValue = request.getHeader(JwtConstants.HEADER);
        if (headerValue == null || !headerValue.startsWith(JwtConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = headerValue.substring(JwtConstants.TOKEN_PREFIX.length());

        try {
            String username = jwtHelper.extractUsername(token);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (username == null || authentication != null) {
                filterChain.doFilter(request, response);
                return;
            }

            Optional<User> user = userService.findByUsername(username);
            if (user.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            if (!jwtHelper.isExpired(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        user.get(),
                        null,
                        user.get().getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (JwtException jwtException) {
            handlerExceptionResolver.resolveException(
                    request,
                    response,
                    null,
                    jwtException
            );
            return;
        }

        filterChain.doFilter(request, response);
    }
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        System.out.println("REQUEST: " + request.getRequestURI());
//
//        String headerValue = request.getHeader(JwtConstants.HEADER);
//
//        System.out.println("AUTH HEADER: " + headerValue);
//
//        if (headerValue == null || !headerValue.startsWith(JwtConstants.TOKEN_PREFIX)) {
//            System.out.println("NO VALID HEADER");
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = headerValue
//                .substring(JwtConstants.TOKEN_PREFIX.length())
//                .trim();
//
//        System.out.println("TOKEN: " + token);
//
//        try {
//            String username = jwtHelper.extractUsername(token);
//
//            System.out.println("USERNAME: " + username);
//
//            Authentication authentication =
//                    SecurityContextHolder.getContext().getAuthentication();
//
//            System.out.println("CURRENT AUTH: " + authentication);
//
//            if (username == null || authentication != null) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//            Optional<User> user = userService.findByUsername(username);
//
//            System.out.println("USER FOUND: " + user.isPresent());
//            System.out.println(user.get().getAuthorities());
//
//            if (user.isEmpty()) {
//                filterChain.doFilter(request, response);
//                return;
//            }
//
//            if (!jwtHelper.isExpired(token)) {
//                UsernamePasswordAuthenticationToken authToken =
//                        new UsernamePasswordAuthenticationToken(
//                                user.get(),
//                                null,
//                                user.get().getAuthorities()
//                        );
//
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//
//                System.out.println("AUTHENTICATION SET");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        filterChain.doFilter(request, response);
//    }
}

