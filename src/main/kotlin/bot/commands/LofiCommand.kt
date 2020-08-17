package bot.commands

import bot.configurations.configurations
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
        audioPlayerManager.clearQueue(event.guild)
        audioPlayerManager.loadAndPlay(event, configurations.lofiURL)
    }
}
