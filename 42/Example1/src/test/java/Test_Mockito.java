import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Test_Mockito {

    @Mock
    ICalculator mcalc;

    @Mock
    List<String> mockedList;

    // используем аанотацию @InjectMocks для создания mock объекта
    @InjectMocks
    Calculator calc = new Calculator(mcalc);

    @Spy
    List<String> spiedList = new ArrayList<String>();

    @Mock
    List<String> mockList;








    // @Test(expected = NullPointerException.class)
    @Test
    public void whenMockitoAnnotationsUninitialized_thenNPEThrown() {
    Mockito.when(mockList.size()).thenReturn(13);
    assertEquals(13, mockList.size());
    System.err.println("Error ==>");
    }


    @Test
    public void whenUseSpyAnnotation_thenSpyIsInjectedCorrectly() {
    spiedList.add("one");
    spiedList.add("two");
    Mockito.verify(spiedList).add("one");
    Mockito.verify(spiedList).add("two");
    assertEquals(2, spiedList.size());
    Mockito.doReturn(100).when(spiedList).size();
    assertEquals(100, spiedList.size());
    }


    @Test
    public void whenUseMockAnnotation_thenMockIsInjected() {
    //    List mockList = Mockito.mock(ArrayList.class);  // you can just ==> @Mock List<String> mockedList;
    mockedList.add("one");
    Mockito.verify(mockedList).add("one");
    assertEquals(0, mockedList.size());
    Mockito.when(mockedList.size()).thenReturn(100);
    assertEquals(100, mockedList.size());
    }


    @Test
    public void testCalcAdd()
    {
        // определяем поведение калькулятора для операции сложения
        when(calc.add(10.0, 20.0)).thenReturn(30.0);

        // проверяем действие сложения
        assertEquals(calc.add(10, 20), 30.0, 0);
        // проверяем выполнение действия
        verify(mcalc).add(10.0, 20.0);

        // определение поведения с использованием doReturn
        doReturn(15.0).when(mcalc).add(10.0, 5.0);

        // проверяем действие сложения
        assertEquals(calc.add(10.0, 5.0), 15.0, 0);
        verify(mcalc).add(10.0, 5.0);
    }



    @Test
    public void testCallMethod()
    {
        // определяем поведение (результаты)
        when(mcalc.subtract(25.0, 15.0)).thenReturn(10.0);
        when(mcalc.subtract(25.0, 35.0)).thenReturn(-10.0);

        // вызов метода subtract и проверка результата
        assertEquals (calc.subtract(25.0, 15.0), 10, 0);
        assertEquals (calc.subtract(25.0, 15.0), 10, 0);

        assertEquals (calc.subtract(25.0, 35.0),-10, 0);

        // проверка вызова методов
        verify(mcalc, atLeastOnce()).subtract(25.0, 35.0);
        verify(mcalc, atLeast   (2)).subtract(25.0, 15.0);

        // проверка - был ли метод вызван 2 раза?
        verify(mcalc, times(2)).subtract(25.0, 15.0);
        // проверка - метод не был вызван ни разу
        verify(mcalc, never()).divide(10.0,20.0);

        /* Если снять комментарий со следующей проверки, то
         * ожидается exception, поскольку метод "subtract"
         * с параметрами (35.0, 25.0) был вызван 1 раз
         */
        // verify(mcalc, atLeast (2)).subtract(35.0, 25.0);

        /* Если снять комментарий со следующей проверки, то
         * ожидается exception, поскольку метод "subtract"
         * с параметрами (15.0, 25.0) был вызван 2 раза, а
         * ожидался всего один вызов
         */
        // verify(mcalc, atMost (1)).subtract(15.0, 25.0);
    }



    @Test
    public void testDevide()
    {
        when(mcalc.divide(15.0, 3)).thenReturn(5.0);

        assertEquals(calc.divide(15.0, 3), 5.0, 0);
        // проверка вызова метода
        verify(mcalc).divide(15.0, 3);

        // создаем исключение
        RuntimeException exception = new RuntimeException ("Division by zero");
        // определяем поведение
        doThrow(exception).when(mcalc).divide(15.0, 0);

        assertEquals(calc.divide(15.0, 0), 0.0, 0);
        verify(mcalc).divide(15.0, 0);
    }

    private Answer<Double> answer = new Answer<Double>() {
        @Override
        public Double answer(InvocationOnMock invocation) throws Throwable
        {
            // получение объекта mock
            Object mock = invocation.getMock();
            System.out.println ("mock object : " + mock.toString());

            // аргументы метода, переданные mock
            Object[] args = invocation.getArguments();
            double d1 = (double) args[0];
            double d2 = (double) args[1];
            double d3 = d1 + d2;
            System.out.println ("" + d1 + " + " + d2);
            return d3;
        }
    };


    @Test
    public void testThenAnswer()
        {
        // определение поведения mock для метода с параметрами
        when(mcalc.add(11.0, 12.0)).thenAnswer(answer);
        assertEquals(calc.add(11.0,12.0), 23.0, 0);
        }



    @Test
    public void testSpy()
    {
        Calculator scalc = spy(new Calculator(mcalc));
        when(scalc.double15()).thenReturn(23.0);

        // вызов метода реального класса
        scalc.double15();
        // проверка вызова метода
        verify(scalc).double15();

        // проверка возвращаемого методом значения
        assertEquals(23.0, scalc.double15(), 0);
        // проверка вызова метода не менее 2-х раз
        verify(scalc, atLeast(2)).double15();
    }



    @Test
    public void testTimout()
    {
        // определение результирующего значения mock для метода
        when(mcalc.add(11.0, 12.0)).thenReturn(23.0);
        // проверка значения
        assertEquals(calc.add(11.0,12.0), 23.0, 0);

        // проверка вызова метода в течение 10 мс
        verify(mcalc, timeout(100)).add(11.0, 12.0);
    }



    @Test
    public void testJavaClasses()
    {
        // создание объекта mock
        Iterator<String> mis = mock(Iterator.class);
        // формирование ответов
        when(mis.next()).thenReturn("Привет").thenReturn("Mockito");
        // формируем строку из ответов
        String result = mis.next() + ", " + mis.next();
        // проверяем
        assertEquals("Привет, Mockito", result);

        when(mis.next()).thenReturn("Hello").thenReturn(" mock!").thenReturn(" How are you?");
        System.out.println(mis.next() + ", " + mis.next() + mis.next() + mis.next());

        Comparable<String> mcs = mock(Comparable.class);
        when(mcs.compareTo("Mockito")).thenReturn(1);
        assertEquals(1, mcs.compareTo("Mockito"));

        Comparable<Integer> mci = mock(Comparable.class);
        when(mci.compareTo(anyInt())).thenReturn(1);
        assertEquals(1, mci.compareTo(5));
    }




}
