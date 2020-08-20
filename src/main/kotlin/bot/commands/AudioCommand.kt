package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

abstract class AudioCommand : Command() {
    abstract fun executeCommand(event: CommandEvent)

    override fun execute(event: CommandEvent) {
        val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)
        val scheduler = musicManager.scheduler

        if (event.guild.audioManager.isConnected) {
            if (scheduler.isEmpty()) {
                val builder = EmbedBuilder()

                builder.setDescription("${event.author.asMention} Bro the queue is empty")
                builder.setColor(Color.RED)
                builder.setTimestamp(event.message.timeCreated)

                event.reply(builder.build())
            } else {
                executeCommand(event)
            }
        } else {
            val builder = EmbedBuilder()

            builder.setDescription("${event.author.asMention} Bro I'm not even in a voice channel")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        }
    }
}
