package edu.nju.ise.auction.service;

import edu.nju.ise.auction.dao.UserDao;
import edu.nju.ise.auction.model.User;
import edu.nju.ise.auction.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserDao userDao;

    public boolean verifyUser(String username) {
        return username != null && userDao.findByUsername(username).isPresent();
    }

    public boolean verifyPassword(String username, String password) {
        if (username == null || password.equals("")) {
            return false;
        }
        Optional<User> user = userDao.findByUsername(username);
        if (!user.isPresent()) {
            return false;
        }
        password = ShiroUtils.encryptPassword(password, user.get().getSalt());
        return password.equals(user.get().getPassword());
    }

}
