package org.training360.finalexam.teams;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    List<TeamDTO> listTeams(){
        return teamService.listTeams();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TeamDTO createTeam(@Valid @RequestBody CreateTeamCommand command){
        return teamService.createTeam(command);
    }

}
