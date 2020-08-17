package bot.handlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

class GeneralAudioLoadHandler(private val event: CommandEvent) : AudioLoadResultHandler {
    private fun connectToVoiceChannel() {
        if (!event.guild.audioManager.isConnected) {
            val author = event.author
            val audioManager = event.guild.audioManager
            val voiceChannel = event.guild.voiceChannels.find {
                channel ->
                author in channel.members.map { it.user }
            } ?: throw AuthorNotConnectedToVoiceChannelException(author)

            audioManager.openAudioConnection(voiceChannel)
            audioManager.isSelfDeafened = true
        }
    }

    override fun trackLoaded(track: AudioTrack) {
        val builder = EmbedBuilder()

        try {
            builder.setDescription("Queued [${track.info.title}](${track.info.uri})")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            connectToVoiceChannel()

            audioPlayerManager.queueTrack(event.guild, track)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            builder.clear()
            builder.setDescription("${event.author.asMention} Bro you gotta join a voice channel first")
            builder.setColor(Color.RED)
        }

        event.reply(builder.build())
    }

    override fun playlistLoaded(playlist: AudioPlaylist) {
        val builder = EmbedBuilder()
        val tracks = playlist.tracks
        val firstTrack = playlist.selectedTrack ?: playlist.tracks.first()
        val firstTrackIndex = playlist.tracks.indexOf(firstTrack)
        val remainingTracks = tracks.subList(firstTrackIndex + 1, tracks.size) + tracks.subList(0, firstTrackIndex)

        try {
            builder.setDescription("Queued **${playlist.tracks.size}** tracks")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            connectToVoiceChannel()

            audioPlayerManager.queueTracks(event.guild, firstTrack, remainingTracks)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            builder.clear()
            builder.setDescription("${event.author.asMention} Bro you gotta join a voice channel first")
            builder.setColor(Color.RED)
        }

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
