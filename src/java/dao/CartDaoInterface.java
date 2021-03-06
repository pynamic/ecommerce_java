/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.math.BigDecimal;
import java.util.List;
import model.Cart;

/**
 *
 * @author vdtru
 */
public interface CartDaoInterface {

    public List<Cart> getMany();

    public Cart getCartByUserAndProductId(int userId, int productId);

    public List<Cart> getCartByUserId(int userId);

    public boolean createCart(Cart cart);

    public boolean insertOrUpdateCart(int userId, int productId, BigDecimal discountPercent);

    public boolean removeCartByUserAndProductId(int userId, int productId);

    public boolean cleanCart(int userId);

    public boolean updateCart(Cart cart);

}
