package cloud2.shopingmall.product.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.product.dto.*;
import cloud2.shopingmall.product.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


public interface ProductMainMapper {

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface CategoryMapper extends EntityMapper<Category, CategoryDTO> {

    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface CategoryProductDisplayMapper extends EntityMapper<CategoryProduct, CategoryProductDTO> {

    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductMapper extends EntityMapper<Product, ProductDTO> {

    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductWithDetailsAndBodiesMapper extends EntityMapper<Product, ProductDTO.ProductWithDetailsAndBodiesDTO> {

        @Mapping(source = "productBodies", target = "productBodyDTOList")
        @Mapping(source = "productDetails", target = "productDetailsDTOList")
        ProductDTO.ProductWithDetailsAndBodiesDTO toDto(Product product);

        @Mapping(source = "productBodyDTOList", target = "productBodies", ignore = true)
        @Mapping(source = "productDetailsDTOList", target = "productDetails", ignore = true)
        Product toEntity(ProductDTO.ProductWithDetailsAndBodiesDTO productWithDetailsAndBodiesDTO);

    }
    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductBodyMapper extends EntityMapper<ProductBody, ProductBodyDTO> {


    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductDetailsMapper extends EntityMapper<ProductDetails, ProductDTO> {

    }
}
