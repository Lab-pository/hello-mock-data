package asap.hellomockdata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.math.BigInteger;
import java.time.Instant;

@Entity
@Table(name = "payment", indexes = {@Index(name = "index_order_id", columnList = "order_id")})
public class Payment extends BaseEntity {

    @Id
    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "purchased_money", nullable = false)
    private BigInteger purchasedMoney;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "pay_type", length = 16, nullable = false)
    private PaymentType payType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "pay_status", length = 16, nullable = false)
    private PaymentStatus payStatus;

    protected Payment() {
    }

    public Payment(
        final Long paymentId,
        final Long userId,
        final Long orderId,
        final BigInteger purchasedMoney,
        final PaymentType payType,
        final PaymentStatus payStatus
    ) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.orderId = orderId;
        this.purchasedMoney = purchasedMoney;
        this.payType = payType;
        this.payStatus = payStatus;
    }

    public Long getPaymentId() {
        return this.paymentId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public BigInteger getPurchasedMoney() {
        return this.purchasedMoney;
    }

    public PaymentType getPayType() {
        return this.payType;
    }

    public PaymentStatus getPayStatus() {
        return this.payStatus;
    }

    public Instant getCreatedAt() {
        return super.createdAt;
    }

    public Instant getUpdatedAt() {
        return super.updatedAt;
    }
}
