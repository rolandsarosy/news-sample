package dev.rolandsarosy.newssample.common.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.rolandsarosy.newssample.common.event.Event
import dev.rolandsarosy.newssample.common.event.NavigationEvent
import dev.rolandsarosy.newssample.network.ModelResponse
import dev.rolandsarosy.newssample.network.collectResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class BaseViewModel : ViewModel() {
    val errorEvent = MutableLiveData<Event<String>>()
    val navigationEvent = MutableLiveData<Event<NavigationEvent>>()

    companion object {
        // TODO - Make sure that these errors use string resources instead of burned-in string values.
        private const val DEFAULT_NETWORK_ERROR = "There was an unexpected server error."
        private const val NO_INTERNET_NETWORK_ERROR = "You happen to have lost internet connection."
    }

    protected fun <M : Any, E : Any> CoroutineScope.launchOnIO(
        block: suspend () -> Flow<ModelResponse<M, E>>,
        success: (data: M) -> Unit,
        apiFailure: (error: E) -> Unit,
        unknownFailure: ((throwable: Throwable?) -> Unit)? = null,
        networkFailure: ((exception: IOException) -> Unit)? = null,
        externalFlow: MutableSharedFlow<ModelResponse.Success<M>>? = null
    ) {
        this.launch(Dispatchers.IO, CoroutineStart.DEFAULT) {
            block.invoke().collectResponse(
                externalFlow = externalFlow,
                success = success,
                apiFailure = apiFailure,
                unknownFailure = unknownFailure ?: { throwable -> handleUnknownFailure(throwable) },
                networkFailure = networkFailure ?: { exception -> handleNetworkFailure(exception) },
            )
        }
    }

    private fun handleNetworkFailure(exception: IOException) {
        Timber.e(exception, "A network outage error was caught and handled.")
        errorEvent.postValue(Event(NO_INTERNET_NETWORK_ERROR))
    }

    private fun handleUnknownFailure(throwable: Throwable?) {
        Timber.e(throwable, "An unknown network error has occurred and has been handled in a generic way.")
        errorEvent.postValue(Event(DEFAULT_NETWORK_ERROR))
    }
}
