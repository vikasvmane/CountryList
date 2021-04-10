package com.vikas.mindbodyhw.network.model

import com.google.gson.annotations.SerializedName

data class Country(

	@field:SerializedName("ID")
	val iD: Int,

	@field:SerializedName("Code")
	val code: String? = null,

	@field:SerializedName("PhoneCode")
	val phoneCode: String? = null,

	@field:SerializedName("Name")
	val name: String? = null
)
