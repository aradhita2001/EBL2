package com.wecp.progressive.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Customers;


@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    public Optional<Customers> findByNameAndEmail(String name, String email);
}
