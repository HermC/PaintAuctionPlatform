package edu.nju.ise.auction.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "dev")
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void test() throws Exception {
//        mailService.sendSimpleMail("测试邮件", "测试邮件内容");

        Map<String, Object> params = new HashMap<>();
        params.put("username", "testUsername");
        params.put("recipient", "yyy");
        params.put("phone", "123456789");
        params.put("address", "12312");
        params.put("src", "http://121.43.49.208:8080/images/5a1ab1c2-4e51-4518-b580-6073c3966317_7P8A7995_副本.jpg");

        mailService.sendTemplateMail("测试Mime", params);

    }

}
