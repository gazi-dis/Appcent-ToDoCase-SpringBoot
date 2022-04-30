package appcentcase.todo.models.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotNull
    @NotBlank(message = "Kullanıcı adı boş bırakılamaz")
    private String userName;

    @NotNull
    @NotBlank(message = "Parola boş bırakılamaz")
    private String password;
}
