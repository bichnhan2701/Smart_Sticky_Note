package com.example.smartstickynote.utils

import android.content.Context
import android.util.Log
import com.example.smartstickynote.domain.model.Note
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.tensorflow.lite.task.text.nlclassifier.NLClassifier
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Lớp này cung cấp khả năng phân loại tự động ghi chú dựa trên nội dung
 * kết hợp giữa phân tích từ khóa và sử dụng mô hình ML.
 */
@Singleton
class EnhancedAutoCategorizer @Inject constructor(
    private val context: Context,
    private val basicCategorizer: AutoCategorizer
) {
    private val gson = Gson()
    private var classifier: NLClassifier? = null
    private val tag = "EnhancedAutoCategorizer"
    
    // Các danh mục bổ sung nâng cao
    private val enhancedCategories = listOf(
        "công việc", "học tập", "cá nhân", "sức khỏe", 
        "tài chính", "mua sắm", "du lịch", "ẩm thực",
        "giải trí", "công nghệ", "sáng tạo", "dự án"
    )
    
    init {
        try {
            // Khởi tạo mô hình ML - trong thực tế, bạn sẽ tải xuống hoặc có sẵn một tệp model
            // Ở đây chúng ta chỉ mô phỏng việc sử dụng mô hình
            setupModel()
        } catch (e: Exception) {
            Log.e(tag, "Không thể khởi tạo mô hình ML: ${e.message}")
        }
    }
    
    private fun setupModel() {
        // Trong ứng dụng thực tế, bạn sẽ tải một mô hình thực từ assets
        // Ở đây chúng ta chỉ giả lập để tránh phải tạo mô hình thực
        
        // Trên thực tế, đây sẽ là đoạn mã để tải mô hình:
        // val modelFile = File(context.cacheDir, "text_classification.tflite")
        // context.assets.open("text_classification.tflite").use { input ->
        //     FileOutputStream(modelFile).use { output ->
        //         input.copyTo(output)
        //     }
        // }
        // classifier = NLClassifier.createFromFile(modelFile)
    }
    
    /**
     * Phân tích nội dung ghi chú và gợi ý các danh mục phù hợp kết hợp từ keyword matching
     * và mô hình ML (khi có)
     */
    fun suggestCategories(note: Note): List<String> {
        // Lấy các danh mục từ phương pháp dựa trên từ khóa
        val keywordBasedCategories = basicCategorizer.suggestCategories(note)
        
        // Lấy các danh mục từ mô hình ML (mô phỏng)
        val mlBasedCategories = processWithML(note)
        
        // Kết hợp các danh mục từ cả hai phương pháp
        return (keywordBasedCategories + mlBasedCategories).distinct()
    }
    
    /**
     * Xử lý nội dung ghi chú bằng ML để phân loại
     */
    private fun processWithML(note: Note): List<String> {
        val content = "${note.title.lowercase()} ${note.content.lowercase()}"
        return getMlCategories(content)
    }
    
    /**
     * Mô phỏng việc sử dụng mô hình ML để phân loại nội dung
     * Trong ứng dụng thực tế, hàm này sẽ sử dụng mô hình đã được huấn luyện
     */
    private fun getMlCategories(content: String): List<String> {
        // Trong ứng dụng thực tế, đây sẽ là đoạn mã sử dụng mô hình TensorFlow Lite:
        // classifier?.let { model ->
        //     val results = model.classify(content)
        //     return results
        //         .filter { it.score > 0.7f }  // Lọc theo ngưỡng tin cậy
        //         .map { it.label }
        // }
        
        // Mô phỏng kết quả từ mô hình ML
        // Tạo danh mục dựa trên các đặc điểm đơn giản của nội dung
        val result = mutableListOf<String>()
        
        // Phân tích độ dài nội dung
        if (content.length > 500) {
            result.add("dài")
        } else if (content.length < 100) {
            result.add("ngắn")
        }
        
        // Phân tích ngôn ngữ sử dụng
        if (content.contains(Regex("\\b(code|programming|software|developer|app|function|class|method)\\b", 
                RegexOption.IGNORE_CASE))) {
            result.add("công nghệ")
        }
        
        if (content.contains(Regex("\\b(creative|design|art|music|draw|paint|write|novel|story)\\b", 
                RegexOption.IGNORE_CASE))) {
            result.add("sáng tạo")
        }
        
        if (content.contains(Regex("\\b(project|milestone|deadline|team|collaboration|plan|strategy)\\b", 
                RegexOption.IGNORE_CASE))) {
            result.add("dự án")
        }
        
        if (content.contains(Regex("\\b(movie|film|game|play|fun|entertainment|relax|music|song)\\b", 
                RegexOption.IGNORE_CASE))) {
            result.add("giải trí")
        }
        
        // Chọn ngẫu nhiên một danh mục nếu không tìm thấy danh mục nào (để mô phỏng)
        if (result.isEmpty() && content.isNotEmpty()) {
            val randomIndex = content.hashCode() % enhancedCategories.size
            result.add(enhancedCategories[Math.abs(randomIndex)])
        }
        
        return result
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
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    /**
     * Giải phóng tài nguyên khi không còn sử dụng
     */
    fun close() {
        classifier?.close()
    }
} 