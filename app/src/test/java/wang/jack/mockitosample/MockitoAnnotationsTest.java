package wang.jack.mockitosample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jack Wang on 2016/6/16.
 */
public class MockitoAnnotationsTest {

    @Mock
    AccountData accountData;

    @Before
    public void setupAccountData(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsNotNull(){
        assertNotNull(accountData);
    }

}
