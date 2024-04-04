package cloud2.shopingmall.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;



    public ProductDTO(String name, String description, String imageUrl) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductWithDetailsAndBodiesDTO{

        private Long id;
        private String name;
        private String description;
        private String imageUrl;
        private List<ProductBodyDTO> productBodyDTOList;
        private List<ProductDetailsDTO> productDetailsDTOList;

    }

}
