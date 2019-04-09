package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.User;
import edu.nju.ise.auction.utils.Constants;
import edu.nju.ise.auction.utils.ShiroUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("dev")
@SpringBootTest
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    @Transactional
    public void insertTest() {
        User user = new User();
        user.setUsername("admin_insert");
        user.setSalt(ShiroUtils.generateSalt(Constants.SALT_LENGTH));
        user.setPassword(ShiroUtils.encryptPassword(Constants.MD5, "Pychen@gmail.com", user.getSalt()));
        user.setEmail("Pychen_insert@gmail.com");
        user.setBan(Constants.NOT_BAN);
        User user1 = userDao.save(user);
        assert user1 != null;
    }

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("admin");
        user.setSalt(ShiroUtils.generateSalt(Constants.SALT_LENGTH));
        user.setPassword(ShiroUtils.encryptPassword(Constants.MD5, "Pychen@gmail.com", user.getSalt()));
        user.setEmail("Pychen_insert@gmail.com");
        user.setBan(Constants.NOT_BAN);
        User user1 = userDao.save(user);
        assert user1 != null;
    }

    @Test
    public void findTest() {
        Optional<User> user = userDao.findById(1L);
        assert user.isPresent();
        assert user.get().getUsername().equals("admin");

        System.out.println(user.get().toString());
        System.out.println(user.get().getRoles().get(0).toString());
    }

    @Test
    @Transactional
    public void updateTest() {
        User user = userDao.findByUsername("admin").get();
        user.setPassword(ShiroUtils.encryptPassword("1234567", user.getSalt()));
        userDao.save(user);

        User userModified = userDao.findByUsername("admin").get();
        assert ShiroUtils.encryptPassword("1234567", userModified.getSalt()).equals(userModified.getPassword());
    }

}
