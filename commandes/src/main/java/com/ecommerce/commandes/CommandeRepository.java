package com.ecommerce.order;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CommandeRepository extends JpaRepository<Commande,Integer> {
}
