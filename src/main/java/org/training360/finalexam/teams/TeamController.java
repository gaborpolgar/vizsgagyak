package org.training360.finalexam.teams;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training360.finalexam.players.CreatePlayerCommand;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<TeamDTO> listTeams(){
        return teamService.listTeams();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command){
        return teamService.createTeam(command);
    }

    @PutMapping("/{id}/players")
    public TeamDTO updateTeamWithExistingPlayer(@PathVariable("id") long id, @RequestBody UpdateWithExistingPlayerCommand command ){
        return teamService.updateTeamWithExistingPlayer(id, command);
    }

    @PostMapping("/{id}/players")
    public TeamDTO addNewPlayerToTeam(@PathVariable("id") Long id, @RequestBody CreatePlayerCommand command){
        return teamService.addNewPlayerToTeam(id, command);
    }

}
