package cloud2.shopingmall.product.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.product.dto.*;
import cloud2.shopingmall.product.entity.*;
import org.mapstruct.Mapper;
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
    interface ProductBodyMapper extends EntityMapper<ProductBody, ProductBodyDTO> {


    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface ProductDetailsMapper extends EntityMapper<ProductDetails, ProductDTO> {

    }
}
