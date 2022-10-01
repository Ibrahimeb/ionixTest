package com.example.ionixtest.presentation.features.home.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ionixtest.presentation.features.home.repositories.IHomeRepository
import com.example.ionixtest.presentation.features.home.usecase.ISorterMoviesUseCase
import com.example.ionixtest.presentation.features.home.viewmodels.utils.MainCoroutineScopeRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    lateinit var homeRepository: IHomeRepository

    @Mock
    lateinit var sorterMoviesUseCase: ISorterMoviesUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(homeRepository, sorterMoviesUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `call correctly repository`() = coroutineScope.runBlockingTest {

        viewModel.getMovies()

        Mockito.verify(homeRepository).getMovies()
    }
}