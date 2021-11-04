package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Cart;
import com.doan.webbanhang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, CartRepositoryCustom {
    @Query(value = "select * from Cart where idCli = ?1 and idBil is null and idPro = ?2", nativeQuery = true)
    Cart getCartNotOrderFollowClient(Long idCli, Long idPro);

    @Modifying
    @Query(value = "update Cart set idBil = ?1 where id in ?2", nativeQuery = true)
    void updateCart(Long idBil, List<Long> ids);
}
