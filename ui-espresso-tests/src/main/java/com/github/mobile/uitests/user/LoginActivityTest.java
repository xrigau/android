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
package com.github.mobile.uitests.user;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.clearText;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isEnabled;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import com.github.mobile.R;
import com.github.mobile.accounts.LoginActivity;
import com.github.mobile.uitests.ActivityTest;

/**
 * Tests of {@link com.github.mobile.accounts.LoginActivity}
 */
public class LoginActivityTest extends ActivityTest<LoginActivity> {

    /**
     * Create test for {@link com.github.mobile.accounts.LoginActivity}
     */
    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        getActivity();
    }

    /**
     * Verify activity was created successfully
     *
     * @throws Throwable
     */
    public void testSignInIsDisabled() throws Throwable {
        onView(withId(R.id.m_login)).check(matches(not(isEnabled())));

        String expectedUsername = "someUsername";
        onView(withId(R.id.et_login))
            .perform(typeText(expectedUsername))
            .check(matches(withText(expectedUsername)));
        onView(withId(R.id.m_login)).check(matches(not(isEnabled())));

        String expectedPassword = "somePassword";
        onView(withId(R.id.et_password))
            .perform(typeText(expectedPassword))
            .check(matches(withText(expectedPassword)));
        onView(withId(R.id.m_login)).check(matches(isEnabled()));

        onView(withId(R.id.et_login)).perform(clearText());
        onView(withId(R.id.et_password)).perform(clearText());

        onView(withId(R.id.m_login)).check(matches(not(isEnabled())));
    }
}
