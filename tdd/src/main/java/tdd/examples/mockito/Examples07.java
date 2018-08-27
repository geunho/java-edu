package tdd.examples.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;


public class Examples07 {
  // annotation으로 선언 후 사용
  @Mock
  private List mockedList;

  // @Mock 이 붙은 필드를 모두 Mock객체로 초기화한다.
  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this); // mock all the field having @Mock annotation
    // @Mock 필드에 Mock을 모두 생성해준다.
  }

  @Test
  public void test() {
    // test here
    mockedList.add("one");

    // verification
    verify(mockedList).add("one");
  }
}
