package bot.handlers.audioLoadHandlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

abstract class AudioLoadHandler(private val event: CommandEvent) : AudioLoadResultHandler {
    protected fun connectToVoiceChannel() {
        if (!event.guild.audioManager.isConnected) {
            val author = event.author
            val audioManager = event.guild.audioManager
            val voiceChannel = event.guild.voiceChannels.find {
                channel ->
                author in channel.members.map { it.user }
            } ?: throw AuthorNotConnectedToVoiceChannelException(author)

            audioManager.isSelfDeafened = true
            audioManager.openAudioConnection(voiceChannel)
        }
    }

    protected fun notifyAuthorNotConnected() {
        val builder = EmbedBuilder()

        builder.setDescription("${event.author.asMention} Bro you gotta join a voice channel first")
        builder.setColor(Color.RED)

        event.reply(builder.build())
    }

    override fun noMatches() {
        val builder = EmbedBuilder()

        builder.setDescription("${event.author.asMention} Check the link again ey?")
        builder.setColor(Color.RED)

        event.reply(builder.build())
    }

    override fun loadFailed(exception: FriendlyException) {
        val builder = EmbedBuilder()

        builder.setDescription("LMAO: ${exception.message}")
        builder.setColor(Color.RED)

        event.reply(builder.build())
    }
}
