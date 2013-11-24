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
package com.github.mobile.uitests.issue;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.pressImeActionButton;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;

import com.github.mobile.R;
import com.github.mobile.ui.issue.EditIssueActivity;
import com.github.mobile.uitests.ActivityTest;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;

/**
 * Tests of {@link com.github.mobile.ui.issue.EditIssueActivity}
 */
public class EditIssueActivityTest extends ActivityTest<EditIssueActivity> {

    /**
     * Create test
     */
    public EditIssueActivityTest() {
        super(EditIssueActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setUpIntent();
        getActivity();
    }

    private void setUpIntent() {
        User user = new User().setLogin("someOwner");
        Repository repo = new Repository().setName("someName").setOwner(user);
        setActivityIntent(EditIssueActivity.createIntent(repo));
    }

    /**
     * Verify save menu is properly enabled/disable depending on the issue have
     * a non-empty title
     *
     * @throws Throwable
     */
    public void ignored_because_needs_authentication___testSaveMenuEnabled() throws Throwable {
        onView(withId(R.id.et_login)).perform(typeText("someUsername")).perform(pressImeActionButton());
        onView(withId(R.id.et_password)).perform(typeText("somePassword")).perform(pressImeActionButton());

        testButtonIsDisabledDependingOnEditTextContent(R.id.m_apply, R.id.et_issue_title);
    }
}
