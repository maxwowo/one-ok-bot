package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents !ok command
 *
 * Skips the current song in the queue
 */
class OkCommand : Command() {
    init {
        name = "ok"
        help = "Skips the current song in the queue"
    }

    override fun execute(event: CommandEvent) {
        audioPlayerManager.skipTrack(event.textChannel)

        event.reply("Skipped")
    }
}
