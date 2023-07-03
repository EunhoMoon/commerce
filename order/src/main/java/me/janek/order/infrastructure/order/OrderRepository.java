package me.janek.order.infrastructure.order;

import me.janek.order.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderToken(String orderToken);

    List<Order> findByUserToken(String userToken);

}
