package edu.nju.ise.auction.service;

import edu.nju.ise.auction.command.ImageOrderCommand;
import edu.nju.ise.auction.dao.ImageDao;
import edu.nju.ise.auction.dao.ImageOrderDao;
import edu.nju.ise.auction.model.Image;
import edu.nju.ise.auction.model.ImageOrder;
import edu.nju.ise.auction.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageOrderService {

    @Autowired
    private ImageOrderDao imageOrderDao;
    @Autowired
    private ImageDao imageDao;
    @Autowired
    private UserService userService;

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

        return orderInsert;
    }

}
