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
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.novoda.uitests.utils.NovodaMatchers.withCurrentItem;
import static com.novoda.uitests.utils.NovodaViewAction.SwipeDirection.LEFT_TO_RIGHT;
import static com.novoda.uitests.utils.NovodaViewAction.SwipeDirection.RIGHT_TO_LEFT;
import static com.novoda.uitests.utils.NovodaViewAction.swipe;
import static org.hamcrest.Matchers.is;

import com.github.mobile.R;
import com.github.mobile.core.gist.GistStore;
import com.github.mobile.ui.gist.GistFilesViewActivity;
import com.github.mobile.uitests.ActivityTest;
import com.google.inject.Inject;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;

import roboguice.RoboGuice;

/**
 * Tests of {@link com.github.mobile.ui.gist.GistFilesViewActivity}
 */
public class GistFilesViewActivityTest extends ActivityTest<GistFilesViewActivity> {

    private static final int FIRST_PAGE = 0;
    private static final int SECOND_PAGE = 1;

    @Inject
    private GistStore store;

    private Gist gist;

    /**
     * Create test
     */
    public GistFilesViewActivityTest() {
        super(GistFilesViewActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        RoboGuice.injectMembers(getInstrumentation().getTargetContext().getApplicationContext(), this);
        setupGistStore();
    }

    private void setupGistStore() {
        gist = new Gist().setId("someId");
        Map<String, GistFile> files = new LinkedHashMap<String, GistFile>();
        files.put("firstGistFile", new GistFile().setFilename("firstGistFilename").setContent("firstGistContent"));
        files.put("secondGistFile", new GistFile().setFilename("secondGistFilename").setContent("secondGistContent"));
        gist.setFiles(files);
        store.addGist(gist);
    }

    @Override
    public void testActivityIsCreated() {
        int ignored = FIRST_PAGE;
        startGistFilesViewActivity(ignored);
        super.testActivityIsCreated();
    }

    /**
     * Verify changing pages between gist files
     *
     * @throws Throwable
     */
    public void testChangingPages() throws Throwable {
        startGistFilesViewActivity(FIRST_PAGE);

        onView(withId(R.id.vp_pages)).check(matches(withCurrentItem(is(FIRST_PAGE))));

        // Line breaks make it more readable (Given, When, Then) - Only when action and assertion are on the same view
        onView(withId(R.id.vp_pages))
            .perform(swipe(RIGHT_TO_LEFT))
            .check(matches(withCurrentItem(is(SECOND_PAGE))));

        onView(withId(R.id.vp_pages))
            .perform(swipe(LEFT_TO_RIGHT))
            .check(matches(withCurrentItem(is(FIRST_PAGE))));
    }

    /**
     * Verify Activity shows right initial file
     *
     * @throws Throwable
     */
    public void testShowsInitialFile() throws Throwable {
        startGistFilesViewActivity(SECOND_PAGE);

        onView(withId(R.id.vp_pages)).check(matches(withCurrentItem(is(SECOND_PAGE))));
    }

    private void startGistFilesViewActivity(int initialPage) {
        setActivityIntent(GistFilesViewActivity.createIntent(gist, initialPage));
        getActivity();
    }
}
