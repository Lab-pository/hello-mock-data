package asap.hellomockdata.repository;

import asap.hellomockdata.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
