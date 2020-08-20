package bot.handlers.audioLoadHandlers

import bot.sounds.audioPlayerManager
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.EmbedBuilder

class LoudAudioLoadHandler(private val event: CommandEvent) : AudioLoadHandler(event) {
    override fun trackLoadedReply(track: AudioTrack) {
        if (!audioPlayerManager.queueIsEmpty(event.guild)) {
            val builder = EmbedBuilder()

            builder.setDescription("Queued [${track.info.title}](${track.info.uri})")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            event.reply(builder.build())
        }
    }
    override fun playlistLoadedReply(playlist: AudioPlaylist) {
        if (!audioPlayerManager.queueIsEmpty(event.guild)) {
            val builder = EmbedBuilder()

            builder.setDescription("Queued **${playlist.tracks.size}** tracks")
            builder.setFooter(event.author.name)
            builder.setTimestamp(event.message.timeCreated)

            event.reply(builder.build())
        }
    }
}
