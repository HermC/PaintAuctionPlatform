package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LotDao extends JpaRepository<Lot, Long>, JpaSpecificationExecutor<Lot> {

}
