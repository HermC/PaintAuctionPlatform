package edu.nju.ise.auction.service;

import edu.nju.ise.auction.command.UserCommand;
import edu.nju.ise.auction.dao.RoleDao;
import edu.nju.ise.auction.dao.UserDao;
import edu.nju.ise.auction.model.Role;
import edu.nju.ise.auction.model.User;
import edu.nju.ise.auction.utils.Constants;
import edu.nju.ise.auction.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public User getUser(String username) {
        if (username == null) {
            return null;
        }
        Optional<User> user = userDao.findByUsername(username);
        return user.orElse(null);
    }

    public boolean isExisted(String username) {
        return this.getUser(username) != null;
    }

    @Transactional
    public User addUser(UserCommand command) {
        User user = new User();
        user.setUsername(command.getUsername());
        user.setPassword(command.getPassword());
        user.setEmail(command.getEmail());
        if (user == null) {
            return null;
        }
        if (user.getUsername() == null
            || user.getPassword() == null
            || user.getEmail() == null) {
            return null;
        }
        if (this.isExisted(user.getUsername())) {
            return null;
        }
        String salt = ShiroUtils.generateSalt();
        user.setSalt(salt);
        user.setPassword(ShiroUtils.encryptPassword(user.getPassword(), salt));
        user.setBan(Constants.NOT_BAN);

        User userInsert = userDao.save(user);
        if (userInsert == null) {
            return null;
        }

        Role defaultRole = new Role();
        defaultRole.setRole(Role.Type.USER);
        defaultRole.setUser(userInsert);

        Role roleInsert = roleDao.save(defaultRole);
        List<Role> roles = new ArrayList<>();
        roles.add(roleInsert);
        userInsert.setRoles(roles);

        return userInsert;
    }

    public User modifyPassword(String username, String oldPassword, String newPassword) {
        if (!authService.verifyUser(username)) {
            return null;
        }
        if (!authService.verifyPassword(username, oldPassword)) {
            return null;
        }
        User user = userDao.findByUsername(username).get();
        user.setSalt(ShiroUtils.generateSalt());
        user.setPassword(ShiroUtils.encryptPassword(newPassword, user.getSalt()));
        return userDao.save(user);
    }

}
