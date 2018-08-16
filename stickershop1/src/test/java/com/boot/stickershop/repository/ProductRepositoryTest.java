package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.ProductFile;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.repository.ProductRepository;
import com.boot.stickershop.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest // JPA에 대한 것만 테스트 하겠다는 annotation. 모든 bean을 등록하진 않으므로 실행속도 빨라짐
public class ProductRepositoryTest {
	@Autowired
	ProductRepository productRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getProducts() throws Exception{

		List<Product> products = productRepository.fetchProducts(); // productRepository.findAll(); 과 비교해본다. findAll()을 하는 순간 아래 중첩 for문을 돌면서 계속 쿼리가 날아간다..
//    List<Product> products = productRepository.findAll();
		for(Product p : products){
			System.out.println("===============================");
			System.out.println(p.getName());
			System.out.println("--");
			System.out.println(p.getProductCategory().getName());
			System.out.println("**");
			List<ProductFile> productFiles = p.getProductFiles();
			for(ProductFile pf : productFiles){
				System.out.println(pf.getFileName());
			}
			System.out.println("===============================");
		}

	}

}
