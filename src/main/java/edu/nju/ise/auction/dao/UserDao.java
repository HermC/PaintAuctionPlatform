package edu.nju.ise.auction.dao;


import edu.nju.ise.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * 用户数据服务接口
 *
 * @author HermC yzy627@126.com
 * @date 2019/01/30
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
