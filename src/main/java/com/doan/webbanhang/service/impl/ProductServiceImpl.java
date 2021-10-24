package com.doan.webbanhang.service.impl;


import com.doan.webbanhang.dto.BranchCategoryDTO;
import com.doan.webbanhang.dto.ProductDTO;
import com.doan.webbanhang.dto.SearchTermDTO;
import com.doan.webbanhang.entity.*;
import com.doan.webbanhang.repository.*;
import com.doan.webbanhang.service.ProductService;
import com.doan.webbanhang.service.WareHouseService;
import com.doan.webbanhang.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    private CommentRepository commentRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              BrandRepository brandRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Object> getBrandAndCategory() {
        List<Object> resObjects = new ArrayList<>();
        List<Category> categories = categoryRepository.findAll();
        List<Brand> brand = brandRepository.findAll();
        resObjects.add(categories);
        resObjects.add(brand);
        return resObjects;
    }

    public List<Brand> getBrand(Long idCat) {
        return brandRepository.getBrand(idCat);
    }

    public Product save(Product product, MultipartFile multipartFile) throws IOException {
        Product check = productRepository.findByName(product.getNamePro());
        if (product.getId() != null) {
            if (check != null && check.getId() != null && !check.getId().equals(product.getId())) {
                return null;
            }
            if (multipartFile == null) {
                Product temp = new Product();
                temp.setNamePro(product.getNamePro());
                temp.setPrice(product.getPrice());
                temp.setIdCat(product.getIdCat());
                temp.setIdBra(product.getIdBra());
                temp.setDescription(product.getDescription());
                temp.setScreen(product.getScreen());
                temp.setOs(product.getOs());
                temp.setRam(product.getRam());
                temp.setBattery(product.getBattery());
                temp.setDate(product.getDate());
                temp.setStatus(product.getStatus());
                productRepository.updateOne(temp.getNamePro(), temp.getPrice(), temp.getIdCat(), temp.getIdBra(), temp.getDescription(),
                        temp.getScreen(), temp.getOs(), temp.getRam(), temp.getBattery(), temp.getDate(), temp.getStatus(), product.getId());
            } else {
                product.setImage(Common.compressBytes(multipartFile.getBytes()));
                product = productRepository.save(product);
            }
        } else {
            product.setRate((double) 0);
            if (check != null && check.getId() != null) {
                return null;
            }
            if (multipartFile != null) {
                product.setImage(Common.compressBytes(multipartFile.getBytes()));
            }
            product = productRepository.save(product);
        }
        return product;
    }

    public Page<Product> loadAllData(SearchTermDTO searchTermDTO, Pageable pageable) {
        return productRepository.loadAllData(searchTermDTO, pageable);
    }

    public List<Product> loadAll() {
        return productRepository.loadAllDataForReceipt();
    }

    @Override
    public List<ProductDTO> loadProductDefaultForWelcome(List<BranchCategoryDTO> listID) {
        return productRepository.loadProductDefaultForWelcome(listID);
    }

    @Override
    public Double submitComment(Comment comment) {
        comment.setDate(LocalDate.now());
        List<Double> list = productRepository.listRate(comment.getIdPro());
        double total = list.stream().reduce((double) 0, Double::sum) + comment.getRate();
        double rateAverage = total / (list.size() + 1);
        productRepository.updateRate(rateAverage, comment.getIdPro());
        commentRepository.save(comment);
        return rateAverage;
    }

    @Override
    public ProductDTO findById(Long id) {
        return productRepository.findOneById(id);
    }

    @Override
    public List<Comment> getAllComment(Long id) {
        return commentRepository.getAllComment(id);
    }


}
