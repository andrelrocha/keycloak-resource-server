package com.geekcatalog.api.domain.gameRating.useCase;

import com.geekcatalog.api.domain.gameRating.GameRating;
import com.geekcatalog.api.domain.gameRating.GameRatingRepository;
import com.geekcatalog.api.domain.gameRating.specification.GameRatingSpecification;
import com.geekcatalog.api.dto.gameRating.GameRatingReturnDTO;
import com.geekcatalog.api.service.UtilsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetGameRatings {

    private final GameRatingRepository repository;
    private final UtilsService utilsService;

    public Page<GameRatingReturnDTO> getRatingsPageable(
            Pageable pageable,
            String userId,
            String gameId,
            String gameName
    ) {
        // cria specification para userId e gameId
        Specification<GameRating> spec = createSpecification(userId, gameId);

        List<GameRating> filteredBySpec = repository.findAll(spec, Sort.unsorted());

        // filtra por gameName em memória
        List<GameRating> filteredByName = filterByGameName(filteredBySpec, gameName);

        // ordena
        List<GameRating> sorted = sortRatings(filteredByName, pageable);

        // retorna o paginado
        return paginate(sorted, pageable);
    }

    private Specification<GameRating> createSpecification(String userId, String gameId) {
        Map<String, Object> filters = new HashMap<>();
        if (userId != null && !userId.isBlank()) filters.put("userId", userId);
        if (gameId != null && !gameId.isBlank()) filters.put("gameId", gameId);

        return GameRatingSpecification.byFilters(filters);
    }

    private List<GameRating> filterByGameName(List<GameRating> ratings, String gameName) {
        if (gameName == null || gameName.isBlank()) return ratings;

        String normalized = utilsService.normalizeString(gameName);
        return ratings.stream()
                .filter(r -> utilsService.normalizeString(r.getGame().getName()).contains(normalized))
                .collect(Collectors.toList());
    }

    private List<GameRating> sortRatings(List<GameRating> ratings, Pageable pageable) {
        Map<String, Comparator<GameRating>> comparators = Map.of(
                "userId", Comparator.comparing(r -> r.getUser().getId(), Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)),
                "gameId", Comparator.comparing(r -> r.getGame().getId(), Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)),
                "gameName", Comparator.comparing(r -> utilsService.normalizeString(r.getGame().getName()), Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)),
                "rating", Comparator.comparing(GameRating::getRating, Comparator.nullsLast(Comparator.naturalOrder())),
                "createdAt", Comparator.comparing(GameRating::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder()))
        );

        String sortField = pageable.getSort().stream()
                .findFirst()
                .map(Sort.Order::getProperty)
                .orElse("createdAt");

        boolean asc = pageable.getSort().stream()
                .findFirst()
                .map(Sort.Order::isAscending)
                .orElse(false);

        Comparator<GameRating> comparator = comparators.getOrDefault(sortField, comparators.get("createdAt"));

        return asc ? ratings.stream().sorted(comparator).collect(Collectors.toList())
                : ratings.stream().sorted(comparator.reversed()).collect(Collectors.toList());
    }

    private Page<GameRatingReturnDTO> paginate(List<GameRating> ratings, Pageable pageable) {
        int total = ratings.size();
        int start = Math.toIntExact(pageable.getOffset());
        int end = Math.min(start + pageable.getPageSize(), total);

        List<GameRatingReturnDTO> pageContent = ratings.subList(start, end).stream()
                .map(GameRatingReturnDTO::new)
                .collect(Collectors.toList());

        return new PageImpl<>(pageContent, pageable, total);
    }
}
