package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.ImageOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageOrderDao extends JpaRepository<ImageOrder, Long>, JpaSpecificationExecutor<ImageOrder> {

}
