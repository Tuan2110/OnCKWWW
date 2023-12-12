package vn.edu.iuh.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.models.Category;
import vn.edu.iuh.models.Product;
import vn.edu.iuh.services.CategoryService;
import vn.edu.iuh.services.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    @GetMapping("/{categoryId}")
    public String getProductsByCategory(@PathVariable("categoryId")long categoryId, Model model, HttpSession session){
        session.setAttribute("category",categoryService.findById(categoryId));
        List<Product> products = productService.findByCategoryId(categoryId);
        model.addAttribute("products",products);
        return "list-products";
    }
    @GetMapping("/add")
    public String addForm(){
        return "add-product";
    }
    @PostMapping("/add")
    public String addProduct(Product product,HttpSession session){
        Category category = (Category) session.getAttribute("category");
        product.setCategory(category);
        productService.createProduct(product);
        session.invalidate();
        return "redirect:/products/"+category.getId();
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id")long id,Model model, HttpSession session){
        Product product = productService.findById(id);
        session.setAttribute("category",product.getCategory());
        model.addAttribute("product",product);
        return "update-product";
    }
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id")long id,Product product, HttpSession session){
        Category category = (Category) session.getAttribute("category");
        product.setCategory(category);
        productService.updateProduct(id,product);
        return "redirect:/products/"+category.getId();
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")long id, HttpSession session){
        Category category = (Category) session.getAttribute("category");
        productService.deleteProduct(id);
        return "redirect:/products/"+category.getId();
    }
}
