package cloud2.shopingmall.product.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.product.dto.*;
import cloud2.shopingmall.product.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ProductMainMapper {

    interface CategoryMapper extends EntityMapper<CategoryEntity, CategoryDTO> {

    }

    interface CategoryProductDisplayMapper extends EntityMapper<CategoryProductDisplayEntity, CategoryProductDisplayDTO> {

    }

    interface ProductDisplayMapper extends EntityMapper<ProductDisplayEntity, ProductDisplayDTO> {

    }

    interface ProductDisplayImageMapper extends EntityMapper<ProductDisplayImageEntity, ProductDisplayImageDTO> {


    }

    interface ProductMapper extends EntityMapper<ProductEntity, ProductDTO> {

    }
}
