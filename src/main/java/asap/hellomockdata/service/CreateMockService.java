package asap.hellomockdata.service;

import static asap.hellomockdata.utils.GenerateProductUtils.adjective;
import static asap.hellomockdata.utils.GenerateProductUtils.descriptions;
import static asap.hellomockdata.utils.GenerateProductUtils.nouns;
import static asap.hellomockdata.utils.GenerateProductUtils.prices;
import static java.util.stream.Collectors.toList;

import asap.hellomockdata.entity.Cart;
import asap.hellomockdata.entity.Order;
import asap.hellomockdata.entity.OrderProduct;
import asap.hellomockdata.entity.OrderType;
import asap.hellomockdata.entity.Product;
import asap.hellomockdata.entity.User;
import asap.hellomockdata.entity.UserType;
import asap.hellomockdata.infra.IdGenerator;
import asap.hellomockdata.repository.CartRepository;
import asap.hellomockdata.repository.OrderRepository;
import asap.hellomockdata.repository.ProductRepository;
import asap.hellomockdata.repository.UserRepository;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CreateMockService {

    private static final String RAW_PASSWORD = "123456789";
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final IdGenerator idGenerator;
    private final PasswordEncoder passwordEncoder;

    public CreateMockService(
        final CartRepository cartRepository,
        final ProductRepository productRepository,
        final UserRepository userRepository,
        final OrderRepository orderRepository,
        final IdGenerator idGenerator,
        final PasswordEncoder passwordEncoder
    ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.idGenerator = idGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    public void createOrdersMock(final int size, final Long userId) {
        int productIdx = 0;
        final List<Order> orders = new ArrayList<>();
        final List<Product> content = productRepository.findOne(PageRequest.of(0, 20)).getContent();

        for (int i = 0; i < size; i++) {
            final Product product = content.get(productIdx);
            final Long productId = product.getId();
            final String productName = product.getName();
            final String productPrice = product.getPrice();
            final long orderId = idGenerator.generate();
            final Order order = new Order(orderId, userId, new ArrayList<>(), productPrice,
                OrderType.SUCCESS);
            final OrderProduct orderProduct = new OrderProduct(
                order,
                productId,
                productName,
                productPrice,
                1L
            );
            order.getOrderProducts().add(orderProduct);
            orders.add(order);
            productIdx = (productIdx + 1 >= content.size()) ? 0 : productIdx + 1;
        }
        orderRepository.saveAll(orders);
    }

    public void createProductsMock(final int size) {
        int priceIdx = 0;
        int descriptionIdx = 0;
        int adjIdx = 0;
        int nameIdx = 0;
        int randomMinutesIdx = 0;
        final List<Product> products = new ArrayList<>();
        final List<Long> randomMinuets = LongStream.rangeClosed(1, 100).boxed().collect(toList());
        Collections.shuffle(randomMinuets);

        for (int i = 0; i < size; i++) {
            final long id = idGenerator.generate();
            final String productName = adjective[adjIdx] + nouns[nameIdx];
            final String price = prices.get(priceIdx);

            final LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC")).withNano(0).withSecond(0);
            final LocalDateTime start = now.minusDays(1)
                .plusMinutes(randomMinuets.get(randomMinutesIdx));
            final LocalDateTime end = now.plusDays(7)
                .minusMinutes(randomMinuets.get(randomMinutesIdx));

            final Product product = new Product(
                id,
                productName,
                MessageFormat.format(descriptions.get(descriptionIdx), productName),
                price,
                1_000_000L,
                start.toInstant(ZoneOffset.UTC),
                end.toInstant(ZoneOffset.UTC)
            );
            products.add(product);

            priceIdx = (priceIdx + 1 >= prices.size()) ? 0 : priceIdx + 1;
            descriptionIdx = (descriptionIdx + 1 >= descriptions.size()) ? 0 : descriptionIdx + 1;
            adjIdx = (adjIdx + 1 >= adjective.length) ? 0 : adjIdx + 1;
            nameIdx = (nameIdx + 1 >= nouns.length) ? 0 : nameIdx + 1;
            randomMinutesIdx =
                (randomMinutesIdx + 1 >= randomMinuets.size()) ? 0 : randomMinutesIdx + 1;
        }

        productRepository.saveAll(products);
    }

    public void createUsersMock(final int size) {
        final List<User> users = new ArrayList<>();
        final List<Cart> carts = new ArrayList<>();
        final String password = passwordEncoder.encode(RAW_PASSWORD);

        for (int i = 0; i < size; i++) {
            final long id = idGenerator.generate();
            final String username = "user" + (i + 1);
            final UserType userType = UserType.ROLE_USER;

            users.add(new User(id, username, password, userType));
            carts.add(new Cart(id, id));
        }

        userRepository.saveAll(users);
        cartRepository.saveAll(carts);
    }
}
