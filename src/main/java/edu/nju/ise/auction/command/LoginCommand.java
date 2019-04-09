package edu.nju.ise.auction.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginCommand {

    private String username;
    private String password;

}
