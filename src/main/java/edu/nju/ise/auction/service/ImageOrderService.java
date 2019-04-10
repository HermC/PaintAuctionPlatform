package edu.nju.ise.auction.service;

import edu.nju.ise.auction.command.ImageOrderCommand;
import edu.nju.ise.auction.dao.ImageDao;
import edu.nju.ise.auction.dao.ImageOrderDao;
import edu.nju.ise.auction.model.Image;
import edu.nju.ise.auction.model.ImageOrder;
import edu.nju.ise.auction.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.Map;

@Service
public class ImageOrderService {

    private static final Logger logger = LoggerFactory.getLogger(ImageOrderService.class);

    @Autowired
    private ImageOrderDao imageOrderDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @Value("${server.address}")
    private String serverAddress;
    @Value("${server.port}")
    private String serverPort;

    @Transactional
    public ImageOrder addOrder(String username, ImageOrderCommand command) {
        Image image = imageDao.findById(command.getImageid()).orElse(null);
        if (image == null) {
            return null;
        }
        if (!image.getAvailable().equals(Image.AVAILABLE)) {
            return null;
        }
        User user = userService.getUser(username);
        if (user == null) {
            return null;
        }
        ImageOrder order = new ImageOrder();
        order.setUserid(user.getId());
        order.setImageid(command.getImageid());
        order.setRecipient(command.getRecipient());
        order.setPhone(command.getPhoneNumber());
        order.setAddress(command.getAddress());

        ImageOrder orderInsert = imageOrderDao.save(order);

        image.setAvailable(Image.ORDERED);
        imageDao.save(image);

        try {
            Map<String, Object> params = new HashMap<>();
            params.put("username", username);
            params.put("recipient", command.getRecipient());
            params.put("phone", command.getPhoneNumber());
            params.put("address", command.getAddress());
            params.put("src", "http://" + serverAddress + ":" + serverPort + "/images/" + image.getSrc());
            mailService.sendTemplateMail("公益订单", params);
            return orderInsert;
        } catch (Exception e) {
            logger.error(e.getMessage());

            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return null;
        }
    }

}
