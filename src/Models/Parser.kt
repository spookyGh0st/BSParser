package Models


class Parser() {


    fun parseVaporFrame(normalLightsDifficulty: Difficulty, highwayLightsDifficulty: Difficulty,finalLightsDifficulty: Difficulty){

        highwayLightsDifficulty._events.forEach {
            when(it._type){
                0 -> it._type= 5
                1 -> it._type = 6
                2 -> it._type = 7
                3 -> it._type = 10
                4 -> it._type = 11
            }
        }
        println("1/3")
        finalLightsDifficulty._events = normalLightsDifficulty._events.plus(highwayLightsDifficulty._events)
        println("2/3")
        finalLightsDifficulty._events = finalLightsDifficulty._events.sortedBy { it._time }
        println("3/3")
        Reader.writeDifficulty(finalLightsDifficulty)
        println("Written file ,exiting")
    }
}
