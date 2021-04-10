package com.vikas.mindbodyhw.list

/**
 * Manages CountryList recyclerView click
 */
interface OnCountryItemClickListener {
    /**
     * Returns clicked country id
     */
    fun onCountryClick(id: Int)
}