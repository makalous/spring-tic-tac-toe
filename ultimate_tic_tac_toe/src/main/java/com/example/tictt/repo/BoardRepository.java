package com.example.tictt.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.tictt.entity.BoardEntity;
 
@Repository
public interface BoardRepository extends CrudRepository<BoardEntity, Long>{

	
}
