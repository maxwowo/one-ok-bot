package bot.commands

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder
import java.awt.Color
import java.time.Instant

/**
 * Class that represents !one command
 *
 * Plays a song
 */
class OneCommand : Command() {
    init {
        name = "one"
        help = "Plays a song"
        arguments = "<Youtube URL>"
    }

    companion object {
        fun handleTrackLoaded(event:CommandEvent, track: AudioTrack) {
            val builder = EmbedBuilder()

            builder.setDescription("Queued [${track.info.title}](${track.info.uri})")
            builder.setFooter(event.author.name)
            builder.setTimestamp(Instant.now())

            event.reply(builder.build())
        }

        fun handlePlaylistLoaded(event:CommandEvent, playlist: AudioPlaylist) {
            val builder = EmbedBuilder()

            builder.setDescription("Queued **${playlist.tracks.size}** tracks")
            builder.setFooter(event.author.name)
            builder.setTimestamp(Instant.now())

            event.reply(builder.build())
        }

        fun handleNoMatches(event: CommandEvent) {
            val builder = EmbedBuilder()

            builder.setDescription("Check the link again ey?")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        }

        fun handleLoadFailed(event: CommandEvent, exception: FriendlyException) {
            val builder = EmbedBuilder()

            builder.setDescription("LMAO: ${exception.message}")
            builder.setColor(Color.RED)

            event.reply(builder.build())
        }
    }

    override fun execute(event: CommandEvent) {
        if (event.args.isEmpty()) {
            event.replyWarning("Give me the song URL dummy")
        } else {
            audioPlayerManager.loadAndPlay(event, event.args.trim())
        }
    }
}
