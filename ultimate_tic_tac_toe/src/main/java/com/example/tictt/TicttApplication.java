package com.example.tictt;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.tictt.entity.BoardEntity;
import com.example.tictt.entity.GameStateEntity;
import com.example.tictt.service.GameStateService;

@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@Configuration
public class TicttApplication {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}

	// Consider defining a bean of type 'java.util.List' in your configuration.
	@Bean
	public List<GameStateService> list() {
		List<GameStateService> list = new ArrayList<GameStateService>();
		return list;
	}

	// Consider defining a bean of type 'com.example.tictt.entity.GameStateEntity'
	// in your configuration.
	@Bean
	public GameStateEntity gse() {
		GameStateEntity gse = new GameStateEntity();
		return gse;
	}

	// Consider defining a bean of type 'com.example.tictt.service.BoardService' in
	// your configuration.
	/*
	 * @Bean public BoardService bs() { BoardService bs = new BoardService(); return
	 * bs; }
	 */

	@Bean
	public BoardEntity be() {
		BoardEntity be = new BoardEntity();
		return be;
	}

	// private static final Logger log =
	// LoggerFactory.getLogger(TicttApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(TicttApplication.class, args);
	}

}
