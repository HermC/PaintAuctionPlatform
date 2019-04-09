package edu.nju.ise.auction.dao;

import edu.nju.ise.auction.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {

}
