package example;

import gunit.annotations.After;
import gunit.annotations.Before;
import gunit.annotations.Test;

public class LifeCycleTest {
    // Подготовительные мероприятия. Метод выполнится перед каждым тестом
    @Before
    public void firstSetUp() {
        System.out.print("\n@Before #1. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
    }

    // Подготовительные мероприятия. Метод выполнится перед каждым тестом
    @Before
    public void secondSetUp() {
        System.out.print("\n@Before #2. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
    }

    // Тест, который проходит
    @Test
    public void anyGoodTest1() {
        System.out.print("@Test: anyGoodTest1. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
    }

    // Тест, который падает
    @Test
    public void anyBadTest1() {
        System.out.print("@Test: anyBadTest1. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
        throw new RuntimeException("Oops, this test is failed!");
    }

    // Еще тест, который проходит
    @Test
    public void anyGoodTest2() {
        System.out.print("@Test: anyGoodTest2. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
    }

    // Тест, который падает
    @Test
    public void anyBadTest2() {
        System.out.print("@Test: anyBadTest2. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
        throw new RuntimeException("Sorry, this test is failed too!");
    }

    // Завершающие мероприятия. Метод выполнится после каждого теста
    @After
    public void firstTearDown1() {
        System.out.print("@After #1. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
    }

    // Завершающие мероприятия. Метод выполнится после каждого теста
    @After
    public void secondTearDown1() {
        System.out.print("@After #2. ");
        System.out.println("Test class instance: " + Integer.toHexString(hashCode()));
    }

}
