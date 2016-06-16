package wang.jack.mockitosample;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Jack Wang on 2016/6/16.
 */
public class MockitoRuleTest {

    @Mock
    AccountData accountData;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(accountData);
    }

}
