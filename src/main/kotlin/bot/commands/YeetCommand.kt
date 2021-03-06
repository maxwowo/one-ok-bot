package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents the !yeet command
 *
 * Clears the queue
 */
class YeetCommand : AudioCommand() {
    init {
        name = "yeet"
        help = "Clears the queue"
        category = Category("Music")
    }

    override fun executeCommand(event: CommandEvent) {
        val builder = EmbedBuilder()
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

        builder.setDescription("Yote the queue")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        musicManager.scheduler.clear()

        event.reply(builder.build())
    }
}
