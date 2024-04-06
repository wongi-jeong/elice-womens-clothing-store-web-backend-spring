package cloud2.shopingmall.product.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.product.dto.*;
import cloud2.shopingmall.product.entity.*;
import org.mapstruct.*;


public interface ProductMainMapper {

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface CategoryMapper extends EntityMapper<Category, CategoryDTO> {

    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface CategoryProductDisplayMapper extends EntityMapper<CategoryProduct, CategoryProductDTO> {

    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductMapper extends EntityMapper<Product, ProductDTO> {

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateFromDto(ProductDTO dto, @MappingTarget Product entity);

    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductWithDetailsAndBodiesMapper extends EntityMapper<Product, ProductDTO.ProductWithDetailsAndBodiesDTO> {

        @Mapping(source = "productBodies", target = "productBodyDTOList")
        @Mapping(source = "productImages", target = "productImageDTOList")
        @Mapping(source = "productDetails", target = "productDetailsDTOList")
        ProductDTO.ProductWithDetailsAndBodiesDTO toDto(Product product);

        @Mapping(source = "productBodyDTOList", target = "productBodies")
        @Mapping(source = "productDetailsDTOList", target = "productDetails")
        @Mapping(source = "productImageDTOList", target = "productImages")
        Product toEntity(ProductDTO.ProductWithDetailsAndBodiesDTO productWithDetailsAndBodiesDTO);

    }
    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductBodyMapper extends EntityMapper<ProductBody, ProductBodyDTO> {

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateFromDto(ProductBodyDTO dto, @MappingTarget ProductBody entity);
    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductImageMapper extends EntityMapper<ProductImage, ProductImageDTO> {

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateFromDto(ProductImageDTO dto, @MappingTarget ProductImage entity);
    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductDetailsMapper extends EntityMapper<ProductDetails, ProductDetailsDTO> {

        @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        void updateFromDto(ProductDetailsDTO dto, @MappingTarget ProductDetails entity);

    }
}
