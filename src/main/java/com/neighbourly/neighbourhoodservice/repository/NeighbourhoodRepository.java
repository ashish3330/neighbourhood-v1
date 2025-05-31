package com.neighbourly.neighbourhoodservice.repository;

import com.neighbourly.neighbourhoodservice.entity.Neighbourhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NeighbourhoodRepository extends JpaRepository<Neighbourhood, Long> {
}