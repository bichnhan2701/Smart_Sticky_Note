package com.example.smartstickynote.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.smartstickynote.R
import com.example.smartstickynote.ui.components.ActionButton
import com.example.smartstickynote.ui.components.GradientText

data class OnboardingPage(
    val title: String,
    val description: String,
    val animationResId: Int? = null
)

@Composable
fun OnboardingScreen(
    onSkip: () -> Unit,
    onDone: () -> Unit
) {
    val onboardingPages = listOf(
        OnboardingPage(
            "Sticky Note",
            "Ghi chú mọi ý tưởng một cách nhanh chóng, gọn gàng và trực quang!",
            R.raw.welcome,
        ),
        OnboardingPage(
            "Ghi chú thông minh",
            "Tạo, chỉnh sửa, lọc, phân loại và tìm kiếm ghi chú chỉ trong vài thao tác.",
            R.raw.note
        ),
        OnboardingPage(
            "Trực quang ghi chú",
            "Thay đổi màu sắc ghi chú để phân biệt theo mức độ ưu tiên.",
            R.raw.color
        ),
        OnboardingPage(
            "Widget tiện lợi",
            "Ghim ghi chú thành widget để không bao giờ bỏ lỡ điều quan trọng!",
            R.raw.widget
        )
    )

    var currentPage by rememberSaveable { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            OnboardingContent(page = onboardingPages[currentPage])
            DotIndicator(
                totalDots = onboardingPages.size,
                selectedIndex = currentPage
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentPage > 0) {
                    TextButton(onClick = { currentPage-- }) {
                        Text("Trở lại", color = Color(0xFF4D7ED9))
                    }
                } else {
                    Spacer(modifier = Modifier.width(64.dp))
                }

                ActionButton(
                    text = if (currentPage == onboardingPages.size - 1) "Bắt đầu" else "Tiếp tục",
                    onClick = {
                        if (currentPage < onboardingPages.size - 1) {
                            currentPage++
                        } else {
                            onDone()
                        }
                    }
                )
            }
        }

        // Nút Skip ở góc trên phải
        TextButton(
            onClick = onSkip,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Text("Bỏ qua", color = Color(0xFF4D7ED9))
        }
    }
}

@Composable
fun OnboardingContent(page: OnboardingPage) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(page.animationResId ?: return)
    )
    val progress by animateLottieCompositionAsState(composition)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        GradientText(text = page.title)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DotIndicator(totalDots: Int, selectedIndex: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .padding(4.dp)
                    .background(
                        color = if (index == selectedIndex) Color(0xFF2196F3) else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}