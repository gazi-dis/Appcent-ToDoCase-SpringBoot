package appcentcase.todo.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToDoAddDto {

    @NotNull(message = "Kullanıcı boş bırakılamaz")
    private int userId;

    @NotNull(message = "Görev başlığı boş bırakılamaz")
    @NotBlank(message = "Görev başlığı boş bırakılamaz")
    @Size(min = 3, message = "Görev başlığı 3 karakterden az olamaz")
    private String title;
}
