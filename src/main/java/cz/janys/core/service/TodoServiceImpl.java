package cz.janys.core.service;

import cz.janys.iface.service.TodoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Martin Janys
 */
@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final Logger LOG = Logger.getLogger(TodoServiceImpl.class);

}
