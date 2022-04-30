package appcentcase.todo.services.concretes;

import appcentcase.todo.core.business.Rules;
import appcentcase.todo.entities.User;
import appcentcase.todo.models.dto.UserLoginDto;
import appcentcase.todo.models.dto.UserRegisterDto;
import appcentcase.todo.repository.UserRepository;
import appcentcase.todo.services.abstracts.UserService;
import appcentcase.todo.utils.response.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {

    @Autowired
    public UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Result register(UserRegisterDto userRegisterDto) {
        var result = Rules.exec(checkUserExistsByUserName(userRegisterDto.getUserName()),checkUserExistsByEmail(userRegisterDto.getEmail()));
        if (result != null)
            return new ErrorResult(result.getMessage());
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(encodePassword(user.getPassword()));
        user.setRegisterDate(LocalDate.now());
        userRepository.save(user);
        return new SuccessResult("Kullanıcı kayıt işlemi başarılı");
    }

    @Override
    public Result login(UserLoginDto userLoginDto) {
        var user = this.getByUserName(userLoginDto.getUserName());
        if (!user.isSuccess() || !passwordEncoder.matches(userLoginDto.getPassword(), user.getData().getPassword())) {
            return new ErrorResult("Kullanıcı adı veya parola yanlış");
        }
        return new SuccessResult();
    }

    @Override
    public DataResult<User> getById(int id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return new ErrorDataResult<User>("Kullanıcı bulunamadı");
        }
        return new SuccessDataResult<User>(user);
    }

    @Override
    public DataResult<User> getByMail(String email) {
        var user = userRepository.findByEmail(email);
        if (user == null)
            return new ErrorDataResult<User>("Kullanıcı bulunamadı");
        return new SuccessDataResult<User>(user);
    }

    @Override
    public DataResult<User> getByUserName(String userName) {
        var user = userRepository.findByUserName(userName);
        if (user == null)
            return new ErrorDataResult<User>("Kullanıcı bulunamadı");
        return new SuccessDataResult<User>(user);
    }

    public Result checkUserExistsByEmail(String email) {
        var result = this.getByMail(email);
        if (result.getData() != null)
            return new ErrorResult("Bu kullanıcı zaten mevcut");
        return new SuccessResult();
    }

    public Result checkUserExistsByUserName(String userName) {
        var result = this.getByUserName(userName);
        if (result.getData() != null)
            return new ErrorResult("Bu kullanıcı adı şuan başkası tarafından kullanılıyor");
        return new SuccessResult();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
