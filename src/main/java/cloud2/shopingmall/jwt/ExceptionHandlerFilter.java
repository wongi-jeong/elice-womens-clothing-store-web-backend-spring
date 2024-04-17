package cloud2.shopingmall.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.security.access.AccessDeniedException;
import java.util.NoSuchElementException;



@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN);
        } catch (JwtException | IllegalArgumentException e) {
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        } catch (NoSuchElementException e) {
            setErrorResponse(response, ErrorCode.USERNAME_NOT_FOUND);
        } catch (AccessDeniedException e) {
            setErrorResponse(response, ErrorCode.ACCESS_DENIED);
        }
    }

    private static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        String errorMessage = errorCode.getMessage();
        byte[] errorMessageBytes = errorMessage.getBytes(StandardCharsets.UTF_8);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(errorMessageBytes);
        outputStream.flush();
    }

    private static enum ErrorCode {
        EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired token"),
        INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
        USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
        ACCESS_DENIED(HttpStatus.FORBIDDEN, "Access denied");

        private final HttpStatus httpStatus;
        private final String message;

        ErrorCode(HttpStatus httpStatus, String message) {
            this.httpStatus = httpStatus;
            this.message = message;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }

        public String getMessage() {
            return message;
        }
    }
}
