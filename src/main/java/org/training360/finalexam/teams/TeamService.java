package org.training360.finalexam.teams;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    TeamRepository teamRepository;
    ModelMapper modelMapper;

    public TeamService(TeamRepository teamRepository, ModelMapper modelMapper) {
        this.teamRepository = teamRepository;
        this.modelMapper = modelMapper;
    }

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
}
