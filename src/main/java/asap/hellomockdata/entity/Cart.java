package asap.hellomockdata.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

    @Id
    @Column(name = "cart_id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartProducts = new ArrayList<>();

    protected Cart() {
    }

    public Cart(final Long id, final Long userId) {
        this(id, userId, new ArrayList<>());
    }

    public Cart(final Long id, final Long userId, final List<CartProduct> cartProducts) {
        this.id = id;
        this.userId = userId;
        this.cartProducts = cartProducts;
    }

    public Long getId() {
        return this.id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public List<CartProduct> getCartProducts() {
        return this.cartProducts;
    }

    public Instant getCreatedAt() {
        return super.createdAt;
    }

    public Instant getUpdatedAt() {
        return super.updatedAt;
    }

}
