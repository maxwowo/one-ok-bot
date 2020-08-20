package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !everyday command
 *
 * Shuffles the queue
 */
class EverydayCommand : AudioCommand() {
    init {
        name = "everyday"
        help = "Shuffles the queue"
        category = Category("Music")
    }

    override fun executeCommand(event: CommandEvent) {
        val builder = EmbedBuilder()
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        builder.setDescription("I'm shufflin'")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        musicManager.scheduler.shuffle()

        event.reply(builder.build())
    }
}
