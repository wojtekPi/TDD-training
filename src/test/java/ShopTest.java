import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ShopTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shoulBeCreated(){
        assertThat(new Shop()).isNotNull();
    }

}