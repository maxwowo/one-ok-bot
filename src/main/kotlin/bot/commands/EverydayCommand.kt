package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !everyday command
 *
 * Shuffles the queue
 */
class EverydayCommand : Command() {
    init {
        name = "everyday"
        help = "Shuffles the queue"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription("I'm shufflin'")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        audioPlayerManager.shuffleQueue(event.textChannel)

        event.reply(builder.build())
    }
}
