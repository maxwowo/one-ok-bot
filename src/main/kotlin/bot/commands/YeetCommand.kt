package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !yeet command
 *
 * Clears the queue
 */
class YeetCommand : Command() {
    init {
        name = "yeet"
        help = "Clears the queue"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription("Yote the queue")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        audioPlayerManager.clearQueue(event.textChannel)

        event.reply(builder.build())
    }
}
