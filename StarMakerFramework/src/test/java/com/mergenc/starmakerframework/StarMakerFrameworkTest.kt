package com.mergenc.starmakerframework

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mergenc.starmakerframework.data.misc.Brightness
import com.mergenc.starmakerframework.data.misc.Color
import com.mergenc.starmakerframework.data.misc.Size
import com.mergenc.starmakerframework.data.model.StarModel
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created by Mehmet Emin Ergenc on 12/30/2023
 */

class StarMakerFrameworkTest {

    @Mock
    private lateinit var mockContext: Context

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var starMakerFramework: StarMakerFramework

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(
            mockContext.getSharedPreferences(
                anyString(),
                Mockito.eq(Context.MODE_PRIVATE)
            )
        )
            .thenReturn(sharedPreferences)
        Mockito.`when`(sharedPreferences.edit()).thenReturn(editor)
        Mockito.`when`(editor.putString(anyString(), anyString())).thenReturn(editor)

        starMakerFramework = StarMakerFramework(mockContext)
    }

    @Test
    fun testAddStarInterface() {
        // Preparing a small list of stars
        val initialStars = listOf(StarModel(Size.S, Color.RED, Brightness.BRIGHT))
        val gson = Gson()
        val initialJson = gson.toJson(initialStars)
        Mockito.`when`(sharedPreferences.getString(anyString(), Mockito.anyString()))
            .thenReturn(initialJson)

        // Attempt to add a new star; this should succeed :swh:
        starMakerFramework.addStarInterface(Size.B)

        // Verify that the star list was updated and saved in local (shared preferences)
        Mockito.verify(editor).putString(Mockito.eq("StarsList"), Mockito.anyString())

        // Note: This requires modifying the StarMakerFramework class to expose the starsList or its size for testing
    }
}