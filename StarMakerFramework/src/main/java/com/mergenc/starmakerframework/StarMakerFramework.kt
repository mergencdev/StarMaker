package com.mergenc.starmakerframework

import android.content.Context
import android.widget.Toast
import com.mergenc.starmakerframework.data.misc.Brightness
import com.mergenc.starmakerframework.data.misc.Color
import com.mergenc.starmakerframework.data.misc.Size
import com.mergenc.starmakerframework.data.model.StarModel

/**
 * Created by Mehmet Emin Ergenc on 12/30/2023
 */

class StarMakerFramework(context: Context) {
    private val context: Context = context
    private val starsList = mutableListOf<StarModel>()

    // Single-interface
    fun createStar(size: Size) {
        if (starsList.size < 10) {
            val star = if (size == Size.S) {
                createSmallStar()
            } else {
                createBigStar()
            }
            starsList.add(star)
            printAllList()
        } else {
            println("Sky is full")
            Toast.makeText(context, "Sky is full", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createSmallStar(): StarModel {
        return StarModel(
            Size.S,
            Color.values().random(),
            Brightness.values().random()
        )
    }

    private fun createBigStar(): StarModel {
        return StarModel(
            Size.B,
            Color.values().random(),
            Brightness.values().random()
        )
    }

    fun resetStars() {
        starsList.clear()
        println("Sky is empty")
    }

    private fun printAllList() {
        starsList.forEachIndexed { index, star ->
            println("${index + 1}. Size: ${star.size}, Color: ${star.color}, Brightness: ${star.brightness}")
        }
        println("Bright stars count: ${starsList.count { it.brightness == Brightness.BRIGHT }}")
    }
}