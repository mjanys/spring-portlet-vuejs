package cz.janys.core.service;

import cz.janys.iface.dto.TodoDto;
import cz.janys.iface.service.TodoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Janys
 */
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final Logger LOG = Logger.getLogger(TodoServiceImpl.class);

    private static List<TodoDto> TODOS = new ArrayList<TodoDto>();

    @Override
    public List<TodoDto> load() {
        return TODOS;
    }

    @Override
    public void save(List<TodoDto> todos) {
        TODOS = todos;
    }
}
