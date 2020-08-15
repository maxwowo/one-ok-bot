package bot.sounds

import bot.handlers.LoudAudioLoadHandler
import com.jagrosh.jdautilities.command.CommandEvent
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
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
            musicManager, trackURL, LoudAudioLoadHandler(event)
        )
    }

    @Synchronized
    fun findGuildMusicManager(guild: Guild): GuildMusicManager {
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

            manager.isSelfDeafened = true
        }
    }

    fun playTrack(guild: Guild, manager: GuildMusicManager, track: AudioTrack) {
        connectToFirstVoiceChannel(guild.audioManager)

        manager.scheduler.queue(track)
    }

    fun skipTrack(channel: TextChannel) {
        val musicManager = findGuildMusicManager(channel.guild)

        musicManager.scheduler.nextTrack()
    }
}

val audioPlayerManager = AudioPlayerManager()
