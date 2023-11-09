import com.example.catchmoment.listscreen.ViewModel
import com.example.catchmoment.data.MomentRepository
import com.example.catchmoment.data.MomentRepositoryImpl
import com.example.catchmoment.mainscreen.MainPresenter
import com.example.catchmoment.settingsscreen.SettingPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {


    single<MomentRepository> {
        MomentRepositoryImpl()
    }

    viewModel {
        ViewModel(momentsRepository = get())
    }

    single {
        MainPresenter(repository = get())
    }

    single { SettingPresenter(repository = get()) }


}