package appcentcase.todo.models.dto;

import appcentcase.todo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private User user;
    private String token;
}
