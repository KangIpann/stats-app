package Adapter

import Data.matchStatistic
import com.google.firebase.firestore.Query

class MatchBerjalanAdapter(private val query: Query) {

    private var matchesData: MutableList<matchStatistic> = mutableListOf()

}