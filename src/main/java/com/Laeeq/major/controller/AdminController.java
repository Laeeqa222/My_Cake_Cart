package com.Laeeq.major.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.Laeeq.major.dto.ProductDTO;
import com.Laeeq.major.model.Category;
import com.Laeeq.major.model.Product;
import com.Laeeq.major.service.CategoryService;
import com.Laeeq.major.service.ProductService;

@Controller
public class AdminController {
    public static String uploadDir =System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    @Autowired
    CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCatAdd(@PathVariable int id){
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model){
        Optional<Category> cat=categoryService.updateCategoryById(id);
        if(cat.isPresent()){
            model.addAttribute("category", cat.get());
            return "categoriesAdd";
        }
        else{
            return "404";
        }
    }

    //Product Section
    @GetMapping("/admin/products")
    public String getproduct(Model model){
        model.addAttribute("products",productService.getAllProduct() );
        return "products";
    }
    @GetMapping("/admin/products/add")
    public String addProduct(Model model){
        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String postProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
                    @RequestParam("productImage")MultipartFile file,
                    @RequestParam("imgName")String imgName) throws IOException{
                        Product product =new Product();
                        product.setId(productDTO.getId());
                        product.setName(productDTO.getName());
                        product.setCategory(categoryService.updateCategoryById(productDTO.getCategoryId()).get());
                        product.setPrice(productDTO.getPrice());
                        product.setWeight(productDTO.getWeight());
                        product.setDescription(productDTO.getDescription());
                        String imgUUID;
                        if(!file.isEmpty()){
                            imgUUID=file.getOriginalFilename();
                            Path fileNameAndPath=Paths.get(uploadDir,imgUUID);
                            try {
                                Files.write(fileNameAndPath, file.getBytes());
                            } catch (IOException e) {
                               e.printStackTrace();
                            }
                        }else{
                            imgUUID=imgName;
                        }
                        product.setImageName(imgUUID);
                        productService.addProduct(product);
                        return "redirect:/admin/products";
                    }
                    //delete
                    @GetMapping("/admin/product/delete/{id}")
                    public String deleteProduct(@PathVariable long id){
                       productService.removeProduct(id);
                        return "redirect:/admin/products";
                    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable long id, Model model){
        Product product=productService.findProductById(id).get();
        ProductDTO productDTO =new ProductDTO();
                        productDTO.setId(product.getId());
                        productDTO.setName(product.getName());
                        productDTO.setPrice(product.getPrice());
                        productDTO.setWeight(product.getWeight());
                        productDTO.setImageName(product.getImageName());
                        productDTO.setDescription(product.getDescription());
                        productDTO.setCategoryId(product.getCategory().getId());
                        model.addAttribute("categories", categoryService.getAllCategory());
                        model.addAttribute("productDTO",productDTO);
        return "productsAdd";
    }
}
