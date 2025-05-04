package com.example.smartstickynote.domain.model

enum class Filter {
    NONE,         // Không lọc
    HIGH,         // Lọc theo mức độ ưu tiên cao
    MEDIUM,       // Lọc theo mức độ ưu tiên trung bình
    LOW,          // Lọc theo mức độ ưu tiên thấp
    FAVORITE      // Lọc theo trạng thái yêu thích
}