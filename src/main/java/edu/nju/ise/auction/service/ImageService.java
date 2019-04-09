package edu.nju.ise.auction.service;

import edu.nju.ise.auction.dao.ImageDao;
import edu.nju.ise.auction.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;

    public Page<Image> getImageList(Long page, Long size) {
        Pageable pageable = new PageRequest(page.intValue(), size.intValue(), Sort.Direction.ASC, "id");
        Page<Image> images = imageDao.findAll((Specification<Image>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("available").as(Integer.class), Image.AVAILABLE), pageable);
        return images;
    }

    public Image getImage(Long id) {
        return imageDao.findById(id).orElse(null);
    }

    @Transactional
    public Image addImage(String path) {
        Image image = new Image();
        image.setSrc(path);

        Image imageInsert = imageDao.save(image);

        return imageInsert;
    }

    @Transactional
    public List<Image> addImages(List<String> paths) {
        List<Image> results = new ArrayList<>();
        for (String path: paths) {
            Image image = new Image();
            image.setSrc(path);
            results.add(image);
        }
        imageDao.saveAll(results);
        return results;
    }

    @Transactional
    public void deleteImage(Long imageid) {
        imageDao.deleteById(imageid);
    }

}
