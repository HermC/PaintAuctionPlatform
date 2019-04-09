package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {


}
