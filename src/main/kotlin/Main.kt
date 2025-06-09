package org.example

import org.vosk.Model
import org.vosk.Recognizer
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val modelPath = "/home/badgateway/voice-models/english-model"
    val sampleRate = 16000f
    val bufferSize = 4096

    val model = Model(modelPath)
    val recognizer = Recognizer (model, sampleRate)

    val commands = mapOf(
        "open google chrome" to "google-chrome",
        "start containers" to "synchro-utils dfe start-containers"
    )

    val format = AudioFormat(sampleRate, 16, 1, true, false)
    val micInfo = AudioSystem.getTargetDataLine(format)
    micInfo.open(format)
    micInfo.start()

    println("🎤 Speak a command...")

    val buffer = ByteArray(bufferSize)

    while (true) {
        val bytesRead = micInfo.read(buffer, 0, buffer.size)
        if (recognizer.acceptWaveForm(buffer, bytesRead)) {
            val result = recognizer.result
            println(result)
            val text = Regex("\"text\"\\s*:\\s*\"(.*?)\"").find(result)?.groupValues?.get(1)
            if (!text.isNullOrEmpty()) {
                println("🗣 You said: $text")
                val cmd = commands[text.lowercase()]
                if (cmd != null) {
                    println("⚙️ Running command: $cmd")
                    try {
                        Runtime.getRuntime().exec(cmd)
                    } catch (e: Exception) {
                        println(e.message)
                        e.printStackTrace()
                    }
                } else {
                    println("❌ Command not found: $text")
                }
            }
        }
    }
}