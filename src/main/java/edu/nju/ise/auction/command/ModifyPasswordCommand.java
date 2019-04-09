package edu.nju.ise.auction.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModifyPasswordCommand {

    private String oldPassword;
    private String newPassword;

}
