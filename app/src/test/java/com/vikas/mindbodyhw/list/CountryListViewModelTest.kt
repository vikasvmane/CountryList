package com.vikas.mindbodyhw.list

import com.vikas.mindbodyhw.network.model.Country
import com.vikas.mindbodyhw.repositories.CountryDataRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Callback

class CountryListViewModelTest {
    lateinit var countryListViewModel: CountryListViewModel

    @Mock
    lateinit var countryDataRepository: CountryDataRepository

    @Mock
    lateinit var callback: Callback<List<Country>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        countryListViewModel = Mockito.mock(CountryListViewModel::class.java)
    }

    @Test(expected = Throwable::class)
    fun testHandleGetCountryListFailure() {
        val throwable = Mockito.mock(Throwable::class.java)
        countryListViewModel.getCountryList()
        `when`(countryDataRepository.fetchCountryList(callback)).thenThrow(throwable)
        assert(countryListViewModel.errorMsg.value == throwable.message)
    }
}