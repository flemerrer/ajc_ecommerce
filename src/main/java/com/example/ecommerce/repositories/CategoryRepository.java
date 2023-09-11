package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Boardgame;
import com.example.ecommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findTopBySizeOrderBySizeDesc(int i);

    /*List<Boardgame> findBy();*/

}
