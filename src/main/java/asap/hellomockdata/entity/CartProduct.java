package asap.hellomockdata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_product")
@IdClass(CartProductId.class)
public class CartProduct {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    protected CartProduct() {
    }

    public CartProduct(final Cart cart, final Product product, final Long quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    void setCart(final Cart cart) {
        this.cart = cart;
    }
}
