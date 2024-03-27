package cloud2.shopingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShopingmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopingmallApplication.class, args);
	}

}
