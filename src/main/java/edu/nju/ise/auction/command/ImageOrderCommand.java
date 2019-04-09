package edu.nju.ise.auction.command;

import lombok.Data;

@Data
public class ImageOrderCommand {

    private Long imageid;
    private String recipient;
    private String phoneNumber;
    private String address;

}
