package com.example.smartstickynote.utils.widget

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.widget.RemoteViews
import com.example.smartstickynote.R
import com.example.smartstickynote.di.WidgetUseCaseEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object WidgetUpdater {
    fun updateWidgetNow(context: Context) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val widgetComponent = ComponentName(context, NoteWidgetProvider::class.java)
        val widgetIds = appWidgetManager.getAppWidgetIds(widgetComponent)

        widgetIds.forEach { widgetId ->
            updateSingleWidget(context, appWidgetManager, widgetId)
        }
    }

    private fun updateSingleWidget(context: Context, appWidgetManager: AppWidgetManager, widgetId: Int) {
        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            WidgetUseCaseEntryPoint::class.java
        )
        val getNoteForWidgetUseCase = entryPoint.getNoteForWidgetUseCase()

        CoroutineScope(Dispatchers.IO).launch {
            val note = getNoteForWidgetUseCase()
            withContext(Dispatchers.Main) {
                val views = RemoteViews(context.packageName, R.layout.note_widget).apply {
                    if (note != null) {
                        setTextViewText(R.id.tv_note_title, note.title)
                        setTextViewText(R.id.tv_note_content, note.content)
                    } else {
                        setTextViewText(R.id.tv_note_title, "Không có ghi chú")
                        setTextViewText(R.id.tv_note_content, "Ghim một ghi chú để hiển thị tại đây")
                    }
                }

                appWidgetManager.updateAppWidget(widgetId, views)
            }
        }
    }
}