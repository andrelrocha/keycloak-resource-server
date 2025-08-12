package com.geekcatalog.api.service;

import com.geekcatalog.api.domain.game.useCase.CreateGame;
import com.geekcatalog.api.domain.game.useCase.GetGames;
import com.geekcatalog.api.dto.game.GameDTO;
import com.geekcatalog.api.dto.game.GameReturnDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final CreateGame createGame;
    private final GetGames getGames;

    public Page<GameReturnDTO> getGamesPageable(
            Pageable pageable,
            String id,
            String name,
            Integer startYear,
            Integer endYear
    ) {
        return getGames.getGamesPageable(pageable, id, name, startYear, endYear);
    }

    public GameReturnDTO create(GameDTO data) {
        return createGame.create(data);
    }
}
