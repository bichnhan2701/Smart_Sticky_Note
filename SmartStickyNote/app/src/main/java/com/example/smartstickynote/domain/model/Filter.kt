package com.example.smartstickynote.domain.model

sealed class Filter {
    data object HIGH : Filter() // Khởi tạo đối tượng singleton HIGH
    data object MEDIUM : Filter() // Khởi tạo đối tượng singleton MEDIUM
    data object LOW : Filter() // Khởi tạo đối tượng singleton LOW
    data object FAVORITE : Filter() // Khởi tạo đối tượng singleton FAVORITE
    data object NONE : Filter() // Khởi tạo đối tượng singleton NONE
    data class CATEGORY(val categoryId: String) : Filter() // CATEGORY có tham số categoryId

    companion object {
        // Có thể tạo các hàm tiện ích để lấy các Filter mặc định hoặc theo điều kiện
        fun getDefaultFilter(): Filter = NONE
    }
}

