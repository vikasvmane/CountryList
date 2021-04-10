package com.vikas.mindbodyhw.detail

import com.vikas.mindbodyhw.network.model.ProvinceItem
import com.vikas.mindbodyhw.repositories.CountryDataRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Callback


class CountryDetailsViewModelTest {
    lateinit var countryDetailViewModel: CountryDetailsViewModel

    @Mock
    lateinit var countryDataRepository: CountryDataRepository

    @Mock
    lateinit var callback: Callback<List<ProvinceItem>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        countryDetailViewModel = mock(CountryDetailsViewModel::class.java)
    }

    @Test(expected = Throwable::class)
    fun testHandleGetCountryDetail_Failure() {
        val throwable = mock(Throwable::class.java)
        countryDetailViewModel.getProvinces(0)
        `when`(countryDataRepository.fetchCountryDetail(callback, 0)).thenThrow(throwable)
        assert(countryDetailViewModel.errorMsg.value == throwable.message)
    }
}