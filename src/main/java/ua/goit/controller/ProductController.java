package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.controller.propertyEditor.ProducerDtoPropertyEditor;
import ua.goit.model.dto.ProducerDto;
import ua.goit.model.dto.ProductDto;
import ua.goit.service.ProducerService;
import ua.goit.service.ProductService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/products")
public class ProductController {
    private final ProductService productService;
    private final ProducerService producerService;

    @Autowired
    public ProductController(ProductService productService, ProducerService producerService) {
        this.productService = productService;
        this.producerService = producerService;
    }

    @ModelAttribute
    public ProductDto getDefaultProductDto() {
        return new ProductDto();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(ProducerDto.class, new ProducerDtoPropertyEditor());
    }

    @GetMapping(path = "/findProducts")
    public String findAllProducts(Model model) {
        List<ProductDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "findProducts";
    }

    @GetMapping(path = "/form/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createProductForm(Model model) {
        List<ProducerDto> producers = producerService.findAllProducers();
        model.addAttribute("producers", producers);
        return "createProductForm";
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView createProduct(@ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult bindingResult,
                                      ModelAndView model) {
        if (bindingResult.hasErrors()) {
            List<ProducerDto> producers = producerService.findAllProducers();
            model.addObject("producers", producers);
            model.setViewName("createProductForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.saveOrUpdate(productDto);
        model.setViewName("createProduct");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @GetMapping(path = "/form/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateProductForm(@PathVariable("id") UUID id, Model model) {
        ProductDto productDto = productService.findProductById(id);
        List<ProducerDto> producers = producerService.findAllProducers();
        model.addAttribute("producers", producers);
        model.addAttribute("productDto", productDto);
        return "updateProductForm";
    }

    @PostMapping(path = "/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateProduct(@PathVariable("id") UUID id,
                                      @ModelAttribute("productDto") @Valid ProductDto productDto, BindingResult bindingResult,
                                      ModelAndView model) {
        if (bindingResult.hasErrors()) {
            List<ProducerDto> producers = producerService.findAllProducers();
            model.addObject("producers", producers);
            model.setViewName("updateProductForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        productService.saveOrUpdate(productDto);
        model.setViewName("updateProduct");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @GetMapping(path = "/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@RequestParam("id") UUID id, ModelAndView model) {
        ProductDto productDto = productService.findProductById(id);
        productService.delete(productDto);
        model.addObject("id", id);
        return "deleteProduct";
    }
}
