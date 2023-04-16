package com.example.flagquizapp.data

fun getQuestion(questionNo: Int): QuizQuestion {
    val options: MutableList<String> = mutableListOf()
    val correctAns = getCountry(options)
    options.add(correctAns)
    val imageUrl: String = countryFlagsMap[correctAns]
        ?: "https://previews.123rf.com/images/kaymosk/kaymosk1804/kaymosk180400006/100130939-error-404-page-not-found-error-with-glitch-effect-on-screen-vector-illustration-for-your-design.jpg"

    for(i in 0 until 3) {
        options.add(getCountry(options))
    }

    return QuizQuestion(
        questionNo,
        imageUrl,
        correctAns,
        options
    )
}