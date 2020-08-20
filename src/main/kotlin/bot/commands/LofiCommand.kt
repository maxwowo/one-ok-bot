package bot.commands

import bot.configurations.configurations
import bot.handlers.audioLoadHandlers.LofiAudioLoadHandler
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
        category = Category("Music")
    }

    override fun execute(event: CommandEvent) {
        val guild = event.guild
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        musicManager.scheduler.clear()

        audioPlayerManager.loadAndPlay(guild, configurations.lofiURL, LofiAudioLoadHandler(event))
    }
}
