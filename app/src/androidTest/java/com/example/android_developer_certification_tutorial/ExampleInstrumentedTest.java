package com.example.android_developer_certification_tutorial;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android_developer_certification_tutorial.Testing.ExampleTestActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals( "com.example.android_developer_certification_tutorial", appContext.getPackageName() );
    }

    @Before
    public void launchActivity() {
        ActivityScenario.launch( ExampleTestActivity.class);
    }

    @Test
    public void PerformPositiveCheck() {

        final String operandOne = "ABC";
        final String operandTwo= "ABC";
        final String expectedResult = "True";

        // Type the two operands in the EditText fields
        onView(withId(R.id.FirstEditText)).perform(typeText(operandOne),
                closeSoftKeyboard());
        onView(withId(R.id.SecondEditText)).perform(typeText(operandTwo),
                closeSoftKeyboard());

        // Click on a given operation button
        onView(withId(R.id.CheckButton)).perform(click());

        // Check the expected test is displayed in the Ui
        onView(withId(R.id.ResultTextView)).check(matches(withText(expectedResult)));
    }
}
