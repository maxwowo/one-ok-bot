package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents !one command
 *
 * Plays a song
 */
class PlayCommand : Command() {
    init {
        name = "one"
        help = "Plays a song"
        arguments = "<track URL>"
    }

    override fun execute(event: CommandEvent) {
        if (event.args.isEmpty()) {
            event.replyWarning("\uD83C\uDFB8 Give me the song URL dummy")
        } else {
            audioPlayerManager.loadAndPlay(event.textChannel, event.args.trim())
        }
    }
}
