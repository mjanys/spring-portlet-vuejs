package cz.janys.iface.service;

import cz.janys.iface.dto.TodoDto;

import java.util.List;

/**
 * @author Martin Janys
 */
public interface TodoService {
    List<TodoDto> load();
    void save(List<TodoDto> todos);
}
