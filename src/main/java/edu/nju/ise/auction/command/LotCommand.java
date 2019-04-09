package edu.nju.ise.auction.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class LotCommand {

    private Long id;    // 新建的时候不用传id
    private String name;
    private String description;
    private Date startingTime;
    private Double startingPrice;
    private Date endingTime;
    private Double endingPrice; // 一般默认是空的
    private List<String> imgSrcs;

}
