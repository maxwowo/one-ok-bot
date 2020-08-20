package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

/**
 * Class that represents the !ok command
 *
 * Skips the current song in the queue
 */
class OkCommand : Command() {
    init {
        name = "ok"
        help = "Skips the current song in the queue"
    }

    override fun execute(event: CommandEvent) {
        if (event.guild.audioManager.isConnected) {
            audioPlayerManager.skipTrack(event.textChannel)

            event.message.addReaction("\uD83D\uDC4C").queue()
        } else {
            val builder = EmbedBuilder()

            builder.setDescription("${event.author.asMention} Bro I'm not even in a voice channel")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        }
    }
}
