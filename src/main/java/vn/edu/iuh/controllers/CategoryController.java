package vn.edu.iuh.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.models.Category;
import vn.edu.iuh.services.CategoryService;

import java.util.List;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String getAllCategory(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "index";
    }
}
