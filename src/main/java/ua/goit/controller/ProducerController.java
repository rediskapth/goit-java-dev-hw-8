package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.exception.ProducerAlreadyExistsException;
import ua.goit.model.dto.ProducerDto;
import ua.goit.service.ProducerService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/producers")
public class ProducerController {
    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @ModelAttribute
    public ProducerDto getDefaultProducerDto() {
        return new ProducerDto();
    }

    @GetMapping(path = "/findProducers")
    public String findAllProducers(Model model) {
        List<ProducerDto> producers = producerService.findAllProducers();
        model.addAttribute("producers", producers);
        return "findProducers";
    }

    @GetMapping(path = "/form/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createProducerForm() {
        return "createProducerForm";
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createProducer(@ModelAttribute("producerDto") @Valid ProducerDto producerDto, BindingResult bindingResult,
                                   ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("createProducerForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        try {
            producerService.saveOrUpdate(producerDto);
            model.setViewName("createProducer");
            model.setStatus(HttpStatus.CREATED);
        } catch (ProducerAlreadyExistsException ex) {
            model.addObject("message", ex.getMessage());
            model.setViewName("createProducerForm");
        }
        return model;
    }

    @GetMapping(path = "/form/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateProducerForm(@PathVariable ("id") UUID id, Model model) {
        ProducerDto producerDto = producerService.findProducerById(id);
        model.addAttribute("producerDto", producerDto);
        return "updateProducerForm";
    }

    @PostMapping(path = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateProducer(@PathVariable("id") UUID id,
                                       @ModelAttribute("producerDto") @Valid ProducerDto producerDto, BindingResult bindingResult,
                                       ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("updateProducerForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        try {
            producerService.saveOrUpdate(producerDto);
            model.setViewName("updateProducer");
            model.setStatus(HttpStatus.CREATED);
        } catch (ProducerAlreadyExistsException ex) {
            model.addObject("message", ex.getMessage());
            model.setViewName("updateProducerForm");
        }
        return model;
    }

    @GetMapping(path = "/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProducer(@RequestParam("id") UUID id, Model model) {
            ProducerDto producerDto = producerService.findProducerById(id);
        try {
            producerService.delete(producerDto);
        } catch (Exception ex) {
            model.addAttribute("message", "You may delete producer " + producerDto.getName() + " after deleting all producer's products");
            List<ProducerDto> producers = producerService.findAllProducers();
            model.addAttribute("producers", producers);
            return "findProducers";
        }
        return "deleteProducer";
    }
}
