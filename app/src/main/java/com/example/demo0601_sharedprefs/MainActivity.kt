package com.example.demo0601_sharedprefs

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // Current count
    private var mCount = 0

    // Current background color
    private var mColor = 0

    // Key for current count
    private val COUNT_KEY = "count"

    // Key for current color
    private val COLOR_KEY = "color"

    private var mPreferences: SharedPreferences? = null
    private val sharedPrefFile = "com.example.demo0601_sharedprefs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mColor = ContextCompat.getColor(
            this,
            R.color.default_background
        )

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        mCount = mPreferences?.getInt(COUNT_KEY, 0)!!
        count_textview?.setText(String.format("%s", mCount))
        mColor = mPreferences?.getInt(COLOR_KEY, mColor)!!
        count_textview?.setBackgroundColor(mColor)

    }

    override fun onPause() {
        super.onPause()

        val preferencesEditor = mPreferences!!.edit()
        preferencesEditor.putInt(COUNT_KEY, mCount)
        preferencesEditor.putInt(COLOR_KEY, mColor)

        preferencesEditor.apply()
    }

    /**
     * Handles the onClick for the background color buttons. Gets background
     * color of the button that was clicked, and sets the TextView background
     * to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    fun changeBackground(view: View) {
        val color = (view.background as ColorDrawable).color
        count_textview!!.setBackgroundColor(color)
        mColor = color
    }

    /**
     * Handles the onClick for the Count button. Increments the value of the
     * mCount global and updates the TextView.
     *
     * @param view The view (Button) that was clicked.
     */
    fun countUp(view: View?) {
        mCount++
        count_textview!!.text = String.format("%s", mCount)
    }

    /**
     * Handles the onClick for the Reset button. Resets the global count and
     * background variables to the defaults and resets the views to those
     * default values.
     *
     * @param view The view (Button) that was clicked.
     */
    fun reset(view: View?) {
        // Reset count
        mCount = 0
        count_textview!!.text = String.format("%s", mCount)

        // Reset color
        mColor = ContextCompat.getColor(
            this,
            R.color.default_background
        )
        count_textview!!.setBackgroundColor(mColor)

        // Clear preferences
        val preferencesEditor = mPreferences!!.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()
    }
}









