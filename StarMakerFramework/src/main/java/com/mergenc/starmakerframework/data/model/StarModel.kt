package com.mergenc.starmakerframework.data.model

import com.mergenc.starmakerframework.data.misc.Brightness
import com.mergenc.starmakerframework.data.misc.Color
import com.mergenc.starmakerframework.data.misc.Size

/**
 * Created by Mehmet Emin Ergenc on 12/30/2023
 */

data class StarModel(val size: Size, var color: Color, val brightness: Brightness)