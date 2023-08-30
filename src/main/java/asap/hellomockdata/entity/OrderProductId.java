package asap.hellomockdata.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderProductId implements Serializable {

    private Order order;
    private Long productId;

    private OrderProductId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderProductId that)) {
            return false;
        }
        return Objects.equals(order.getId(), that.order.getId())
            && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order.getId(), productId);
    }
}
