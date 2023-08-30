package asap.hellomockdata.entity;

import java.io.Serializable;
import java.util.Objects;

public class CartProductId implements Serializable {

    private Cart cart;
    private Product product;

    protected CartProductId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartProductId that)) {
            return false;
        }
        return Objects.equals(cart.getId(), that.cart.getId())
            && Objects.equals(product.getId(), that.product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart.getId(), product.getId());
    }
}
