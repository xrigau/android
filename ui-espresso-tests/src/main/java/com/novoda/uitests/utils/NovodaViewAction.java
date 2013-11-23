package com.novoda.uitests.utils;

import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isAssignableFrom;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.novoda.uitests.utils.NovodaMatchers.withChildCount;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.google.android.apps.common.testing.ui.espresso.UiController;
import com.google.android.apps.common.testing.ui.espresso.ViewAction;

import org.hamcrest.Matcher;

public class NovodaViewAction {

    public enum SwipeDirection {
        LEFT_TO_RIGHT(-1),
        RIGHT_TO_LEFT(1);

        public final int delta;

        SwipeDirection(int delta) {
            this.delta = delta;
        }
    }

    public static ViewAction swipe(final SwipeDirection direction) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(ViewPager.class), withChildCount(is(greaterThan(0))));
            }

            @Override
            public String getDescription() {
                return "swipe " + direction.name();
            }

            @Override
            public void perform(UiController uiController, View view) {
                ViewPager viewPager = (ViewPager) view;  viewPager.getAdapter();
                viewPager.setCurrentItem(viewPager.getCurrentItem() + direction.delta);
            }
        };
    }
}
