package Models

import java.io.File
import java.nio.file.Paths


fun parseVaporFrame(normalLightsDifficulty: Difficulty, highwayLightsDifficulty: Difficulty,finalLightsDifficulty: Difficulty){
    createBackup(finalLightsDifficulty)
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
fun mapdown(){
    val cs = CurrentSong.getCS() ?: return
    for (i in DiffEnum.values().size-1 downTo  1){
        if(cs.songsDifficulties[i]==null)
            continue
        if(cs.songsDifficulties[i-1] == null) {
            cs.songsDifficulties[i-1] = cs.songsDifficulties[i]?.copy()
            cs.songsDifficulties[i-1]!!.difficulty = DiffEnum.values()[i-1]
            cs.songsDifficulties[i-1]!!.path = Paths.get(cs.songsDifficulties[i-1]!!.path.toString().replace(DiffEnum.values()[i].difString, DiffEnum.values()[i-1].difString))
            mapDifficulty(cs.songsDifficulties[i-1],cs._beatsPerMinute)
        }
    }
    cs.songsDifficulties.forEach {
        if (it != null) {
            //Reader.writeDifficulty(it)
        }
    }
}
fun mapDifficulty( lowerD:Difficulty?,bpm:Double){

    if ( lowerD == null)
        return
    val minRange = when(lowerD.difficulty){
        DiffEnum.easy -> 1/(bpm/60*1)
        DiffEnum.normal -> 1/(bpm/60*1.3)
        DiffEnum.hard -> 1/(bpm/60*1.9)
        DiffEnum.expert -> 1/(bpm/60*2.5)
        DiffEnum.expertPlus -> 0.0
    }


   val notes= lowerD._notes.toMutableList()
    val notesIterator = notes.listIterator(1)
    for(i in notesIterator){
        if(i._time - notes[notes.indexOf(i)-1]._time < minRange)
            notesIterator.remove()
        //das klappt so nicht
    }
    notes.forEach{
        println(it._time - notes[notes.indexOf(it)-1]._time)
    }
}

fun parseConcertCreator(targetBPM: Double?, d:Difficulty, songList: ArrayList<Song?>){

    createBackup(d)

    val list = arrayListOf<Difficulty?>()
    songList.forEach {  list.add(getExpertPlusDifficulty(it)) }

    var i = 0


    d._notes = emptyList()
    d._events = emptyList()
    d._obstacles = emptyList()

    d._BPMChanges.forEach { _BPMChanges ->
        val offset = _BPMChanges._time
        val baseBPM = songList[i]!!._beatsPerMinute
        list[i]?._notes?.forEach { _notes ->
            _notes._time *= (targetBPM!! / baseBPM)
            _notes._time += offset
        }
        list[i]?._events?.forEach { _events ->
            _events._time *= (targetBPM!! / baseBPM)
            _events._time += offset
        }

        list[i]?._obstacles?.forEach { _obstacles ->
            _obstacles._time *= (targetBPM!! / baseBPM)
            _obstacles._time += offset
            if(_obstacles._duration>0)
                _obstacles._duration *= (targetBPM / baseBPM)
        }

        d._notes = d._notes.plus(list[i]!!._notes)
        d._events = d._events.plus(list[i]!!._events)
        d._obstacles = d._obstacles.plus(list[i]!!._obstacles)
        i++
    }
    d._notes = d._notes.sortedBy { it._time }
    d._events = d._events.sortedBy { it._time }
    d._obstacles = d._obstacles.sortedBy { it._time }
    Reader.writeDifficulty(d)
}


fun getExpertPlusDifficulty(s:Song?):Difficulty?{
    s?.songsDifficulties?.forEach { if(it?.difficulty?.difString ?: "" == "ExpertPlus")
        return it
    }
    return null
}



fun createBackup(d:Difficulty){
    val dBack = d.copy()
    dBack.path = File("${d.path}.old").toPath()
    Reader.writeDifficulty(dBack)
}
