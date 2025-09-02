package com.geekcatalog.api.domain.gameRating;

import com.geekcatalog.api.domain.game.Game;
import com.geekcatalog.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "game_rating")
@Entity(name = "GameRating")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class GameRating {
    @Id
    @Column(name = "id", nullable = false, length = 36, updatable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", referencedColumnName = "id", nullable = false)
    private Game game;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.id = UUID.randomUUID().toString().toUpperCase();
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
