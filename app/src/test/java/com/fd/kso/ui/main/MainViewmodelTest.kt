package com.fd.kso.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.fd.kso.data.model.MyItem
import com.fd.kso.data.repositories.LocationRepository
import com.fd.kso.testUtlis.MainCoroutineScopeRule
import com.fd.kso.testUtlis.TestUtil
import com.fd.kso.utils.Resource
import com.fd.kso.utils.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewmodelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    @Mock
    private lateinit var locationRepository: LocationRepository
    private lateinit var viewModel: MainViewmodel

    @Mock
    private lateinit var itemsObserver: Observer<Resource<List<MyItem>>>

    @Captor
    private lateinit var captor: ArgumentCaptor<Resource<List<MyItem>>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewmodel(locationRepository)
    }

    @After
    fun tearDown() {
        viewModel.allItems.removeObserver(itemsObserver)
    }


    @Test
    fun `Returned value is succes and list is not empty`() {
        coroutineScope.runBlockingTest {
            val mFlow  = flow <Resource<List<MyItem>>>{
                emit(Resource.loading(null))
                delay(8)
                emit(Resource.success(TestUtil.listOfItems))
            }

            `when`(locationRepository.allItems).thenReturn(mFlow)
            val livedate =  viewModel.allItems
            livedate.observeForever(itemsObserver)

            verify(itemsObserver, times(2)).onChanged(captor.capture())
            assertEquals(Status.LOADING, captor.value.status)

            coroutineScope.advanceTimeBy(8)
            verify(itemsObserver, times(3)).onChanged(captor.capture())

            assertEquals(Status.SUCCESS, captor.value.status)

            assertNotNull(captor.value.data)
            assertEquals(TestUtil.listOfItems, captor.value.data)
            assertEquals(TestUtil.listOfItems.get(0).id, captor.value.data?.get(0)?.id)

        }
    }

    @Test
    fun `Test if catching error`() {
        coroutineScope.runBlockingTest {
            val mFlow  = flow <Resource<List<MyItem>>>{
                emit(Resource.loading(null))
                delay(8)
                emit(Resource.error(null,TestUtil.errorMessage))
            }

            `when`(locationRepository.allItems).thenReturn(mFlow)
            val livedate =  viewModel.allItems
            livedate.observeForever(itemsObserver)

            verify(itemsObserver, times(2)).onChanged(captor.capture())
            assertEquals(Status.LOADING, captor.value.status)

            coroutineScope.advanceTimeBy(8)

            verify(itemsObserver, times(3)).onChanged(captor.capture())

            assertEquals(Status.ERROR, captor.value.status)

            assertNull(captor.value.data)
            assertEquals(TestUtil.errorMessage, captor.value.message)

        }
    }

}