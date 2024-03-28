package cloud2.shopingmall.user.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("testname");
        user.setPassword("testpassword");
        user.setUserRole(User.UserRole.USER);

    }

    @Test
    void getId() {
        assertEquals(1L, user.getId());
    }

    @Test
    void getUsername() {
        assertEquals("testname", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("testpassword", user.getPassword());
    }

    @Test
    void getUserRole() {
        assertEquals(User.UserRole.USER, user.getUserRole());
    }
}
