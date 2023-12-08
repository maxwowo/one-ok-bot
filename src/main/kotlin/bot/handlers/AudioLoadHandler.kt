package bot.handlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

/**
 * An intermediate class that provides an implementation for
 * - noMatches
 * - loadFailed
 *
 * The following are left abstract for the classes that inherit from [AudioLoadHandler] to implement, since they
 * can be heavily customized to suit specific commands
 * - trackLoaded
 * - playlistLoaded
 *
 * @param event The event that caused the audio load handler to be used
 */
abstract class AudioLoadHandler(private val event: CommandEvent) : AudioLoadResultHandler {
    /**
     * The music manager corresponding to the guild that the event was fired from
     */
    protected val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

    /**
     * Connects the bot to the voice channel containing the event's author, throwing
     * [AuthorNotConnectedToVoiceChannelException] if such a voice channel cannot be found
     *
     * A helper method for the classes inheriting from [AudioLoadHandler] to use
     */
    protected fun connectToVoiceChannel() {
        // Only execute if bot is not currently connected to a voice channel in the event's guild
        if (!event.guild.audioManager.isConnected) {
            val author = event.author
            val audioManager = event.guild.audioManager

            // Find voice channel containing event's author
            val voiceChannel =
                event.guild.voiceChannels.find {
                        channel ->
                    author in channel.members.map { it.user }
                } ?: throw AuthorNotConnectedToVoiceChannelException(author)

            // Deafen self to save resources
            audioManager.isSelfDeafened = true

            // Connect to the voice channel
            audioManager.openAudioConnection(voiceChannel)
        }
    }

    /**
     * Notifies the author of the event if they are not in a voice channel when they fired the event
     *
     * A helper method for the classes inheriting from [AudioLoadHandler] to use
     */
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
