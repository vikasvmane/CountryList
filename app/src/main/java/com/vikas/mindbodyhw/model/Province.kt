package com.vikas.mindbodyhw.network.model

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(
	val province: List<ProvinceItem>
)

data class ProvinceItem(
	@SerializedName("ID") val iD: Int? = null,
	@SerializedName("CountryCode") val countryCode: String? = null,
	@SerializedName("Code") val code: String? = null,
	@SerializedName("Name") val name: String? = null
)

