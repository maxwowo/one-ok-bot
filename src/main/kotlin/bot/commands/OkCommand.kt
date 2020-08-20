package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents the !ok command
 *
 * Skips the current song in the queue
 */
class OkCommand : AudioCommand() {
    init {
        name = "ok"
        help = "Skips the current song in the queue"
        category = Category("Music")
    }

    override fun executeCommand(event: CommandEvent) {
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        musicManager.scheduler.nextTrack()

        event.message.addReaction("\uD83D\uDC4C").queue()
    }
}
