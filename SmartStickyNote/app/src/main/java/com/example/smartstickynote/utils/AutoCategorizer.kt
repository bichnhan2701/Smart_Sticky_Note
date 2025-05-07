package com.example.smartstickynote.utils

import android.content.Context
import com.example.smartstickynote.domain.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

/**
 * Lớp này cung cấp khả năng đề xuất danh mục cho ghi chú dựa trên nội dung
 */
class AutoCategorizer @Inject constructor(
    private val context: Context
) {
    private val gson = Gson()
    
    // Danh sách các danh mục cơ bản
    private val basicCategories = mapOf(
        "công việc" to listOf("công việc", "việc", "job", "work", "task", "nhiệm vụ", "deadline", "dự án", "project"),
        "học tập" to listOf("học", "tập", "study", "bài tập", "homework", "assignment", "trường", "trường học", "school", "lớp", "class", "khóa học", "course"),
        "cá nhân" to listOf("cá nhân", "personal", "riêng tư", "private", "tôi", "me", "bản thân", "self"),
        "sức khỏe" to listOf("sức khỏe", "health", "bệnh", "ill", "sick", "đau", "pain", "thuốc", "medicine", "khám", "exam", "bác sĩ", "doctor"),
        "tài chính" to listOf("tiền", "money", "tài chính", "finance", "chi tiêu", "expense", "thu nhập", "income", "lương", "salary", "ngân sách", "budget")
    )
    
    /**
     * Đề xuất danh mục cho một ghi chú dựa trên nội dung
     */
    fun suggestCategories(note: Note): List<String> {
        val content = "${note.title.lowercase()} ${note.content.lowercase()}"
        val suggestedCategories = mutableListOf<String>()
        
        // Tìm kiếm từ khóa trong nội dung và tiêu đề
        basicCategories.forEach { (category, keywords) ->
            for (keyword in keywords) {
                if (content.contains(keyword.lowercase())) {
                    suggestedCategories.add(category)
                    break
                }
            }
        }
        
        return suggestedCategories
    }
    
    /**
     * Chuyển đổi danh sách danh mục thành chuỗi JSON để lưu trữ
     */
    fun categoriesToJson(categories: List<String>): String {
        return gson.toJson(categories)
    }
    
    /**
     * Chuyển đổi chuỗi JSON thành danh sách danh mục
     */
    fun jsonToCategories(json: String?): List<String> {
        if (json.isNullOrEmpty()) return emptyList()
        
        val type = object : TypeToken<List<String>>() {}.type
        return try {
            gson.fromJson<List<String>>(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
} 