package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.UserLot;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class UserLotDaoTest {

    @Autowired
    private UserLotDao userLotDao;

    @Test
    @Transactional
    public void insertTest() {
        UserLot userLot = new UserLot();
        userLot.setUserid(1L);
        userLot.setLotid(1L);
        UserLot userLotInsert = userLotDao.save(userLot);

        System.out.println(userLotInsert.getId());
    }

}
