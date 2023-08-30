package asap.hellomockdata.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends OrderBaseEntity {

    @Id
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "total_price", nullable = false)
    private String totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type", length = 10, nullable = false)
    private OrderType orderType;

    protected Order() {
    }

    public Order(
        final Long id,
        final Long userId,
        final List<OrderProduct> orderProducts,
        final String totalPrice,
        final OrderType orderType
    ) {
        this.id = id;
        this.userId = userId;
        this.orderProducts = orderProducts;
        this.totalPrice = totalPrice;
        this.orderType = orderType;
    }

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public List<OrderProduct> getOrderProducts() {
        return this.orderProducts;
    }

    public String getTotalPrice() {
        return this.totalPrice;
    }

    public OrderType getOrderType() {
        return this.orderType;
    }

}
