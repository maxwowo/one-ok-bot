package bot.sounds

import bot.commands.OneCommand
import com.jagrosh.jdautilities.command.CommandEvent
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

    fun loadAndPlay(event: CommandEvent, trackURL: String) {
        val musicManager = findGuildMusicManager(event.guild)

        playerManager.loadItemOrdered(
            musicManager, trackURL,
            object : AudioLoadResultHandler {
                override fun trackLoaded(track: AudioTrack) {
                    OneCommand.handleTrackLoaded(event, track)

                    playTrack(event.guild, musicManager, track)
                }

                override fun playlistLoaded(playlist: AudioPlaylist) {
                    val firstTrack = playlist.selectedTrack ?: playlist.tracks.first()

                    OneCommand.handlePlaylistLoaded(event, playlist)

                    playTrack(event.guild, musicManager, firstTrack)
                }

                override fun noMatches() {
                    OneCommand.handleNoMatches(event)
                }

                override fun loadFailed(exception: FriendlyException) {
                    OneCommand.handleLoadFailed(event, exception)
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

            manager.setSelfDeafened(true)
        }
    }

    private fun playTrack(guild: Guild, manager: GuildMusicManager, track: AudioTrack) {
        connectToFirstVoiceChannel(guild.audioManager)

        manager.scheduler.queue(track)
    }

    fun skipTrack(channel: TextChannel) {
        val musicManager = findGuildMusicManager(channel.guild)

        musicManager.scheduler.nextTrack()
    }
}

val audioPlayerManager = AudioPlayerManager()
