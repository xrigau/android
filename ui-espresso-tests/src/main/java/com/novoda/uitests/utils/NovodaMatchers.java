package com.novoda.uitests.utils;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.apps.common.testing.ui.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class NovodaMatchers {

    public static Matcher<View> withChildCount(final Matcher<Integer> numberMatcher) {
        return new BoundedMatcher<View, ViewGroup>(ViewGroup.class) {
            @Override
            protected boolean matchesSafely(ViewGroup viewGroup) {
                return numberMatcher.matches(viewGroup.getChildCount());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("number of child views ");
                numberMatcher.describeTo(description);
            }
        };
    }

    public static Matcher<View> withCurrentItem(final Matcher<Integer> numberMatcher) {
        return new BoundedMatcher<View, ViewPager>(ViewPager.class) {
            @Override
            protected boolean matchesSafely(ViewPager viewGroup) {
                return numberMatcher.matches(viewGroup.getCurrentItem());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("current displayed item ");
                numberMatcher.describeTo(description);
            }
        };
    }
}
