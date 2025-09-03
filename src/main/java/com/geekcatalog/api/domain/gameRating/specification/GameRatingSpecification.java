package com.geekcatalog.api.domain.gameRating.specification;

import com.geekcatalog.api.domain.gameRating.GameRating;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameRatingSpecification {

    public static Specification<GameRating> byFilters(Map<String, Object> filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters == null) return cb.conjunction();

            if (filters.containsKey("userId")) {
                predicates.add(cb.equal(root.get("user").get("id"), filters.get("userId")));
            }

            if (filters.containsKey("gameId")) {
                predicates.add(cb.equal(root.get("game").get("id"), filters.get("gameId")));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
