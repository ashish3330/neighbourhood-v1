package com.neighbourly.neighbourhoodservice.repository;


import com.neighbourly.neighbourhoodservice.entity.UserNeighbourhood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserNeighbourhoodRepository extends JpaRepository<UserNeighbourhood, Long> {
    Optional<UserNeighbourhood> findByUserIdAndNeighbourhoodId(Long userId, Long neighborhoodId);

    @Query("SELECT un.neighbourhoodId FROM UserNeighbourhood un WHERE un.userId = :userId")
    List<Long> findNeighbourhoodIdsByUserId(Long userId);
}