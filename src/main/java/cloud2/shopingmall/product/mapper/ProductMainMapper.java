package cloud2.shopingmall.product.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.product.dto.*;
import cloud2.shopingmall.product.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductMainMapper {

    interface CategoryMapper extends EntityMapper<Category, CategoryDTO> {

    }

    interface CategoryProductDisplayMapper extends EntityMapper<CategoryProductDisplay, CategoryProductDisplayDTO> {

    }

    interface ProductDisplayMapper extends EntityMapper<ProductDisplay, ProductDisplayDTO> {

    }

    interface ProductDisplayImageMapper extends EntityMapper<ProductDisplayImage, ProductDisplayImageDTO> {


    }

    interface ProductMapper extends EntityMapper<Product, ProductDTO> {

    }
}
