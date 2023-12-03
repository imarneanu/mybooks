package com.imarneanu.startapp.ui.meds

import com.imarneanu.startapp.domain.model.Medicine

sealed class MedsViewState {

    data class Meds(val medicines: List<Medicine>): MedsViewState()
}
