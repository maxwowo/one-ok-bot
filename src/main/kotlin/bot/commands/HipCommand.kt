package bot.commands

import bot.configurations.configurations
import bot.handlers.HipAudioLoadHandler
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents the !hip command
 *
 * Clears the queue and plays Hip To Be Square
 */
class HipCommand : Command() {
    init {
        name = "hip"
        help = "Clears the queue and plays Hip To Be Square"
        category = Category("Music")
    }

    override fun execute(event: CommandEvent) {
        val guild = event.guild
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        musicManager.scheduler.clear()

        audioPlayerManager.loadAndPlay(guild, configurations.hipToBeSquareURL, HipAudioLoadHandler(event))
    }
}
