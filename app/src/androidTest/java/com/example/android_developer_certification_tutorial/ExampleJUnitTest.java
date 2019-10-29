package com.example.android_developer_certification_tutorial;

import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;



import com.example.android_developer_certification_tutorial.Testing.StringComparer;
import java.lang.Iterable;



/**
 * <p> This test uses a Junit4s Parameterized tests features which uses annotations to pass
 * parameters into a unit test. The way this works is that you have to use the {@link Parameterized}
 * runner to run your tests.
 * </p>
 */
@RunWith(Parameterized.class)
@SmallTest
public class ExampleJUnitTest {

    /**
     * @return {@link Iterable} that contains the values that should be passed to the constructor.
     * In this example we are going to use three parameters: operand one, operand two and the
     * expected result.
     */
    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"A", "B", "False"},
                {"AB","ab", "False"},
                {"ABC", "ABC", "True"},
                {"ThisIsAPositiveCheck", "ThisIsAPositiveCheck", "True"}});
    }

    private final String mOperandOne;
    private final String mOperandTwo;
    private final String mExpectedResult;

    private StringComparer mStringComparer;

    public ExampleJUnitTest(String operandOne, String operandTwo,
                            String expectedResult) {

        mOperandOne = operandOne;
        mOperandTwo = operandTwo;
        mExpectedResult = expectedResult;
    }

    @Before
    public void setUp() {
        mStringComparer = new StringComparer();
    }

    @Test
    public void testCompareTwoStrings() {
        String resultCompare = mStringComparer.CheckString(mOperandOne, mOperandTwo);
        assertThat(resultCompare, is(equalTo(mExpectedResult)));
    }

}