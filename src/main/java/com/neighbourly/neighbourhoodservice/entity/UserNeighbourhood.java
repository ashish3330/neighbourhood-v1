package com.neighbourly.neighbourhoodservice.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "user_neighbourhood")
public class UserNeighbourhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "neighbourhood_id", nullable = false)
    private Long neighbourhoodId;

    @Column(nullable = false)
    private Instant joinedAt = Instant.now();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getNeighbourhoodId() { return neighbourhoodId; }
    public void setNeighbourhoodId(Long neighbourhoodId) { this.neighbourhoodId = neighbourhoodId; }

    public Instant getJoinedAt() { return joinedAt; }
    public void setJoinedAt(Instant joinedAt) { this.joinedAt = joinedAt; }
}