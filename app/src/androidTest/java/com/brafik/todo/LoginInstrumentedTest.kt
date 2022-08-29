package com.brafik.todo

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.todo.R
import com.example.todo.ui.home.MainActivity
import com.example.todo.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun loginTest() {
        onView(withId(R.id.username)).perform(typeText("eve.holt@reqres.in"))
        onView(withId(R.id.password)).perform(typeText("cityslicka"))
        onView(withId(R.id.loginButton)).perform(click())
        Intents.init()
        intending(hasComponent(MainActivity::class.java.name))
    }
}