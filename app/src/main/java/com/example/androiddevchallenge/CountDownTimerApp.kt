/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.androiddevchallenge.screen.CountDownScreen
import com.example.androiddevchallenge.screen.SetTimeScreen
import com.example.androiddevchallenge.utils.TimeSet
import com.example.androiddevchallenge.utils.TimerState.COUNTDOWN
import com.example.androiddevchallenge.utils.TimerState.SET_TIME
import com.example.androiddevchallenge.viewmodel.TimerViewModel

@Composable
fun CountDownTimerApp(timerViewModel: TimerViewModel) {
    var currentState by remember {
        mutableStateOf(SET_TIME)
    }

    Surface(color = Color(0xFFFFFFFF)) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            val time by timerViewModel.time.observeAsState("")
            currentState = if (time.isEmpty()) SET_TIME else COUNTDOWN
            Crossfade(targetState = currentState) { state ->
                when (state) {
                    SET_TIME -> {
                        SetTimeScreen(
                            item = TimeSet.timeSet,
                            timerViewModel = timerViewModel
                        )
                    }
                    COUNTDOWN -> {
                        CountDownScreen(
                            time = time,
                            timerViewModel = timerViewModel
                        )
                    }
                }
            }
        }
    }
}