import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ShopTestCase {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shoulBeCreated(){
        assertThat(new Shop(null)).isNotNull();
    }

}