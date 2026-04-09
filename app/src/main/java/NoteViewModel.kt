import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : NoteRepository
    val notesLiveData = MutableLiveData<List<Note>>()

    init {

        val dao = AppDatabase.getDatabase(application).noteDao()
        repository= NoteRepository(dao)
        loadNotesFromViewModel()
    }

    fun loadNotesFromViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            val notes =repository.getAllNotesFromRepo()
            withContext(Dispatchers.Main){
                notesLiveData.value=notes
            }
        }
    }

    fun insertFromViewModel(note: Note){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(note)
            loadNotesFromViewModel()
        }

    }
    fun updateFromViewModel(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(note)
            loadNotesFromViewModel()
        }

    }
    fun deleteFromViewModel(note: Note){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
            loadNotesFromViewModel()
        }
    }
    fun searchFromViewModel(query: String){
        viewModelScope.launch(Dispatchers.IO){
            val result = repository.searchNotesFromRepo(query)
            withContext(Dispatchers.Main){
                notesLiveData.value=result
            }
        }

    }

}