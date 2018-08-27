package tdd.examples.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class) // 07 예제의 init 코드를 자동화...
public class Examples08 {
    @Mock
    private List mockedList;

    @Test
    public void test() {
        // test here
    }
}
