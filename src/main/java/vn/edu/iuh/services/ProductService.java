package vn.edu.iuh.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.edu.iuh.models.Product;
import vn.edu.iuh.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findByCategoryId(Long id){
        return productRepository.getAllByCategory_Id(id);
    }
    public void createProduct(Product product){
        productRepository.save(product);
    }
    public void updateProduct(Long id,Product product){
        Product existingProduct = productRepository.findById(id).orElseThrow(()->new RuntimeException("Can't find Product"));
        existingProduct.setProductName(product.getProductName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }
}
