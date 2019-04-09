package edu.nju.ise.auction.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class TimeUtilsTest {

    @Test
    public void test() {
        Date date = TimeUtils.string2Date("2018-09-01 00:00:01");
        Date date2 = TimeUtils.string2Date("2018-09-01");
        Date date3 = TimeUtils.string2Date("2018-09-01 00:00");

        assert date != null;
        assert date2 != null;
        assert date3 != null;
    }

}
