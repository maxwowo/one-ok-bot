package bot.commands

import bot.configurations.configurations
import bot.handlers.RockAudioLoadHandler
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
        category = Category("Music")
    }

    override fun execute(event: CommandEvent) {
        val guild = event.guild
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        musicManager.scheduler.clear()

        audioPlayerManager.loadAndPlay(guild, configurations.rockURL, RockAudioLoadHandler(event))
    }
}
