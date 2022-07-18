package de.nvborck.hangman_android.espresso;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.nvborck.hangman_android.R;
import de.nvborck.hangman_android.gui.information.InformationActivity;
import de.nvborck.hangman_android.initialization.AsapSetupActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class InformationActivityTest {

    //private ActivityScenario<InformationActivity> activityRule;

    //@Before
    //public void SetUp(){
    //    activityRule = ActivityScenario.launch(InformationActivity.class);
    //}

    @Rule
    public ActivityScenarioRule<InformationActivity> activityRule = new ActivityScenarioRule<>(InformationActivity.class);

    @Test
    public void NavigatingToInformationViewAndChangeDataSuccessful() {

        // Change Data
        onView(withId(R.id.editPlayerId)).perform(click());
        onView(withId(R.id.editPlayerName)).perform(typeText("Anna"));
        onView(withId(R.id.save)).perform(click());

        onView(withText("Anna")).check(matches(isDisplayed()));
    }

}