package bot.commands

import bot.configurations.configurations
import bot.handlers.audioLoadHandlers.GimmeAudioLoadHandler
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents the !gimme command
 *
 * Clears the queue and plays No Scared
 */
class GimmeCommand : Command() {
    init {
        name = "gimme"
        help = "GIVE ME MOOOOOOOORE"
    }

    override fun execute(event: CommandEvent) {
        val guild = event.guild
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        musicManager.scheduler.clear()

        audioPlayerManager.loadAndPlay(guild, configurations.noScaredURL, GimmeAudioLoadHandler(event))
    }
}
