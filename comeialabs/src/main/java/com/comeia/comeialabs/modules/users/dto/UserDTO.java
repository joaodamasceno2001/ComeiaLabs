package com.comeia.comeialabs.modules.users.dto;

import com.comeia.comeialabs.modules.users.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String userName;

    private String email;

    public UserDTO(User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }
}
