package com.samy.cityselector.helper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

/**
 * Base class for ViewModel Unit tests. Inherit from it to create test cases which need
 * testCoroutineScope and Dispatcher
 *
 * @see AndroidTest
 * @see UnitTest
 */

open class ViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    protected val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    open fun beforeTest() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    open fun afterTest() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
}
