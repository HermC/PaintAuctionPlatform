package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.Lot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class LotDaoTest {

    @Autowired
    private LotDao lotDao;

    @Test
    @Transactional
    public void insertTest() {
        List<Lot> lotsOld = lotDao.findAll();

        Lot lot = new Lot();
        lot.setName("测试拍品1");
        lot.setDescription("测试");
        lot.setStartingPrice(100.);
        lot.setStartingTime(new Date(System.currentTimeMillis() + 3600 * 1000));
        lot.setEndingTime(new Date(System.currentTimeMillis() + 3600 * 1000 * 2));

        lotDao.save(lot);

        List<Lot> lotsNew = lotDao.findAll();
        assert lotsNew.size() == lotsOld.size() + 1;
    }

}
