/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.mobile.uitests;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.clearText;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isEnabled;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Base class for activity tests
 *
 * @param <T>
 */
public abstract class ActivityTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    /**
     * @param activityClass
     */
    public ActivityTest(Class<T> activityClass) {
        super(activityClass);
    }

    /**
     * Verify activity was created successfully
     */
    public void testActivityIsCreated() {
        assertNotNull(getActivity());
    }

    public void testButtonIsDisabledDependingOnEditTextContent(int buttonId, int editTextId) throws Throwable {
        onView(withId(buttonId)).check(matches(not(isEnabled())));

        onView(withId(editTextId)).perform(typeText("someText"));
        onView(withId(buttonId)).check(matches(isEnabled()));

        onView(withId(editTextId)).perform(clearText());
        onView(withId(buttonId)).check(matches(not(isEnabled())));
    }
}
