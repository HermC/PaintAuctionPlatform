package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.Role;
import edu.nju.ise.auction.model.User;
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
public class RoleDaoTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Test
    @Transactional
    public void insertTest() {
        User user = userDao.findById(1L).get();
        Role role = new Role();
        role.setRole(Role.Type.USER);
        role.setUser(user);

        Role iRole = roleDao.save(role);

        System.out.println(iRole.toString());
        System.out.println(iRole.getUser().toString());

        assert iRole != null;
    }

}
