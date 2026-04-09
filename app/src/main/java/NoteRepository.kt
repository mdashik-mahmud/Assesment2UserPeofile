
class NoteRepository(private val noteDao: NoteDao) {


    fun getAllNotesFromRepo(): List<Note>{
        return noteDao.getAllNotes()
    }
    fun insert(note: Note){
        noteDao.insert(note)
    }
    fun update(note: Note){
        noteDao.update(note)
    }
    fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun searchNotesFromRepo(query: String): List<Note>{
        return noteDao.searchNotes(query)
    }

}