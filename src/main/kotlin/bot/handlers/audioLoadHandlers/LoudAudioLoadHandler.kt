package bot.handlers.audioLoadHandlers

import bot.exceptions.AuthorNotConnectedToVoiceChannelException
import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder

class LoudAudioLoadHandler(private val event: CommandEvent) : AudioLoadHandler(event) {
    override fun trackLoaded(track: AudioTrack) {
        try {
            val builder = EmbedBuilder()

            connectToVoiceChannel()

            builder.setDescription("Queued [${track.info.title}](${track.info.uri})")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated).build()

            event.reply(builder.build())

            audioPlayerManager.queueTrack(event.guild, track)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }

    override fun playlistLoaded(playlist: AudioPlaylist) {
        val tracks = playlist.tracks
        val firstTrack = playlist.selectedTrack ?: playlist.tracks.first()
        val firstTrackIndex = playlist.tracks.indexOf(firstTrack)
        val remainingTracks = tracks.subList(firstTrackIndex + 1, tracks.size) + tracks.subList(0, firstTrackIndex)

        try {
            val builder = EmbedBuilder()

            connectToVoiceChannel()

            builder.setDescription("Queued **${playlist.tracks.size}** tracks")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            event.reply(builder.build())

            audioPlayerManager.queueTracks(event.guild, firstTrack, remainingTracks)
        } catch (exception: AuthorNotConnectedToVoiceChannelException) {
            notifyAuthorNotConnected()
        }
    }
}
