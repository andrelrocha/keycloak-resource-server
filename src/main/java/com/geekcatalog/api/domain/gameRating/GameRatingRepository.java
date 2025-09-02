package com.geekcatalog.api.domain.gameRating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GameRatingRepository extends JpaRepository<GameRating, String>, JpaSpecificationExecutor<GameRating> {

}
