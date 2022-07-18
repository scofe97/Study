package fastcampus.aop.part5.chapter05.presentation.stations

import fastcampus.aop.part5.chapter05.domain.Station
import fastcampus.aop.part5.chapter05.presentation.BasePresenter
import fastcampus.aop.part5.chapter05.presentation.BaseView

interface StationsContract {

    interface View : BaseView<Presenter> {

        // 로딩 지시자
        fun showLoadingIndicator()

        // 로딩 숨김
        fun hideLoadingIndicator()

        // 역 목록 보여줌
        fun showStations(stations: List<Station>)
    }

    interface Presenter : BasePresenter {

        // 검색바에 입력해서 걸러줌
       fun filterStations(query: String)
    }
}