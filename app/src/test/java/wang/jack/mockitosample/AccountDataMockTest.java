package wang.jack.mockitosample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jack Wang on 2016/6/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountDataMockTest {

    @Mock
    AccountData accountData;

    @Test
    public void testIsNull(){
        assertNotNull(accountData);
    }

    @Test
    public void testIsLogin(){
        when(accountData.isLogin()).thenReturn(true);
        boolean isLogin=accountData.isLogin();
        assertTrue(isLogin);
    }

    @Test
    public void testGetUserName(){
        when(accountData.getUserName()).thenReturn("Jack");
        assertEquals("Jack",accountData.getUserName());
    }

    @Test
    public void testSetUserName(){
        accountData.setUserName("wang");
        verify(accountData).setUserName(Matchers.eq("wang"));
    }

    @Test
    public void testIsLoginTimes(){
        accountData.isLogin();
        accountData.isLogin();
        verify(accountData,times(2)).isLogin();
    }


}
