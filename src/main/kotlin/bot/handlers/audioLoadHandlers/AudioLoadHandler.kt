package bot.handlers.audioLoadHandlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

abstract class AudioLoadHandler(private val event: CommandEvent) : AudioLoadResultHandler {
    abstract fun trackLoadedReply(track: AudioTrack)

    abstract fun playlistLoadedReply(playlist: AudioPlaylist)

    private fun connectToVoiceChannel() {
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

    override fun trackLoaded(track: AudioTrack) {
        val builder = EmbedBuilder()

        trackLoadedReply(track)

        try {
            connectToVoiceChannel()

            audioPlayerManager.queueTrack(event.guild, track)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            builder.setDescription("${event.author.asMention} Bro you gotta join a voice channel first")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        }
    }

    override fun playlistLoaded(playlist: AudioPlaylist) {
        val tracks = playlist.tracks
        val firstTrack = playlist.selectedTrack ?: playlist.tracks.first()
        val firstTrackIndex = playlist.tracks.indexOf(firstTrack)
        val remainingTracks = tracks.subList(firstTrackIndex + 1, tracks.size) + tracks.subList(0, firstTrackIndex)

        playlistLoadedReply(playlist)

        try {
            connectToVoiceChannel()

            audioPlayerManager.queueTracks(event.guild, firstTrack, remainingTracks)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            val builder = EmbedBuilder()

            builder.setDescription("${event.author.asMention} Bro you gotta join a voice channel first")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        }
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
