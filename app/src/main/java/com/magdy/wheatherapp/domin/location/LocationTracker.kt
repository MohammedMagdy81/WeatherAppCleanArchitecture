package com.magdy.wheatherapp.domin.location

import android.location.Location

interface LocationTracker {

    suspend fun getCurrentLocation():Location?
}