package appcentcase.todo.services.concretes;

import appcentcase.todo.core.jwt.Jwt;
import appcentcase.todo.core.jwt.JwtManager;
import appcentcase.todo.services.abstracts.TokenValidationService;
import appcentcase.todo.services.abstracts.UserService;
import appcentcase.todo.utils.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
@Service
@RequiredArgsConstructor
public class TokenValidationManager implements TokenValidationService {

    private final UserService userService;

    private final HttpServletRequest httpServletRequest;

    private final Jwt jwt;

    @Override
    public Result tokenValidaton(int user_id) {
        var username = getUserNameFromToken();
        if (!username.isSuccess()) {
            return new ErrorResult(username.getMessage());
        }
        var user = userService.getById(user_id);
        if (!user.isSuccess()) {
            return new ErrorResult(user.getMessage());
        }
        if (!user.getData().getUserName().equals(username.getData())) {
            return new ErrorResult("Token kullanıcı ile eşleşmiyor");
        }
        return new SuccessResult();
    }

    public DataResult<String> getUserNameFromToken() {
        var header = httpServletRequest.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            return new ErrorDataResult<String>(null,"Kimlik doğrulama anahtarı bulunamadı");
        }
        if (header != null) {
            String jwtToken = header.substring(7);
            String userName = jwt.getUsernameFromToken(jwtToken);
            return new SuccessDataResult<String>(userName, null);
        }
        return new ErrorDataResult<String>(null, "Kimlik doğrulama anahtarı bulunamadı");
    }

}
