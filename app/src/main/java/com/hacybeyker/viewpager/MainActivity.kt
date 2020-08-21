package com.hacybeyker.viewpager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.name

    private val introSliderAdapter = IntroSliderAdapter(
        mutableListOf(
            IntroSlide("Ivysaur", "Pokemon Hierva", R.drawable.ivysaur),
            IntroSlide("Blastoise", "Pokemon Agua", R.drawable.blastoise),
            IntroSlide("Flareon", "Pokemon Fuego", R.drawable.flareon),
            IntroSlide("Gengar", "Pokemon Fantasma", R.drawable.gengar),
            IntroSlide("Koffing", "Pokemon Toxico", R.drawable.koffing),
            IntroSlide("snorlax", "Pokemon Hierva", R.drawable.snorlax),
            IntroSlide("Venusaur", "Pokemon Hierva", R.drawable.venusaur)
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewPager.adapter = introSliderAdapter
        setUpIndicators()
        setCurrentIndicator(0)
        mainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                /*Log.d(
                    TAG, "Here - onPageScrolled: \n" +
                            "position: $position \n" +
                            "positionOffset: $positionOffset \n" +
                            "positionOffsetPixels: $positionOffsetPixels"
                )*/
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d(TAG, "Here - onPageSelected: $position")
                setCurrentIndicator(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                Log.d(TAG, "Here - onPageScrollStateChanged: $state")
            }
        })

        mainNextButton.setOnClickListener {
            introSliderAdapter.addData(IntroSlide("Articuno", "Pokemon Agua", R.drawable.articuno))
            /*if (mainViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                mainViewPager.currentItem += 1
            } else {
                Intent(applicationContext, AnotherActivity::class.java).also {
                    startActivity(it)
                }
            }*/
        }

        mainSkipText.setOnClickListener {
            Intent(applicationContext, AnotherActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun setUpIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            mainIndicatorsContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = mainIndicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = mainIndicatorsContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_active)
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }

        }
    }
}