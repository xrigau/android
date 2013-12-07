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

import com.github.mobile.R;
import com.github.mobile.ui.issue.CreateCommentActivity;
import com.github.mobile.uitests.ActivityTest;

import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.User;

/**
 * Tests of {@link com.github.mobile.ui.issue.CreateCommentActivity}
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

        setUpIntent();
        getActivity();
    }

    private void setUpIntent() {
        RepositoryId repoId = new RepositoryId("someOwner", "someName");
        int issueNumber = 1;
        User user = new User().setLogin("someLogin");
        setActivityIntent(CreateCommentActivity.createIntent(repoId, issueNumber, user));
    }

    /**
     * Verify empty comment can't be created
     *
     * @throws Throwable
     */
    public void testEmptyCommentIsProhitibed() throws Throwable {
        testButtonIsDisabledDependingOnEditTextContent(R.id.m_apply, R.id.et_comment);
    }
}
