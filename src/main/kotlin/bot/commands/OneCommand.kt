package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

/**
 * Class that represents !one command
 *
 * Queues a track
 */
class OneCommand : Command() {
    init {
        name = "one"
        help = "Queues a track"
        arguments = "<Youtube URL>"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        if (event.args.isEmpty()) {
            builder.setDescription("Give me the song URL dummy")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        } else {
            audioPlayerManager.loadAndPlay(event, event.args.trim())
        }
    }
}
