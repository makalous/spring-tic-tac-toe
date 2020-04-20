package com.example.tictt.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.tictt.dto.GameHistDto;
import com.example.tictt.dto.PlayerDto;
import com.example.tictt.entity.BoardEntity;
import com.example.tictt.error.FieldTaken;
import com.example.tictt.error.ObjectNotFound;
import com.example.tictt.repo.BoardRepository;
import com.example.tictt.service.GameStateService;

@RestController
@RequestMapping("/api/gamestate")
public class GameStateController {

	@Autowired
	public List<BoardRepository> repository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	List<GameStateService> gService = new ArrayList<GameStateService>();
	public int games_counter = 0;

	public GameStateController() {
		gService.add(new GameStateService());
		gService.get(games_counter).constructState();
	}

	@GetMapping("/play")
	public String gameStarted() {
		if (games_counter == 0) {
			games_counter++;
			return gService.get(games_counter - 1).startGame();
		} else {
			games_counter++;
			gService.add(new GameStateService());
			gService.get(games_counter - 1).constructState();
			BoardRepository temp = repository.get(games_counter - 2);
			repository.add(temp);
			return gService.get(games_counter - 1).startGame();
		}

	}

	@GetMapping("/check")
	public PlayerDto checkWinner(@RequestParam int which) {

		try {
			PlayerDto playerDto = modelMapper.map(gService.get(which).checkWinner(), PlayerDto.class);
			return playerDto;
		} catch (Exception e) {
			throw new ObjectNotFound(HttpStatus.NOT_FOUND + ": Object not found!");
		}

	}

	public void add_to_repo(int which, BoardEntity board) {
		repository.get(which).deleteAll();
		repository.get(which).save(board);
	}

	@PostMapping("/next")
	public HttpStatus nextMove(@RequestParam int row, @RequestParam int col, @RequestParam int which) {
		try {
			if (gService.get(which).nextMove(row, col) == null) {
				return HttpStatus.BAD_REQUEST;
			} else {
				gService.get(which).nextMove(row, col);
				BoardEntity temp = new BoardEntity();
				temp = gService.get(which).gState.getBoard();
				add_to_repo(which, temp);
				return HttpStatus.OK;
			}
		} catch (Exception e) {
			throw new FieldTaken(HttpStatus.BAD_REQUEST + ": No such game!");
		}

	}

	@GetMapping("/getplayer")
	public PlayerDto getPlayerById(@RequestParam Integer index, @RequestParam int which) {
		try {
			PlayerDto playerDto = modelMapper.map(gService.get(which).getPlayerById(index), PlayerDto.class);
			return playerDto;
		} catch (Exception e) {
			throw new ObjectNotFound(HttpStatus.NOT_FOUND + ": Object not found!");
		}

	}

	@GetMapping("/checkplayerhist")
	public GameHistDto checkPlayerHist(@RequestParam Integer index, @RequestParam int which) {
		try {
			GameHistDto gameHistDto = modelMapper.map(gService.get(which).returnPlayerHist(index), GameHistDto.class);
			return gameHistDto;
		} catch (Exception e) {
			throw new ObjectNotFound(HttpStatus.NOT_FOUND + ": Object not found!");
		}

	}

}
