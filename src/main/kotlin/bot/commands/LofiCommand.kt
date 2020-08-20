package bot.commands

import bot.configurations.configurations
import bot.handlers.audioLoadHandlers.LoudAudioLoadHandler
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents the !lofi command
 *
 * Clears the queue and plays the lo-fi stream
 */
class LofiCommand : Command() {
    init {
        name = "lofi"
        help = "Clears the queue and plays the lo-fi stream"
    }

    override fun execute(event: CommandEvent) {
        val guild = event.guild

        audioPlayerManager.clearQueue(guild)
        audioPlayerManager.loadAndPlay(guild, configurations.lofiURL, LoudAudioLoadHandler(event))
    }
}
