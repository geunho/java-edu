package com.ebaykorea.gkhim.javaedu;

public class HelloDTO {
  private String name;
  private int age;

  // getter : Spring에서는 name property라고도 부른다.
  public String getName() {
    return name;
  }

  // setter : Spring에서는 name property라고도 부른다.
  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
