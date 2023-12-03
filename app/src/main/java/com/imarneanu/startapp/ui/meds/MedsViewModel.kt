package com.imarneanu.startapp.ui.meds

import com.imarneanu.startapp.core.android.BaseViewModel
import com.imarneanu.startapp.core.coroutines.shareReplayLatest
import com.imarneanu.startapp.domain.model.Medicine
import com.imarneanu.startapp.domain.usecase.InsertMedicine
import com.imarneanu.startapp.domain.usecase.QueryMedicine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MedsViewModel(
    queryMeds: QueryMedicine,
    private val insertMedicine: InsertMedicine,
) : BaseViewModel<MedsViewState, MedsError>() {
    init {
        query {
            queryMeds().map { MedsViewState.Meds(it) }
        }
    }

    fun addMedicine(name: String) = runCommand {
        insertMedicine(Medicine(name))
    }

    val meds = queryMeds().shareReplayLatest()

}
