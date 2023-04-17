package bot.commands

import bot.configurations.configurations
import bot.handlers.TutelAudioLoadHandler
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents the !tutel command
 *
 * Clears the queue and plays 2:23 AM
 */
class TutelCommand : Command() {
    init {
        name = "tutel"
        help = "Tutel"
        category = Category("Music")
    }

    override fun execute(event: CommandEvent) {
        val guild = event.guild
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        musicManager.scheduler.clear()

        audioPlayerManager.loadAndPlay(guild, configurations.tutelURL, TutelAudioLoadHandler(event))
    }
}
