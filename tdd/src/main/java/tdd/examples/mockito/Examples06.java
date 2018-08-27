package tdd.examples.mockito;

import org.mockito.InOrder;

import java.util.List;

import static org.mockito.Mockito.*;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

// Verification in order
public class Examples06 {
  public static void main(String[] args) {
    test1();
    test2();
  }

  public static void test1() {
    // single mock
    List singleMock = mock(List.class);

    singleMock.add("first");
    singleMock.add("second");

    InOrder inOrder = inOrder(singleMock);

    // InOrder를 이용해서 메서드의 호출 순서를 검증
    inOrder.verify(singleMock).add("first");
    inOrder.verify(singleMock).add("second");
  }

  public static void test2() {
    // multiple mocks
    List firstMock = mock(List.class);
    List secondMock = mock(List.class);
    //using mocks
    firstMock.add("first");
    secondMock.add("second");

    InOrder inOrder = inOrder(firstMock, secondMock); // pass multiple mocks to verify
    // 서로 다른 개체에 대해서도 순서 검증 가능
    inOrder.verify(firstMock).add("first");
    inOrder.verify(secondMock).add("second");
  }
}

