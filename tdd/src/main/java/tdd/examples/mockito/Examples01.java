package tdd.examples.mockito;

import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// Mocking과 Verification
public class Examples01 {
  public static void main(String[] args) {
    // mock
    List mockedList = mock(List.class); // List를 구현한 어떤 객체를 생성한 것. Mocito.mock(List.class);
    System.out.println(mockedList.getClass().getName());

    // mock 사용하기
    mockedList.add("two");
    mockedList.clear();

    // verification
    // 아래와 같은 메서드가 사용된적 있는지 검증하는 것
    // verify(mockedList).add("one"); 을 호출하면 오류가 발생
    verify(mockedList).add("two");
    verify(mockedList).clear();
  }
}
