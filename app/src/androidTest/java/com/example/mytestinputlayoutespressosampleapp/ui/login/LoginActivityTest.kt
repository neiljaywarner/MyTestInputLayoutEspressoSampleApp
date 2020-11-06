package com.example.mytestinputlayoutespressosampleapp.ui.login


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.mytestinputlayoutespressosampleapp.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginActivityTest() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.password),
                childAtPosition(
                    allOf(
                        withId(R.id.container),
                        childAtPosition(
                            withId(android.R.id.content),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("abc1234"), closeSoftKeyboard())

        val textInputEditText = onView(
            allOf(
                withId(R.id.username),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.username_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("j@gmail.com"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.username), withText("j@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.username_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.username), withText("j@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.username_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(click())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.username), withText("j@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.username_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("joe@gmail.com"))

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.username), withText("joe@gmail.com"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.username_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(closeSoftKeyboard())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
