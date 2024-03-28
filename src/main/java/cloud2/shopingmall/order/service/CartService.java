package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.entity.Cart;
import cloud2.shopingmall.order.repository.CartItemRepository;
import cloud2.shopingmall.order.repository.CartRepository;
import cloud2.shopingmall.product.entity.Product;
import cloud2.shopingmall.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@RequiredArgsConstructor
@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    @Transactional
    public void addCart(User user, Product product, int amount){
        Cart cart = cartRepository.findCartByUserId(user.getId());
        if(cart == null){
             cart = Cart.createCart(user);
            cartRepository.save(cart);
        }

    }
}
