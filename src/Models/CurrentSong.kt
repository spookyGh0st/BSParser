package Models

object CurrentSong {
    private val currentSong: Song? = null


    fun getCS():Song?{
        return currentSong
    }

    fun getCSAsString(): String {
        return currentSong?._songName ?: ""
    }

    private fun returnCS(): Song? {
        return SongList(WIPPath.getPath()!!).returnCurrentSong()
    }
}