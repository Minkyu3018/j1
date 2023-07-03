package org.zerock.j1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.j1.domain.Todo;
import org.zerock.j1.dto.PageResponseDTO;
import org.zerock.j1.dto.TodoDTO;

@Transactional
public interface TodoService {
    
    //step1.
    PageResponseDTO<TodoDTO> getList();

    //step2.
    TodoDTO register(TodoDTO dto);
}
