package cloud2.shopingmall.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.NoSuchElementException;



@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    /**
     * 토큰 관련 에러 핸들링
     * JwtTokenFilter 에서 발생하는 에러를 핸들링해준다.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {

            //토큰의 유효기간 만료
            log.error("만료된 토큰입니다");
            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN);

        } catch (JwtException | IllegalArgumentException e) {

            //유효하지 않은 토큰
            log.error("유효하지 않은 토큰이 입력되었습니다.");
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);

        } catch (NoSuchElementException e) {

            //사용자 찾을 수 없음
            log.error("사용자를 찾을 수 없습니다.");
            setErrorResponse(response, ErrorCode.USERNAME_NOT_FOUND);
        }
    }

    private static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        // 에러 코드 설정
        response.setStatus(errorCode.getHttpStatus().value());

        // 에러 메시지 설정
        String errorMessage = errorCode.getMessage();
        response.getWriter().write(errorMessage);
    }


    private static enum ErrorCode {
        EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "Expired token"),
        INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "Invalid token"),
        USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found");

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