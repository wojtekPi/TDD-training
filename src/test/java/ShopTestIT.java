import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ShopTestIT {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldBeCalledNotTestedMethod(){
        Shop shop = new Shop();
        shop.notTestedMethod();
        assertThat(true).isTrue();
    }

}