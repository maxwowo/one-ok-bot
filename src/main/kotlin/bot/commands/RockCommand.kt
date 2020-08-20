package bot.commands

import bot.configurations.configurations
import bot.handlers.audioLoadHandlers.QuietAudioLoadHandler
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents the !rock command
 *
 * Clears the queue and plays One Ok Rock songs
 */
class RockCommand : Command() {
    init {
        name = "rock"
        help = "Clears the queue and plays One Ok Rock songs"
    }

    override fun execute(event: CommandEvent) {
        val guild = event.guild

        audioPlayerManager.clearQueue(guild)
        audioPlayerManager.loadAndPlay(guild, configurations.rockURL, QuietAudioLoadHandler(event))
    }
}
