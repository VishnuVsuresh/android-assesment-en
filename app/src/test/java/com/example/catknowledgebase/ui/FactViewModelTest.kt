package com.example.catknowledgebase.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.catknowledgebase.api.FactApi
import com.example.catknowledgebase.data.local.FactDataBase
import com.example.catknowledgebase.data.local.FactRepository
import com.example.catknowledgebase.model.FactItem
import com.example.catknowledgebase.util.MainCoroutinesRule
import com.example.catknowledgebase.util.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FactViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutinesRule = MainCoroutinesRule()

    private val mockApplicationContext: Context? = mock()

    private val fact: FactItem= FactItem(
        _id = "591f9894d369931519ce358f",
        text = "A female cat will be pregnant for approximately 9 weeks - between 62 and 65 days from conception to delivery.",
        createdAt = "2018-01-04T01:10:54.673Z",
        deleted = false,
        source = "api",
        catUrl = "https://cataas.com/cat/",
        __v = 0,
        type = "cat",
        updatedAt = "2018-01-04T01:10:54.673Z",
        used = false,
        user = ""
    )

    private lateinit var viewModel: FactViewModel

    @Mock
    private lateinit var factRepository: FactRepository

    @Mock
    private lateinit var apiHelper: FactApi


    private lateinit var factDatabase: FactDataBase

    @Mock
    lateinit var factObserver: Observer<List<FactItem>>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        factDatabase = Room.inMemoryDatabaseBuilder(
            mockApplicationContext!!,
            FactDataBase::class.java
        ).build()
        runTest {
            val flow = flow {
                emit(apiHelper.getCatFacts())
            }
            whenever(apiHelper.getCatFacts()).thenReturn(Response.success(200, fact))
            whenever(factRepository.getCatFact()).thenReturn(flow)
            whenever(factRepository.getFacts()).thenReturn(arrayListOf(fact))
        }
        viewModel = FactViewModel(factRepository)
    }

    @Test
    @Throws(Exception::class)
    fun loadFactTest_success() {
        runTest {
            viewModel.catFact.observeForever(factObserver)
            viewModel.loadFacts()
            Assert.assertEquals(arrayListOf(fact), viewModel.catFact.getOrAwaitValueTest())
            viewModel.catFact.removeObserver(factObserver)
        }
    }

    @Test
    @Throws(Exception::class)
    fun loadFactFromDB(){
        runTest {
            viewModel.catFact.observeForever(factObserver)
            viewModel.loadFromDB()
            Assert.assertEquals(arrayListOf(fact), viewModel.catFact.getOrAwaitValueTest())
            viewModel.catFact.removeObserver(factObserver)
        }
    }


    @After
    fun closeDb() {
        factDatabase.close()
    }

}
