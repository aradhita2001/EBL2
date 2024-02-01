package com.wecp.progressive.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wecp.progressive.entity.Accounts;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer>{
    public List<Accounts> findByCustomerCustomerId(int customerId);
    public List<Accounts> findByOrderByBalance();
}
