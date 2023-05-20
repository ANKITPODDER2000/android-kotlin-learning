package com.example.recyclerviewandaudioplayer.viewmodels

import com.example.recyclerviewandaudioplayer.R

class AllWordTopicList {
    companion object {
        fun getAllColors() : List<Word> {
            val words = mutableListOf<Word>()
            words.add(Word("red","wetetti" , R.drawable.color_red,R.raw.color_red))
            words.add(Word("mustard yellow","chiwiita", R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow))
            words.add(Word("dusty yellow","topiisa", R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow))
            words.add(Word("green","chokokki", R.drawable.color_green,R.raw.color_green))
            words.add(Word("brown","takaakki", R.drawable.color_brown,R.raw.color_brown))
            words.add(Word("gray","topoppi", R.drawable.color_gray,R.raw.color_gray))
            words.add(Word("black","kululli", R.drawable.color_black,R.raw.color_black))
            words.add(Word("white","kelilli", R.drawable.color_white,R.raw.color_white))

            return words
        }


        fun getAllFamily(): List<Word>{
            val words = mutableListOf<Word>()
            words.add(Word("father","apa", R.drawable.family_father, R.raw.family_father))
            words.add(Word("mother","ata", R.drawable.family_mother, R.raw.family_mother))
            words.add(Word("son","angsi", R.drawable.family_son, R.raw.family_son))
            words.add(Word("daughter","tune", R.drawable.family_daughter, R.raw.family_daughter))
            words.add(Word("older brother"," taachi", R.drawable.family_older_brother, R.raw.family_older_brother))
            words.add(Word("younger brother","chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother))
            words.add(Word("older sister","tete", R.drawable.family_older_sister, R.raw.family_older_sister))
            words.add(Word("younger sister","kollete", R.drawable.family_younger_sister, R.raw.family_younger_sister))
            words.add(Word("grand mother","ama", R.drawable.family_grandmother, R.raw.family_grandmother))
            words.add(Word("grand father","apa", R.drawable.family_grandfather, R.raw.family_grandfather))

            return words
        }

        fun getAllNumber(): List<Word> {
            val words = mutableListOf<Word>()
            words.add(Word("one", "lutti", R.drawable.number_one, R.raw.number_one))
            words.add(Word("two", "otiiko", R.drawable.number_two, R.raw.number_two))
            words.add(Word("three", "tolookasu", R.drawable.number_three, R.raw.number_three))
            words.add(Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four))
            words.add(Word("five", "massokka", R.drawable.number_five, R.raw.number_five))
            words.add(Word("six", "temmokka", R.drawable.number_six, R.raw.number_six))
            words.add(Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven))
            words.add(Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight))
            words.add(Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine))
            words.add(Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten))

            return words
        }

        fun getAllPhase(): List<Word> {
            val words = mutableListOf<Word>()
            words.add(Word("where are you going ?","minto wuksus", null, R.raw.phrase_where_are_you_going))
            words.add(Word("what is your name","tinna oyaasina", null, R.raw.phrase_what_is_your_name))
            words.add(Word("my name is","oyyasit", null, R.raw.phrase_my_name_is))
            words.add(Word("how are you feeling","michaksas", null, R.raw.phrase_how_are_you_feeling))
            words.add(Word("i'm feeling good","kuchi achit", null, R.raw.phrase_im_feeling_good))
            words.add(Word("Are you coming ","aanas'aa", null, R.raw.phrase_are_you_coming))
            words.add(Word("yes , i'm coming","haa'aanam", null, R.raw.phrase_yes_im_coming))
            words.add(Word("i'm coming","aanam", null, R.raw.phrase_im_coming))

            return words
        }

    }
}