### 写在前面

因个人能力有限，可能会出现理解错误的地方，欢迎指正和交流！

### 关于单元测试
通常一个优秀的开源框架，一般都会有很完善的单元测试。

举个例子：

![lizhi.png](http://upload-images.jianshu.io/upload_images/58536-35aba3addf249100.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

不好意思，我调皮了 :)

[Retrofit](https://github.com/square/retrofit) 

[googlesamples/android-architecture](https://github.com/googlesamples/android-architecture)

在这两个优秀的开源框架中，都非常注重单元测试的编写，而且单元测试占的比例也很大，甚至在某些方面上，测试代码会远远多于该框架本身的代码。这里我们以android-architecture中的todoapp为例，看看它的测试代码覆盖率是多少:

![0.png](http://upload-images.jianshu.io/upload_images/58536-cb0fe61bd048b2a0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

恩，相当可观的数据。至少对我而言.

至于如何查看测试代码覆盖率，方法很简单，看图操作：

**第一步**

<img src="http://upload-images.jianshu.io/upload_images/58536-ae9d828c028bbcd0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" width="600" height="600" />

**第二步**

<img src="http://upload-images.jianshu.io/upload_images/58536-3b99dfb29a6af33c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240" width="520" height="520" />

**第三步**

![3.png](http://upload-images.jianshu.io/upload_images/58536-b4b0da352d524d13.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

**Show Time**

![4.png](http://upload-images.jianshu.io/upload_images/58536-becf5c8fd5030874.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

所以...

写单元测试很重要.

写单元测试很重要..

写单元测试很重要..


### Mockito 

 接下来就让我们开始使用Mockito进行单元测试吧

#### 准备工作
    dependencies {
        testCompile 'junit:junit:4.12'
        // 如果要使用Mockito，你需要添加此条依赖库
        testCompile 'org.mockito:mockito-core:1.+'
        // 如果你要使用Mockito 用于 Android instrumentation tests，那么需要你添加以下三条依赖库
        androidTestCompile 'org.mockito:mockito-core:1.+'
        androidTestCompile "com.google.dexmaker:dexmaker:1.2"
        androidTestCompile "com.google.dexmaker:dexmaker-mockito:1.2"
    }

#### 创建Mockito测试

在 Mockito 中你可以使用 `mock()`  方法来创建一个模拟对象，也可以使用注解的方式 `@Mock` 来创建 ，这里推荐使用注解。需要注意的是，如果是使用注解方式，需要在使用前进行初始化。这里有三种初始化方式：

1. 使用 MockitoAnnotations.initMocks(this) 方式

示例代码：

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

2.使用 @RunWith(MockitoJUnitRunner.class) 方式

示例代码：

        @RunWith(MockitoJUnitRunner.class)
        public class MockitoJUnitRunnerTest {
        
            @Mock
            AccountData accountData;
        
            @Test
            public void testIsNotNull(){
                assertNotNull(accountData);
            }
        
        }


3. 使用 MockitoRule 方式

示例代码

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

#### 如何使用Mockito进行测试

这里我以AccountData类为例，进行一个简单的对象模拟测试

AccountData代码

        public class AccountData {
        
            private boolean isLogin;
            private String userName;
        
            public boolean isLogin() {
                return isLogin;
            }
        
            public void setLogin(boolean login) {
                isLogin = login;
            }
        
            public String getUserName() {
                return userName;
            }
        
            public void setUserName(String userName) {
                this.userName = userName;
            }
        }

假设现在你想对AccountData的mock对象进行模拟测试:

比如我希望 `isLogin()` 方法被调用时返回true，那么你可以这样写：

            @Test
            public void testIsLogin(){
                when(accountData.isLogin()).thenReturn(true);
                boolean isLogin=accountData.isLogin();
                assertTrue(isLogin);
            }
            
如果你希望 `getUserName()` 被调用返回Jack

            @Test
            public void testGetUserName(){
                when(accountData.getUserName()).thenReturn("Jack");
                assertEquals("Jack",accountData.getUserName());
            }
            
如果你希望对 `setUserName(String userName)` 方法中参数进行测试

            @Test
            public void testSetUserName(){
                accountData.setUserName("wang");
                verify(accountData).setUserName(Matchers.eq("wang"));
            }

如果你想统计 'isLogin()' 方法被调用的次数:
        
            @Test
            public void testIsLoginTimes(){
                accountData.isLogin();
                accountData.isLogin();
                verify(accountData,times(2)).isLogin();
            }

又或者

        verify(mock, never()).someMethod("never called");
        verify(mock, atLeastOnce()).someMethod("called at least once");
        verify(mock, atLeast(2)).someMethod("called at least twice");
        verify(mock, times(5)).someMethod("called five times");
        verify(mock, atMost(3)).someMethod("called at most 3 times");

......

是不是使用起来简单明了。

那就赶紧用起来吧，如果你想进一步了解，那就赶紧打开[Mockito](http://site.mockito.org/)的官网吧。


### 相关链接

[Mockito Website](http://site.mockito.org/)

[Mockito GitHub](https://github.com/mockito/mockito)

[Unit tests with Mockito - Tutorial](http://www.vogella.com/tutorials/Mockito/article.html)

### 最后

我是Jack Wang...

我的心愿是....

Google回归.....