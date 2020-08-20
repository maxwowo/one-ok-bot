package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

/**
 * Class that represents the !ok command
 *
 * Skips the current song in the queue
 */
class OkCommand : AudioCommand() {
    init {
        name = "ok"
        help = "Skips the current song in the queue"
    }

    override fun handleQueueEmpty(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription("${event.author.asMention} Bro there's nothing to skip")
        builder.setColor(Color.RED)

        event.reply(builder.build())
    }

    override fun handleCommand(event: CommandEvent) {
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        musicManager.scheduler.nextTrack()

        event.message.addReaction("\uD83D\uDC4C").queue()
    }
}
