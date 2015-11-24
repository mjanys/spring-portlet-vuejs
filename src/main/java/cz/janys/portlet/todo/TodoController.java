package cz.janys.portlet.todo;

import cz.janys.iface.dto.TodoDto;
import cz.janys.iface.service.TodoService;
import cz.janys.portlet.AbstractController;
import cz.janys.portlet.mvc.RequestJson;
import cz.janys.portlet.mvc.ResponseJson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import java.util.Collections;
import java.util.List;

import static cz.janys.portlet.Constants.PARAM_ID;
import static cz.janys.portlet.todo.TodoConstants.TODOS_RESOURCE;
import static cz.janys.portlet.todo.TodoConstants.TODO_RESOURCE;
import static cz.janys.portlet.todo.TodoConstants.VIEW_MAIN;

/**
 * @author Martin Janys
 */
@Controller
@RequestMapping("VIEW")
public class TodoController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(TodoController.class);

    @Autowired
    private TodoService service;

    @RenderMapping
    public String view() {
        return VIEW_MAIN;
    }

    @ResourceMapping(TODO_RESOURCE)
    @RequestMapping(method = RequestMethod.GET, params = PARAM_ID)
    @ResponseJson
    public TodoDto getTodo() {
        return new TodoDto();
    }

    @ResourceMapping(TODOS_RESOURCE)
    @RequestMapping(method = RequestMethod.GET)
    @ResponseJson
    public List<TodoDto> getTodos() {
        List<TodoDto> data = service.load();
        return data != null ? data : Collections.<TodoDto>emptyList();
    }

    @ResourceMapping(TODOS_RESOURCE)
    @RequestMapping(method = RequestMethod.POST)
    public void getTodos(@RequestJson List<TodoDto> todos) {
        service.save(todos);
    }

}
