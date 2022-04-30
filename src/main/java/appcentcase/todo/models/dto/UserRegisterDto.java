package appcentcase.todo.models.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Abdurrahman Gazi DIS
 * @Date 28.04.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    @NotNull
    @NotBlank(message = "İsim alanı boş bırakılamaz")
    private String name;

    @NotNull
    @NotBlank(message = "Soyisim alanı boş bırakılamaz")
    private String surName;

    @NotNull
    @NotBlank(message = "Kullanıcı adı alanı boş bırakılamaz")
    @Size(min = 3, message = "Kullanıcı adı 3 karakterden az olamaz")
    private String userName;

    @NotNull
    @NotBlank(message = "Email alanı boş bırakılamaz")
    @Email(message = "Geçerli bir mail formatı giriniz")
    private String email;

    @NotNull
    @NotBlank(message = "Parola alanı boş bırakılamaz")
    @Size(min = 8, message = "Parola 8 karakterden az olamaz")
    private String password;
}
