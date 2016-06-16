package wang.jack.mockitosample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jack Wang on 2016/6/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoJUnitRunnerTest {

    @Mock
    AccountData accountData;

    @Test
    public void testIsNotNull(){
        assertNotNull(accountData);
    }

}
