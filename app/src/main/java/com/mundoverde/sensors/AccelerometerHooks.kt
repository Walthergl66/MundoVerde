package com.mundoverde.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlin.math.sqrt

/**
 * Representa una lectura del acelerómetro.
 * x, y, z: valores en m/s^2 por eje.
 * magnitude: magnitud total (raíz de la suma de cuadrados).
 */
data class AccelerometerData(
    val x: Float = 0f,
    val y: Float = 0f,
    val z: Float = 0f,
    val magnitude: Float = 0f
)

/**
 * @param samplingPeriod Constante de periodo de muestreo (SensorManager.SENSOR_DELAY_*)
 * @param applyLowPass Si true aplica filtro de paso bajo simple para suavizar datos.
 * @param alpha Coeficiente del filtro de paso bajo (0..1), valores pequeños = más suavizado.
 */
@Composable
fun rememberAccelerometer(
    samplingPeriod: Int = SensorManager.SENSOR_DELAY_UI,
    applyLowPass: Boolean = true,
    alpha: Float = 0.1f
): Pair<AccelerometerData, Boolean> {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val accelerometer: Sensor? = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) }
    val isAvailable = accelerometer != null

    // Estado interno para datos filtrados previos si se aplica low-pass
    var lastFiltered by remember { mutableStateOf(AccelerometerData()) }

    val dataState = produceState(initialValue = AccelerometerData(), accelerometer, applyLowPass, alpha, samplingPeriod) {
        if (!isAvailable) return@produceState
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                event ?: return
                val xRaw = event.values[0]
                val yRaw = event.values[1]
                val zRaw = event.values[2]
                val magnitudeRaw = sqrt(xRaw * xRaw + yRaw * yRaw + zRaw * zRaw)

                val (x, y, z) = if (applyLowPass) {
                    val fx = lastFiltered.x + alpha * (xRaw - lastFiltered.x)
                    val fy = lastFiltered.y + alpha * (yRaw - lastFiltered.y)
                    val fz = lastFiltered.z + alpha * (zRaw - lastFiltered.z)
                    lastFiltered = AccelerometerData(fx, fy, fz, sqrt(fx * fx + fy * fy + fz * fz))
                    Triple(lastFiltered.x, lastFiltered.y, lastFiltered.z)
                } else {
                    Triple(xRaw, yRaw, zRaw)
                }

                value = AccelerometerData(x, y, z, if (applyLowPass) lastFiltered.magnitude else magnitudeRaw)
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }
        sensorManager.registerListener(listener, accelerometer, samplingPeriod)
        awaitDispose { sensorManager.unregisterListener(listener) }
    }

    // Exponer pair para coincidir con ejemplos existentes.
    return Pair(dataState.value, isAvailable)
}
