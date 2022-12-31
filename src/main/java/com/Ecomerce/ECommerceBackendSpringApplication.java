package com.Ecomerce;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.Ecomerce.dao.CategoryRepository;
import com.Ecomerce.dao.ProductRepository;
import com.Ecomerce.entities.Category;
import com.Ecomerce.entities.Product;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class ECommerceBackendSpringApplication implements CommandLineRunner{
	  @Autowired
	    private ProductRepository productRepository;
	    @Autowired
	    private CategoryRepository categoryRepository;
	    //pour collect id from spring data
	    @Autowired
	    private RepositoryRestConfiguration repositoryRestConfiguration;
	    //

	public static void main(String[] args) {
		SpringApplication.run(ECommerceBackendSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//return id of category and product in spring data
        repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);
        //

		categoryRepository.save(new Category(null,"Computers",null,null,null));
        categoryRepository.save(new Category(null,"Printers",null,null,null));
        categoryRepository.save(new Category(null,"Smart phones",null,null,null));
        Random rnd=new Random();
        categoryRepository.findAll().forEach(c->{
            for (int i = 0; i <10 ; i++) {
                Product p=new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100+rnd.nextInt(10000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setCategory(c);
                p.setPhotoName("unknown.png");
                productRepository.save(p);
            }
        });
		
	}

}
