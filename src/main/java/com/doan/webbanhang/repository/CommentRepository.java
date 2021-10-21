package com.doan.webbanhang.repository;

import com.doan.webbanhang.entity.Comment;
import com.doan.webbanhang.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from Comment where idPro = ?1 order by date desc", nativeQuery = true)
    List<Comment> getAllComment(Long id);
}
