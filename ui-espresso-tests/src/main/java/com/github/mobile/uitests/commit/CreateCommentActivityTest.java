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
package com.github.mobile.uitests.commit;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.clearText;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isEnabled;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

import com.github.mobile.R;
import com.github.mobile.ui.commit.CreateCommentActivity;
import com.github.mobile.uitests.ActivityTest;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;

/**
 * Tests of {@link com.github.mobile.ui.commit.CreateCommentActivity}
 */
public class CreateCommentActivityTest extends ActivityTest<CreateCommentActivity> {

    /**
     * Create test
     */
    public CreateCommentActivityTest() {
        super(CreateCommentActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        User user = new User().setLogin("owner");
        Repository repo = new Repository().setName("name").setOwner(user);
        String commit = "abcdef";
        setActivityIntent(CreateCommentActivity.createIntent(repo, commit));

        getActivity();
    }

    /**
     * Verify empty comment can't be created
     *
     * @throws Throwable
     */
    public void testEmptyCommentIsProhitibed() throws Throwable {
        onView(withId(R.id.m_apply)).check(matches(not(isEnabled())));

        onView(withId(R.id.et_comment)).perform(typeText("someText"));
        onView(withId(R.id.m_apply)).check(matches(isEnabled()));

        onView(withId(R.id.et_comment)).perform(clearText());
        onView(withId(R.id.m_apply)).check(matches(not(isEnabled())));
    }
}
