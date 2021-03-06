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
package com.github.mobile.uitests.repo;

import static android.app.SearchManager.QUERY;
import static android.content.Intent.ACTION_SEARCH;
import android.content.Intent;

import com.github.mobile.uitests.ActivityTest;
import com.github.mobile.ui.search.SearchActivity;

/**
 * Tests of {@link com.github.mobile.ui.search.SearchActivity}
 */
public class SearchActivityTest extends ActivityTest<SearchActivity> {

    /**
     * Create test
     */
    public SearchActivityTest() {
        super(SearchActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setUpIntent();
        getActivity();
    }

    private void setUpIntent() {
        String query = "someQuery";
        setActivityIntent(new Intent(ACTION_SEARCH).putExtra(QUERY, query));
    }
}
