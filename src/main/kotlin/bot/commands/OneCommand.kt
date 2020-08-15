package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

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
        val builder = EmbedBuilder()

        if (event.args.isEmpty()) {
            builder.setDescription("Give me the song URL dummy")
            builder.setColor(Color.RED)
        } else {
            audioPlayerManager.loadAndPlay(event, event.args.trim())
        }
    }
}
