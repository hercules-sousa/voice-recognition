package org.example.command.impl

import org.example.command.CommandExecutor
import org.vosk.Model
import org.vosk.Recognizer
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem

object VoiceAssistantConfig {
    const val SAMPLE_RATE = 16000f
    const val BUFFER_SIZE = 4096
}

fun runCommand(command: String) {
    try {
        val process = Runtime.getRuntime().exec(arrayOf("zsh", "-c", command))
    } catch (e: Exception) {
        println("Error running command: ${e.message}")
        e.printStackTrace()
    }
}

class CommandMapper {
    val commandMap: Map<String, CommandExecutor> = mapOf(
        "open chrome" to ChromeExecutor(),
        "the open chrome" to ChromeExecutor(),
        "echo" to EchoExecutor(),
        "the echo" to EchoExecutor(),
        "start containers" to ContainerExecutor("start"),
        "the start containers" to ContainerExecutor("start"),
        "stop containers" to ContainerExecutor("stop"),
        "the stop containers" to ContainerExecutor("stop"),
        "brave" to BraveExecutor("https://www.youtube.com/"),
        "the brave" to BraveExecutor("https://www.youtube.com/"),
        "soul of christ" to BraveExecutor("https://www.youtube.com/watch?v=QAUo2mG8e3g"),
        "the soul of christ" to BraveExecutor("https://www.youtube.com/watch?v=QAUo2mG8e3g")
    )

    fun getExecutor(command: String): CommandExecutor? {
        return commandMap[command.lowercase()]
    }
}

fun main(args: Array<String>) {
    val modelPath = args[0]
    val model = Model(modelPath)
    val recognizer = Recognizer(model, VoiceAssistantConfig.SAMPLE_RATE, "{ \"grammar\" : [ \"${CommandMapper().commandMap.keys.joinToString("\", \"")}\" ] }")

    val format = AudioFormat(VoiceAssistantConfig.SAMPLE_RATE, 16, 1, true, false)
    val micInfo = AudioSystem.getTargetDataLine(format)
    micInfo.open(format)
    micInfo.start()

    println("🎤 Speak a command...")

    val buffer = ByteArray(VoiceAssistantConfig.BUFFER_SIZE)
    val commandMapper = CommandMapper()

    while (true) {
        val bytesRead = micInfo.read(buffer, 0, buffer.size)
        if (recognizer.acceptWaveForm(buffer, bytesRead)) {
            val result = recognizer.result
            val text = Regex("\"text\"\\s*:\\s*\"(.*?)\"").find(result)?.groupValues?.get(1)
            if (!text.isNullOrEmpty()) {
                println("🗣 You said: $text")
                val executor = commandMapper.getExecutor(text)
                if (executor != null) {
                    println("⚙️ Running command: $text")
                    executor.execute(text)
                } else {
                    println("❌ Command not found: $text")
                }
            }
        }
    }
}