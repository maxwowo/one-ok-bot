package bot.sounds

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.entities.TextChannel
import net.dv8tion.jda.api.managers.AudioManager

class AudioPlayerManager {
    private val playerManager: AudioPlayerManager
    private val musicManagers: MutableMap<Long, GuildMusicManager>

    init {
        musicManagers = HashMap()
        playerManager = DefaultAudioPlayerManager()

        AudioSourceManagers.registerRemoteSources(playerManager)
        AudioSourceManagers.registerLocalSource(playerManager)
    }

    fun loadAndPlay(channel: TextChannel, trackURL: String) {
        val musicManager = findGuildMusicManager(channel.guild)

        playerManager.loadItemOrdered(
            musicManager, trackURL,
            object : AudioLoadResultHandler {
                override fun trackLoaded(track: AudioTrack) {
                    channel.sendMessage("\uD83C\uDFB8 Adding to queue ${track.info.title}").queue()

                    playTrack(channel.guild, musicManager, track)
                }

                override fun playlistLoaded(playlist: AudioPlaylist) {
                    val firstTrack = playlist.selectedTrack ?: playlist.tracks.first()

                    channel.sendMessage(
                        "\uD83C\uDFB8 Adding to queue ${firstTrack.info.title} " +
                            "(first track of playlist ${playlist.name})"
                    ).queue()

                    playTrack(channel.guild, musicManager, firstTrack)
                }

                override fun noMatches() {
                    channel.sendMessage("\uD83C\uDFB8 Check the link again ey?").queue()
                }

                override fun loadFailed(exception: FriendlyException) {
                    channel.sendMessage("\uD83C\uDFB8 LMAO: ${exception.message}")
                }
            }
        )
    }

    @Synchronized
    private fun findGuildMusicManager(guild: Guild): GuildMusicManager {
        val guildID = guild.id.toLong()
        var musicManager = musicManagers[guildID]

        if (musicManager == null) {
            musicManager = GuildMusicManager(playerManager)
            musicManagers[guildID] = musicManager
        }

        guild.audioManager.sendingHandler = musicManager.getSendHandler()

        return musicManager
    }

    private fun connectToFirstVoiceChannel(manager: AudioManager) {
        if (!manager.isConnected) {
            manager.openAudioConnection(manager.guild.voiceChannels.first())
        }
    }

    private fun playTrack(guild: Guild, manager: GuildMusicManager, track: AudioTrack) {
        connectToFirstVoiceChannel(guild.audioManager)

        manager.scheduler.queue(track)
    }

    fun skipTrack(channel: TextChannel) {
        val musicManager = findGuildMusicManager(channel.guild)
        musicManager.scheduler.nextTrack()

        channel.sendMessage("\uD83C\uDFB8 Skipped to next track \uD83D\uDD25 ").queue()
    }
}

val audioPlayerManager = AudioPlayerManager()
