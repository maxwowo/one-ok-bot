package bot.handlers

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color

class GeneralAudioLoadHandler(private val event: CommandEvent) : AudioLoadResultHandler {
    private val musicManager = audioPlayerManager.findGuildMusicManager(event.guild)

    override fun trackLoaded(track: AudioTrack) {
        val builder = EmbedBuilder()

        builder.setDescription("Queued [${track.info.title}](${track.info.uri})")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        event.reply(builder.build())

        audioPlayerManager.queueTrack(event.guild, musicManager, track)
    }

    override fun playlistLoaded(playlist: AudioPlaylist) {
        val builder = EmbedBuilder()

        builder.setDescription("Queued **${playlist.tracks.size}** tracks")
        builder.setFooter(event.author.name)
        builder.setTimestamp(event.message.timeCreated)

        event.reply(builder.build())

        audioPlayerManager.queueTracks(event.guild, musicManager, playlist.tracks)
    }

    override fun noMatches() {
        val builder = EmbedBuilder()

        builder.setDescription("Check the link again ey?")
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
