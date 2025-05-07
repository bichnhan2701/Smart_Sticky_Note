package com.example.smartstickynote.domain.model

enum class Filter {
    NONE,            // Không lọc
    HIGH,            // Lọc theo mức độ ưu tiên cao
    MEDIUM,          // Lọc theo mức độ ưu tiên trung bình
    LOW,             // Lọc theo mức độ ưu tiên thấp
    FAVORITE,        // Lọc theo trạng thái yêu thích
    FOLDER,          // Lọc theo thư mục
    TAG,             // Lọc theo thẻ
    AUTO_CATEGORY    // Lọc theo danh mục tự động
}

// Dữ liệu bổ sung khi sử dụng bộ lọc
data class FilterData(
    val filter: Filter = Filter.NONE,
    val folderId: String? = null,         // Sử dụng khi filter là FOLDER
    val tagId: String? = null,            // Sử dụng khi filter là TAG
    val category: String? = null          // Sử dụng khi filter là AUTO_CATEGORY
)