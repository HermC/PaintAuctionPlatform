package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.LotImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LotImgDao extends JpaRepository<LotImg, Long> {

}
