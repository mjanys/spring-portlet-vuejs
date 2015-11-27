package cz.janys.portlet.person;

import cz.janys.iface.dto.PersonDto;
import cz.janys.iface.service.PersonService;
import cz.janys.portlet.AbstractController;
import cz.janys.portlet.person.converter.PersonDtoToPtoConverter;
import cz.janys.portlet.person.converter.PersonPtoToDtoConverter;
import cz.janys.portlet.person.pto.PersonPto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.validation.Valid;

import static cz.janys.portlet.Constants.PARAM_ID;
import static cz.janys.portlet.Constants.PARAM_PAGE;
import static cz.janys.portlet.person.PersonConstants.*;

/**
 * @author Martin Janys
 */
@Controller
@RequestMapping("VIEW")
public class PersonController extends AbstractController {

    private static final Logger LOG = Logger.getLogger(PersonController.class);

    @Autowired
    private PersonService service;

    @Autowired
    private PersonPtoToDtoConverter ptoToDto;
    @Autowired
    private PersonDtoToPtoConverter dtoToPto;

    @RenderMapping
    public String defaultView(
            Model model,
            RenderRequest request,
            RenderResponse response) {

        model.addAttribute(ATTR_ITEMS, service.findAllPersons());

        return VIEW_MAIN;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PERSON_DETAIL_PAGE)
    public String detail(
            @RequestParam(PARAM_ID) long id,
            Model model,
            RenderRequest request,
            RenderResponse response) {

        model.addAttribute(ATTR_PERSON_DTO, service.findPersonById(id));

        return VIEW_DETAIL;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PERSON_FORM_CREATE_PAGE)
    public String create(
            Model model,
            RenderRequest request,
            RenderResponse response) {

        if (!model.containsAttribute(ATTR_PERSON_PTO)) {
            model.addAttribute(ATTR_PERSON_PTO, new PersonPto());
        }

        return VIEW_CREATE_FORM;
    }

    @RenderMapping(params = PARAM_PAGE + "=" + PERSON_FORM_EDIT_PAGE)
    public String editForm(
            @RequestParam(PARAM_ID) Long id,
            Model model,
            RenderRequest request,
            RenderResponse response) {

        if (!model.containsAttribute(ATTR_PERSON_PTO)) {
            PersonDto s = service.findPersonById(id);
            PersonPto pto = dtoToPto.convert(s);
            model.addAttribute(ATTR_PERSON_PTO, pto);
        }

        return VIEW_EDIT_FORM;
    }

    @ActionMapping(ACTION_SAVE)
    public void createPerson(
            @Valid @ModelAttribute(ATTR_PERSON_PTO) PersonPto pto,
            BindingResult result,
            ActionRequest request,
            ActionResponse response,
            Model model) {

        if (!result.hasErrors()) {
            PersonDto dto = ptoToDto.convert(pto);

            if (dto.getId() == null) {
                dto = service.createPerson(dto);
                addSuccessMessage(model, "msg-person-saved");
            }
            else {
                dto = service.updatePerson(dto);
                addSuccessMessage(model, "msg-person-created");
            }

            response.setRenderParameter(PARAM_PAGE, PERSON_DETAIL_PAGE);
            response.setRenderParameter(PARAM_ID, dto.getId().toString());
        }
        else {
            addErrorMessage(model, "msg-common-err-form-contains-errors");
            response.setRenderParameter(PARAM_PAGE, PERSON_FORM_CREATE_PAGE);
        }
    }

    @ActionMapping(value = ACTION_DELETE)
    public void deletePerson(
            @RequestParam(PARAM_ID) Long id,
            ActionRequest request,
            ActionResponse response,
            Model model) {

        PersonDto dto = service.findPersonById(id);
        service.deletePersonById(id);

        addSuccessMessage(model, "msg-hello-person-deleted", dto.getName());
    }
}
