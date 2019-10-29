/*
 * Copyright 2014, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android_developer_certification_tutorial;

import androidx.test.core.app.ActivityScenario;
import androidx.test.rule.ActivityTestRule;

import androidx.test.filters.LargeTest;

import com.example.android_developer_certification_tutorial.Testing.ExampleTestActivity;

import junit.framework.TestSuite;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static com.example.android_developer_certification_tutorial.HintMatcher.withHint;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * <p> With the new AndroidJUnit runner you can run both JUnit3 and JUnit4 tests in a single test
 * test suite. The {@link AndroidRunnerBuilder} which extends JUnit's {@link
 * AllDefaultPossibilitiesBuilder} will create a single {@link TestSuite} from all tests and run
 * them. </p>
 */
@LargeTest
public class OperationHintInstrumentationTest
        extends ActivityTestRule<ExampleTestActivity> {

//    private ExampleTestActivity mActivity;

    public OperationHintInstrumentationTest() {
        super(ExampleTestActivity.class);
    }
    @Before
    public void launchActivity() {
        ActivityScenario.launch( ExampleTestActivity.class);
//        mActivity = getActivity();
    }


//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//
//        // Espresso does not start the Activity for you we need to do this manually here.
//        mActivity = getActivity();
//    }
//    @Test
//    public void testPreconditions() {
//        assertThat(mActivity, notNullValue());
//    }

    @Test
    public void testEditText_OperandOneHint() {
        String operandOneHint = "Enter string";
        onView(withId(R.id.FirstEditText)).check(matches(withHint(operandOneHint)));
    }

    @Test
    public void testEditText_OperandTwoHint() {
        String operandTwoHint = "Re enter string";
        onView(withId(R.id.SecondEditText)).check(matches(withHint(operandTwoHint)));
    }

}
