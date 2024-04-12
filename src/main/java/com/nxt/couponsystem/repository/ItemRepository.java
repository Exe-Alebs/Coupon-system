package com.nxt.couponsystem.repository;

import com.nxt.couponsystem.data.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByCartId(Long id);

}
