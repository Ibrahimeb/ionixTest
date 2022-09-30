package com.example.ionixtest.domain.usecase

import com.example.ionixtest.domain.models.MovieModel
import com.example.ionixtest.presentation.features.home.usecase.ISorterMoviesUseCase
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class SorterMoviesUseCase @Inject constructor() : ISorterMoviesUseCase {
    override fun invoke(listMovie: MutableList<MovieModel>): List<MovieModel> {
        val listWithDateLong = mutableListOf<MovieModel>()
        val listResult = mutableListOf<MovieModel>()
        listMovie.forEach { movieListModel ->
            val modelAux = movieListModel.copy(
                releaseState = parseToValidDate(movieListModel.releaseState).time.toString()
            )
            listWithDateLong.add(modelAux)
        }
        listWithDateLong.sortByDescending { it.releaseState.toLong() }

        listWithDateLong.forEach {
            val modelAux = it.copy(releaseState = convertLongToTime(it.releaseState.toLong()))
            listResult.add(modelAux)
        }

        return listResult
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd/MM/yyyy ")
        return format.format(date)
    }

    private fun parseToValidDate(strDate: String): Date {
        val splitDate = strDate.split(" ")
        val formatDate = String.format(VALID_FORMAT_DATE, splitDate[0], getCorrectMount(splitDate[1]), splitDate[2])

        val formatter = SimpleDateFormat(DATE_FORMAT_PATTERN)

        return formatter.parse(formatDate) ?: throw Exception("can't possible format date")
    }

    private fun getCorrectMount(strMount: String) = when (strMount) {
        "Jan" -> "01"
        "Feb" -> "02"
        "Mar" -> "03"
        "Apr" -> "04"
        "May" -> "05"
        "Jun" -> "06"
        "Jul" -> "07"
        "Aug" -> "08"
        "Sep" -> "09"
        "Oct" -> "10"
        "Nov" -> "11"
        "Dec" -> "12"
        else -> ""
    }

    private companion object {
        const val VALID_FORMAT_DATE = "%s/%s/%s"
        const val DATE_FORMAT_PATTERN = "dd/MM/yyyy"
    }
}