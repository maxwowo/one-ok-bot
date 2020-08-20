package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

/**
 * Class that represents the !yeet command
 *
 * Clears the queue
 */
class YeetCommand : AudioCommand() {
    init {
        name = "yeet"
        help = "Clears the queue"
    }

    override fun handleQueueEmpty(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription("${event.author.asMention} Bro the queue is already empty")
        builder.setColor(Color.RED)
        builder.setTimestamp(event.message.timeCreated)

        event.reply(builder.build())
    }

    override fun handleCommand(event: CommandEvent) {
        val builder = EmbedBuilder()
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        builder.setDescription("Yote the queue")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        musicManager.scheduler.clear()

        event.reply(builder.build())
    }
}
