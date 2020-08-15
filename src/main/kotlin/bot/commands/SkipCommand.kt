package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents !ok command
 *
 * Skips a song
 */
class SkipCommand : Command() {
    init {
        name = "ok"
        help = "Skips a song"
    }

    override fun execute(event: CommandEvent) {
        audioPlayerManager.skipTrack(event.textChannel)

        event.reply("\uD83C\uDFB8 Skipped")
    }
}
