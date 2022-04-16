package SpringReact.SpringReact.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class AuthenticationService {
    static final long EXPIRATIONTIME = 864_000_00;
    //토큰의 만료 시간
    static final String SIGNINGKEY = "signingKey";
    //JWT를 서명하는데 사용 (JWT 토큰을 확인하여 애플리케이션에서 가져온 것임을 증명
    static final String BEARER_PREFIX = "Bearer";
    //Authorization 토큰의 접두사

    static public void addJWTToken(HttpServletResponse response, String username) {
        String JwtToken = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
                .compact();
        response.addHeader("Authorization", BEARER_PREFIX + " " + JwtToken);
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        //프론트엔드 의 JS 제한으로 인해 헤더를 추가
    }

    static public Authentication getAuthentication(HttpServletRequest request) {
        //Authorization 헤더에서 JWT 토큰을 가져옴옴
       String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(SIGNINGKEY)
                    .parseClaimsJws(token.replace(BEARER_PREFIX, ""))
                    .getBody()
                    .getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
            } else {
                throw new RuntimeException("Authentication failed");
            }
        }
        return null;
    }
}
