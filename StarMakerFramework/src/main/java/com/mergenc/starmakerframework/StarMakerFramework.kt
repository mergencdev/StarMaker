package com.mergenc.starmakerframework

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mergenc.starmakerframework.application.Constants.SHARED_PREFS_NAME
import com.mergenc.starmakerframework.application.Constants.SHARED_PREFS_STARS_LIST
import com.mergenc.starmakerframework.data.misc.Brightness
import com.mergenc.starmakerframework.data.misc.Color
import com.mergenc.starmakerframework.data.misc.Size
import com.mergenc.starmakerframework.data.model.StarModel


/**
 * Created by Mehmet Emin Ergenc on 12/30/2023
 */

class StarMakerFramework(context: Context) {
    private val context: Context = context
    private var starsList: MutableList<StarModel> = mutableListOf()

    companion object {
        fun triggerReset(context: Context) {
            StarMakerFramework(context).resetStars()
        }
    }

    // Single-interface method for adding a star; this method is called from the app
    fun addStarInterface(size: Size) {
        starsList = getStarsList(context).toMutableList()

        if (starsList.size < 10) {
            val star = when (size) {
                Size.S -> createSmallStar()
                Size.B -> createBigStar()
            }

            starsList.add(star)
            saveStarsList(context, starsList)
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

    /*
     * Method for resetting the stars; this method is triggered from the function called triggerReset() in the companion object;
     * And triggerReset() function called from the app;
     */
    private fun resetStars() {
        clearSharedPreferences(context)
        starsList.clear()
        saveStarsList(context, starsList)
        println("Sky is empty")
    }

    // Method for printing all the stars in the list
    private fun printAllList() {
        starsList.forEachIndexed { index, star ->
            println("${index + 1}. Size: ${star.size}, Color: ${star.color}, Brightness: ${star.brightness}")
        }
        println("Bright stars count: ${starsList.count { it.brightness == Brightness.BRIGHT }}")
    }

    /*
     * Method for saving the stars list to shared preferences;
     * This method is called from the addStarInterface() & resetStars() functions
     */
    private fun saveStarsList(context: Context, starsList: List<StarModel>) {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(starsList)
        editor.putString("StarsList", json)
        editor.apply()
    }

    /*
     * Method for getting the stars list from shared preferences;
     * This method is called from the addStarInterface() function
     */
    private fun getStarsList(context: Context): List<StarModel> {
        val sharedPreferences =
            context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        val json = sharedPreferences.getString(SHARED_PREFS_STARS_LIST, null)
        val gson = Gson()

        return if (json != null) {
            val type = object : TypeToken<List<StarModel>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    /*
     * Method for clearing the shared preferences;
     * This method is called from the resetStars() function
     */
    private fun clearSharedPreferences(context: Context) {
        val settings = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
        settings.edit().clear().apply()
    }
}