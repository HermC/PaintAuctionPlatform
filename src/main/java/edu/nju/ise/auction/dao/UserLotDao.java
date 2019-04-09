package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.UserLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLotDao extends JpaRepository<UserLot, Long>, JpaSpecificationExecutor<UserLot> {

}
