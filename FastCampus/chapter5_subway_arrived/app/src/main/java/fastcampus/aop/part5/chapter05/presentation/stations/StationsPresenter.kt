package fastcampus.aop.part5.chapter05.presentation.stations

import fastcampus.aop.part5.chapter05.data.repository.StationRepository
import fastcampus.aop.part5.chapter05.domain.Station
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StationsPresenter(
    private val view: StationsContract.View,
    private val stationRepository: StationRepository
) : StationsContract.Presenter {


    override val scope : CoroutineScope = MainScope()

    // 값을 넣어주고 방출하는 플로우 -> 상태를 옵저빙한다.
    private val queryString: MutableStateFlow<String> = MutableStateFlow("")
    private val stations: MutableStateFlow<List<Station>> = MutableStateFlow(emptyList())

    init{
        observeStations()
    }


    override fun onViewCreated() {
        scope.launch {
            view.showStations(stations.value)
            stationRepository.refreshStations()
        }
    }

    override fun onDestroyView() {
        TODO("Not yet implemented")
    }

    override fun filterStations(query: String) {
        TODO("Not yet implemented")
    }

    private fun observeStations() {
        stationRepository
            .stations
            // combine -> 가장 최근에 방출된 값을 활용해 내놓음
            .combine(queryString){ stations, query ->
                if(query.isBlank()){
                    stations
                } else{
                    stations.filter { it.name.contains(query)}
                }
            }
            .onStart { view.showLoadingIndicator() }
            .onEach {
                if(it.isEmpty()){
                    view.hideLoadingIndicator()
                }
                stations.value = it
                view.showStations(it)
            }
            .catch {
                it.printStackTrace()
                view.hideLoadingIndicator()
            }
            .launchIn(scope)
    }


}