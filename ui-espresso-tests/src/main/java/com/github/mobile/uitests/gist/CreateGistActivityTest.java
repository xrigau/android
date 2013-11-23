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
package com.github.mobile.uitests.gist;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isEnabled;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import android.content.Intent;

import com.github.mobile.R;
import com.github.mobile.ui.gist.CreateGistActivity;
import com.github.mobile.uitests.ActivityTest;

/**
 * Tests of {@link com.github.mobile.ui.gist.CreateGistActivity}
 */
public class CreateGistActivityTest extends ActivityTest<CreateGistActivity> {

    /**
     * Create test
     */
    public CreateGistActivityTest() {
        super(CreateGistActivity.class);
    }

    /**
     * Create Gist with initial text
     */
    public void testCreateWithInitialText() {
        String expected = "initialContent";
        setUpIntent(expected);
        getActivity();

        onView(withId(R.id.m_apply)).check(matches(isEnabled()));
        onView(withId(R.id.et_gist_content)).check(matches(withText(expected)));
    }

    private void setUpIntent(String expected) {
        setActivityIntent(new Intent().putExtra(Intent.EXTRA_TEXT, expected));
    }

    /**
     * Create Gist with no initial text
     *
     * @throws Throwable
     */
    public void testCreateWithNoInitialText() throws Throwable {
        getActivity();

        onView(withId(R.id.m_apply)).check(matches(not(isEnabled())));

        onView(withId(R.id.et_gist_content)).perform(typeText("someText"));
        onView(withId(R.id.m_apply)).check(matches(isEnabled()));
    }

    public void testCreateGistWithEmptyTextIsProhibited() throws Throwable {
        getActivity();
        testButtonIsDisabledDependingOnEditTextContent(R.id.m_apply, R.id.et_gist_content);
    }
}
