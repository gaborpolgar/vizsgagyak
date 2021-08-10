package org.training360.finalexam.teams;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.training360.finalexam.NotFoundException;
import org.training360.finalexam.players.CreatePlayerCommand;
import org.training360.finalexam.players.Player;
import org.training360.finalexam.players.PlayerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeamService {

    TeamRepository teamRepository;
    ModelMapper modelMapper;
    PlayerRepository playerRepository;

    public List<TeamDTO> listTeams() {
        return teamRepository.findAll()
                .stream()
                .map(t -> modelMapper.map(t, TeamDTO.class)).
                        collect(Collectors.toList());
    }


    public TeamDTO createTeam(CreateTeamCommand command) {
        Team team = new Team(command.getName());
        teamRepository.save(team);
        return modelMapper.map(team, TeamDTO.class);
    }

    @Transactional
    public TeamDTO updateTeamWithExistingPlayer(long id, UpdateWithExistingPlayerCommand command) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        Player player = playerRepository.findById(command.getPlayerId()).orElseThrow(() -> new NotFoundException(command.getPlayerId()));

        if (player.hasNoTeam() && hasOpenedPosition(team, player)) {
            team.addPlayer(player);
        }
        return modelMapper.map(team, TeamDTO.class);
    }

    private boolean hasOpenedPosition(Team team, Player player) {
        return team.getPlayers()
                .stream()
                .filter(p -> p.getPosition() == player.getPosition()).count() < 2;
    }

    @Transactional
    public TeamDTO addNewPlayerToTeam(Long id, CreatePlayerCommand command) {
        Team team = teamRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        Player player = new Player(command.getName(), command.getDateOfBirth(), command.getPosition());
        team.addPlayer(player);
        return modelMapper.map(team, TeamDTO.class);
    }
}
