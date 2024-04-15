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
//        } catch (NullPointerException e){
//            //토큰의 유효기간 만료
//            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN);
        } catch (ExpiredJwtException e) {

            //토큰의 유효기간 만료
            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN);

        } catch (JwtException | IllegalArgumentException e) {

            //유효하지 않은 토큰
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);

        } catch (NoSuchElementException e) {

            //사용자 찾을 수 없음
            setErrorResponse(response, ErrorCode.USERNAME_NOT_FOUND);
        }
    }

    private static void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        // 에러 코드 설정
        response.setStatus(errorCode.getHttpStatus().value());

        // 에러 메시지 설정 및 UTF-8로 인코딩
        String errorMessage = errorCode.getMessage();
        byte[] errorMessageBytes = errorMessage.getBytes(StandardCharsets.UTF_8);

        // 응답 헤더 설정
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json"); // 예시로 JSON 형식으로 설정

        // 응답 데이터 전송
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(errorMessageBytes);
        outputStream.flush();
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