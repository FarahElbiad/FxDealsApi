package com.example.demo.Repository;

import com.example.demo.Model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DealRepository extends JpaRepository<Deal,Long>{
    //JpaRepository gives us built-in methods like save(), findAll(), delete().
    //findByDealUniqueId allows us to check duplicates before saving.
    Optional<Deal> findByDealUniqueId(String dealUniqueId);
    //Using Optional forces you to think about the “not found” case.
}
