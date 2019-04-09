package edu.nju.ise.auction.service;

import edu.nju.ise.auction.command.LotCommand;
import edu.nju.ise.auction.dao.LotDao;
import edu.nju.ise.auction.dao.LotImgDao;
import edu.nju.ise.auction.dao.UserLotDao;
import edu.nju.ise.auction.model.Lot;
import edu.nju.ise.auction.model.LotImg;
import edu.nju.ise.auction.model.User;
import edu.nju.ise.auction.model.UserLot;
import edu.nju.ise.auction.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotService {

    @Autowired
    private UserService userService;

    @Autowired
    private LotDao lotDao;
    @Autowired
    private LotImgDao lotImgDao;
    @Autowired
    private UserLotDao userLotDao;

    public Page<Lot> getAllLotList(Long page, Long size) {
        Pageable pageable = new PageRequest(page.intValue(), size.intValue(), Sort.Direction.ASC, "startingTime");
        Page<Lot> lots = lotDao.findAll(pageable);
        return lots;
    }

    public Page<Lot> getNotEndedLotList(Long page, Long size) {
        Date now = new Date(System.currentTimeMillis());
        return this.getNotEndedLotList(page, size, now);
    }

    public Page<Lot> getNotEndedLotList(Long page, Long size, Date datetime) {
        Pageable pageable = new PageRequest(page.intValue(), size.intValue(), Sort.Direction.ASC, "startingTime");
        Page<Lot> lots = lotDao.findAll((Specification<Lot>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            list.add(criteriaBuilder.greaterThan(root.get("endingTime").as(Date.class), datetime));
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaBuilder.and(list.toArray(predicates));
        }, pageable);
        return lots;
    }

    public Lot getLot(Long id) {
        Optional<Lot> lot = lotDao.findById(id);
        if (!lot.isPresent()) {
            return null;
        } else {
            return lot.get();
        }
    }

    @Transactional
    public Lot addLot(String username, LotCommand command) {
        User user = userService.getUser(username);
        if (user == null) {
            return null;
        }

        Lot lot = new Lot();
        lot.setName(command.getName());
        lot.setDescription(command.getDescription());
        lot.setStartingTime(command.getStartingTime());
        lot.setStartingPrice(command.getStartingPrice());
        lot.setEndingTime(command.getEndingTime());

        Lot lotInsert = lotDao.save(lot);

        if (lotInsert == null) {
            return null;
        }

        if (command.getImgSrcs() != null) {
            List<LotImg> lotImgsInsert = this.modifyImgs(lotInsert, command.getImgSrcs());
            lotInsert.setLotImgs(lotImgsInsert);
        }

        UserLot userLot = new UserLot();
        userLot.setUserid(user.getId());
        userLot.setLotid(lotInsert.getId());
        UserLot userLotInsert = userLotDao.save(userLot);

        return lotInsert;
    }

    @Transactional
    public List<LotImg> modifyImgs(Lot lot, List<String> newlotImgsSrcs) {
        if (lot == null) {
            return null;
        }
        if (lot.getLotImgs() != null && !lot.getLotImgs().isEmpty()) {
            lotImgDao.deleteAll(lot.getLotImgs());
        }
        List<LotImg> newLotImgs = newlotImgsSrcs.stream().map(s -> {
            LotImg lotImg = new LotImg();
            lotImg.setImgSrc(s);
            lotImg.setLot(lot);
            return lotImg;
        }).collect(Collectors.toList());
        List<LotImg> lotImgsInsert = lotImgDao.saveAll(newLotImgs);
        return lotImgsInsert;
    }

    @Transactional
    public List<LotImg> modifyImgs(Long lotid, List<String> newlotImgs) {
        Lot lot = this.getLot(lotid);
        return this.modifyImgs(lot, newlotImgs);
    }

}
