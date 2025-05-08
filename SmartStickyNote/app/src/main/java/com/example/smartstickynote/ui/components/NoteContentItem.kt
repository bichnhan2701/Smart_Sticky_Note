package com.example.smartstickynote.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartstickynote.R

@Composable
fun NoteContentItem(
    value: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean = false,
    backgroundColor: Color = Color(0xFFDDCDF8)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .shadow(4.dp, RoundedCornerShape(16.dp), clip = false)
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(12.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = { if (!readOnly) onValueChange(it) },
            readOnly = readOnly,
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxSize()
                .padding(end = if (readOnly) 0.dp else 36.dp),
            decorationBox = { innerTextField ->
                Box {
                    if (value.isEmpty() && !readOnly) {
                        Text(
                            text = "Add your content",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )

        if (!readOnly) {
            Icon(
                painter = painterResource(id = R.drawable.attach_svgrepo_com),
                contentDescription = "Attach",
                modifier = Modifier.align(Alignment.TopEnd),
                tint = Color(0xFF3F51B5)
            )
        }
    }
}