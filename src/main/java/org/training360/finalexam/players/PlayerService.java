package org.training360.finalexam.players;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    public List<PlayerDTO> listPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(t -> modelMapper.map(t, PlayerDTO.class))
                .collect(Collectors.toList());
    }

    public PlayerDTO createPlayer(CreatePlayerCommand command) {
        Player player = new Player(command.getName(), command.getDateOfBirth(), command.getPosition());
        playerRepository.save(player);
        return modelMapper.map(player, PlayerDTO.class);
    }

}
