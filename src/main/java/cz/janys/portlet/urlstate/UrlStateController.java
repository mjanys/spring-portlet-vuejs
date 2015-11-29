package cz.janys.portlet.urlstate;

import cz.janys.iface.service.TodoService;
import cz.janys.portlet.AbstractController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import static cz.janys.portlet.urlstate.UrlStateConstants.VIEW_MAIN;

/**
 * @author Martin Janys
 */
@Controller
@RequestMapping("VIEW")
public class UrlStateController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(UrlStateController.class);

    @Autowired
    private TodoService service;

    @RenderMapping
    public String view() {
        return VIEW_MAIN;
    }


}
