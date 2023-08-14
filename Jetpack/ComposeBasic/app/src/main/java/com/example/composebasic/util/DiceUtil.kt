package com.example.composebasic.util

import com.example.composebasic.R
import java.util.Random

class DiceUtil {
    companion object {
        fun getRandomDiceNo(): Int = (Random().nextInt(100) % 6) + 1
        fun getDiceImage(diceNo: Int): Int {
            return when (diceNo) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }
    }
}