package edu.nju.ise.auction.service;

import edu.nju.ise.auction.command.LotCommand;
import edu.nju.ise.auction.model.Lot;
import edu.nju.ise.auction.utils.TimeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class LotServiceTest {

    @Autowired
    private LotService lotService;

    @Test
    public void testGetLotListPage() {
//        Page<Lot> lots = lotService.getNotEndedLotList(0L, 12L, TimeUtils.string2Date("2019-02-01 00:00:00"));
        Page<Lot> lots = lotService.getNotEndedLotList(0L, 12L, new Date(System.currentTimeMillis()));
        System.out.println(lots.getTotalPages());
        System.out.println(lots.getNumber());
        assert lots.getContent().size() == 1;

        lots = lotService.getNotEndedLotList(0L, 10L, TimeUtils.string2Date("2028-01-01 00:00:00"));
        assert lots.getContent().size() == 0;

        lots = lotService.getNotEndedLotList(0L, 10L, TimeUtils.string2Date("2018-01-01 00:00:00"));
        assert lots.getContent().size() == 2;
        for (Lot l: lots) {
            System.out.println(l);
        }
    }

    @Test
    @Transactional
    public void testAddLot() {
        LotCommand command = new LotCommand();
        command.setName("测试拍品1");
        command.setStartingPrice(100.);
        command.setStartingTime(TimeUtils.string2Date("2019-01-20 08:00:00"));
        command.setEndingPrice(200.);
        command.setEndingTime(TimeUtils.string2Date("2019-01-20 10:00:00"));
        command.setImgSrcs(Arrays.asList("assets/tmp/WechatIMG369.jpeg", "assets/tmp/WechatIMG369.jpeg"));

        Lot lotInsert1 = lotService.addLot("admin", command);
        assert lotInsert1 != null;
        assert lotInsert1.getLotImgs().size() == 2;

        LotCommand command2 = new LotCommand();
        command2.setName("测试拍品2");
        command2.setStartingPrice(100.);
        command2.setStartingTime(TimeUtils.string2Date("2019-02-05 08:00:00"));
        command2.setEndingPrice(200.);
        command2.setEndingTime(TimeUtils.string2Date("2019-02-05 10:00:00"));
        command2.setImgSrcs(Arrays.asList("assets/tmp/WechatIMG369.jpeg", "assets/tmp/WechatIMG369.jpeg"));

        Lot lotInsert2 = lotService.addLot("admin", command2);
        assert lotInsert2 != null;
        assert lotInsert2.getLotImgs().size() == 2;
    }

}
