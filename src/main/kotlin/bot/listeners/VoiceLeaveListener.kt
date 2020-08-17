package bot.listeners

import bot.sounds.audioPlayerManager
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class VoiceLeaveListener : ListenerAdapter() {
    override fun onGuildVoiceLeave(event: GuildVoiceLeaveEvent) {
        if (event.member.user == event.jda.selfUser) {
            audioPlayerManager.clearQueue(event.guild)
        }
    }
}
