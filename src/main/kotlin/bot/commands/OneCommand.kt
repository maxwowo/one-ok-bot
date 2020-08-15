package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents !one command
 *
 * Plays a song
 */
class OneCommand : Command() {
    init {
        name = "one"
        help = "Plays a song"
        arguments = "<Youtube URL>"
    }

    override fun execute(event: CommandEvent) {
        if (event.args.isEmpty()) {
            event.replyWarning("Give me the song URL dummy")
        } else {
            audioPlayerManager.loadAndPlay(event.textChannel, event.args.trim())
        }
    }
}
