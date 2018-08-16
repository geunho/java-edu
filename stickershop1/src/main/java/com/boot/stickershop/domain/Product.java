package com.boot.stickershop.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
public class Product {
    public Product() { regtime = LocalDateTime.now(); }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String size;
    private Long sales;
    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(targetEntity = ProductCategory.class, fetch=FetchType.EAGER) // EAGER에 해당하는 필드를 함께 join 해서 가져온다.
    @JoinColumn(name="category_id")
    private ProductCategory productCategory;
    private LocalDateTime regtime;
    private LocalDateTime edittime;  // 수정날짜

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 해당 필드가 필요할 떄에만 쿼리가 실행
    private List<ProductFile> productFiles = new ArrayList<>();

    public void addProductFile(ProductFile productFile){
        this.productFiles.add(productFile);
        if(productFile.getProduct()!=this){
            productFile.setProduct(this);
        }
    }

}
