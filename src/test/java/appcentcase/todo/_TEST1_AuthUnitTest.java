package appcentcase.todo;

import appcentcase.todo.controllers.AuthController;
import appcentcase.todo.models.dto.UserLoginDto;
import appcentcase.todo.models.dto.UserRegisterDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class _TEST1_AuthUnitTest extends TodoApplicationTests{

    @Autowired
    private AuthController authController;

    @Order(1)
    @Test
    public void registerTest() throws Exception{
        UserRegisterDto userRegisterDto = new UserRegisterDto("Test Name","Test Surname","test","test@mail.com","12345678");
        var result = authController.userRegister(userRegisterDto);
        assertThat(result.getStatusCodeValue()).isEqualTo(400);
    }

    @Order(2)
    @Test
    public void loginTest() throws Exception{
        UserLoginDto userLoginDto = new UserLoginDto("test","12345678");
        var result = authController.userLogin(userLoginDto);
        assertThat(result.getStatusCodeValue()).isEqualTo(200);
    }


}
